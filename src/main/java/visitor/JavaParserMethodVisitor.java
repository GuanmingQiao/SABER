package visitor;

import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;
import com.github.javaparser.printer.PrettyPrinterConfiguration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;

import utils.LocalVariable;
import utils.MonitorInfo;

import java.util.*;

public class JavaParserMethodVisitor {
    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    protected static final PrettyPrinterConfiguration prettyPrinterNoCommentsConfiguration = new PrettyPrinterConfiguration().setPrintComments(false);

    public static class ClassDeclarationVisitor extends GenericVisitorAdapter<Node, Void> {
        @Override
        public Node visit(ClassOrInterfaceDeclaration n, Void arg) {
            NodeList<ClassOrInterfaceType> implemented = n.getImplementedTypes();
            ClassOrInterfaceType serializableInterface = StaticJavaParser.parseClassOrInterfaceType("Serializable");
            if(!implemented.contains(serializableInterface))
            	implemented.add(StaticJavaParser.parseClassOrInterfaceType("Serializable"));
            return n;
        }
    }

    public static class AddStaticMethodModifierVisitor extends GenericVisitorAdapter<Node, Void> {
        @Override
        public Node visit(MethodDeclaration n, Void arg) {
            /*
             * here you can access the attributes of the method. this method will be called for all methods in this CompilationUnit,
             * including inner class methods
             */
            NodeList<Modifier> modifiers = n.getModifiers();
            if (!modifiers.contains(new Modifier(Modifier.Keyword.STATIC))) {
                modifiers.add(new Modifier(Modifier.Keyword.STATIC));
            }
            n.setModifiers(modifiers);
            return super.visit(n, arg);
        }
    }

    public static class AddNewInputVisitor extends  GenericVisitorAdapter<Node, Map<String, Object>> {
        @Override
        public Node visit(MethodDeclaration n, Map<String, Object> infoMap) {
            super.visit(n, infoMap);
            BlockStmt body = n.getBody().get();
            String testCaseName = infoMap.get("TEST_CASE").toString();

            // Check if is target test case (important for monitoring only the IOs for current IORecord
            if (!n.getNameAsString().equals(testCaseName)) {
                return n;
            }
            List<Statement> statements = body.getStatements();
            ListIterator<Statement> listIterator = statements.listIterator();
            List<Statement> newStatements = addNewInputStatements(infoMap.get("IORECORD_PATH"), infoMap.get("NEW_INPUTS"));
            for (Statement s : newStatements) {
                listIterator.add(s);
            }
            return n;
        }
    }

    private static List<Statement> addNewInputStatements(Object iORecordPath, Object newInputs) {
        String path = (String) iORecordPath;
        List inputs = (ArrayList) newInputs;
        List<Statement> s = new ArrayList<>();
        //s.add(StaticJavaParser.parseStatement("//New Initialization"));
        s.add(StaticJavaParser.parseStatement(String.format("XMLParser xmlParser = new XMLParser(\"%s\");", path)));
        for (int i = 0; i < inputs.size(); i++) {
            String clazz = inputs.get(i).getClass().getCanonicalName();
            String object_name = String.format("newInput%d", i);
            String init = String.format("%s %s = (%s) xmlParser.getNewInputByIndex(%d);", clazz, object_name, clazz, i);
            s.add(StaticJavaParser.parseStatement(init));
        }
        return s;
    }

    private static int monitor_counter = 0;

    public static class AddMonitoringCodeVisitor extends GenericVisitorAdapter<Node, MonitorInfo> {
        @Override
        public Node visit(MethodDeclaration n, MonitorInfo info) {
            super.visit(n, info);
            BlockStmt body = n.getBody().get();
            String testCaseName = info.getTestCase();
            
            // Check if is target test case (important for monitoring only the IOs for current IORecord
            //System.out.println(String.format("Test case name as in parser: %s", n.getNameAsString()));
            if (!n.getNameAsString().equals(testCaseName)) {
                return n;
            }
            List<Statement> statements = body.getStatements();
            ListIterator<Statement> listIterator = statements.listIterator();
//            System.out.println("Target Method: " + info.getTargetMethod());
            while (listIterator.hasNext()) {
                Statement std = listIterator.next();
                if (parseStatement(std, info.getTargetMethod())) {
                    List<Statement> newStatements = addStatements(std, n.getNameAsString(), info.getLocalVariables());
                    listIterator.previous();
                    for (Statement s: newStatements) {
                        listIterator.add(s);
                    }
                    listIterator.next();
                    break;
                }
                if (isLocalVariable(std)) {
                    NodeList<VariableDeclarator> varExpr = std.asExpressionStmt().getExpression().asVariableDeclarationExpr().getVariables();
                    for (VariableDeclarator var : varExpr) {
                        LocalVariable localVar = new LocalVariable(var.getNameAsString(), var.getType());
                        Set<LocalVariable> locals = info.getLocalVariables();
                        locals.add(localVar);
                        info.setLocalVariables(locals);
                    }
                }
            }
            return n;
        }

        private List<Statement> addStatements(Statement std, String outerMethodName, Set<LocalVariable> localVariables) {
        	List<Statement> s = new ArrayList<>();
            Expression expr = std.asExpressionStmt().getExpression();
            MethodCallExpr methodCallExpr = null;
            if (expr.isMethodCallExpr()) {
                methodCallExpr = expr.asMethodCallExpr();
            } else if (expr.isVariableDeclarationExpr()) {
                VariableDeclarationExpr varDecExpr = expr.asVariableDeclarationExpr();
                for (Node e : varDecExpr.getChildNodes().get(0).getChildNodes()) {
                    if (e.getClass() == MethodCallExpr.class) {
                        methodCallExpr = (MethodCallExpr) e;
                    }
                }
            }
            if (methodCallExpr == null) {
                return s;
            }
            MethodCallExpr signature = methodCallExpr.asMethodCallExpr();
            NodeList<Expression> parameters = signature.getArguments();
            ResolvedMethodDeclaration methodDec = methodCallExpr.resolve();
            NodeList<Parameter> parameterTypes = ((JavaParserMethodDeclaration) methodDec).getWrappedNode().getParameters();
            String methodName = methodDec.getPackageName() + "." + methodDec.getClassName() + "." + methodDec.getName();
            s.add(StaticJavaParser.parseStatement(String.format("Reporter reporter = new Reporter(\"%s\");", methodDec.getPackageName() + "." + methodDec.getClassName() + "." + outerMethodName + "." + methodDec.getName())));
            int index = 0;
            for (int i = 0; i < parameters.size(); i++) {
                Expression param = parameters.get(i);
                String paramName;
                String type = parameterTypes.get(i).getTypeAsString();;
                if (param.isIntegerLiteralExpr()) {
                    paramName = param.asIntegerLiteralExpr().toString();
                } else if (param.isBooleanLiteralExpr()) {
                    paramName = param.asBooleanLiteralExpr().toString();
                } else if (param.isStringLiteralExpr()) {
                    paramName = param.asStringLiteralExpr().toString();
                } else if (param.isCharLiteralExpr()) {
                    paramName = param.asCharLiteralExpr().toString();
                } else if (param.isEnclosedExpr()) {
                	paramName = param.asEnclosedExpr().getChildNodes().get(0).toString();
                } else {
                    paramName = param.asNameExpr().getNameAsString();
                }
                if (type.equals("int")) {
                    type = "Integer";
                }
                if (type.equals("char")) {
                	type = "Character";
                }
                if (type.equals("boolean")) {
                	type = "Boolean";
                }
                String monitorName=  "g" + monitor_counter++;
                String g1 = String.format("GenericsType<%s> %s = new GenericsType<>();", type, monitorName);
                s.add(StaticJavaParser.parseStatement(g1));
                
                String g2 = String.format("%s.set(%s,\"%s\",\"%s\", %d);", monitorName, paramName, paramName.replace("\"", ""), methodName, index++);
//                System.out.println(g2);
                s.add(StaticJavaParser.parseStatement(g2));

                String g3 = String.format("reporter.addTargetParam(%s);", monitorName);
                s.add(StaticJavaParser.parseStatement(g3));
            }
            index = 0;
            for (LocalVariable localVar : localVariables) {
                String variableName = localVar.getName();
                String monitorName = "g" + monitor_counter++;
                String g1 = String.format("GenericsType<%s> %s = new GenericsType<>();", localVar.getTypeAsString(), monitorName);
                s.add(StaticJavaParser.parseStatement(g1));

                String g2 = String.format("%s.set(%s,\"%s\",\"%s\", %d);", monitorName, variableName, variableName, methodName, index++);
                s.add(StaticJavaParser.parseStatement(g2));

                String g3 = String.format("reporter.addLocalVar(%s);", monitorName);
                s.add(StaticJavaParser.parseStatement(g3));
            }
            s.add(StaticJavaParser.parseStatement("reporter.report();"));
            return s;
        }

        private boolean isLocalVariable(Statement std) {
            if (std.isExpressionStmt()) {
                Expression expStmt = std.asExpressionStmt().getExpression();
                return expStmt.isVariableDeclarationExpr();
            }
            return false;
        }
    }

    public static class VariableReplacementVisitor extends GenericVisitorAdapter<Node, Map> {
        @Override
        public Node visit(MethodDeclaration n, Map infoMap) {
            super.visit(n, infoMap);
            BlockStmt body = n.getBody().get();
            List<Statement> statements = body.getStatements();
            ListIterator<Statement> listIterator = statements.listIterator();
            String TARGET = (String) infoMap.get("TARGET_METHOD");
            String TESTCASE_NAME = (String) infoMap.get("NEW_Name");
            Map<String, String> replacementMap = (Map<String, String>) infoMap.get("REPLACEMENT_MAP");
            while (listIterator.hasNext()) {
                Statement std = listIterator.next();
                if (parseStatement(std, TARGET)) {
                    Expression expr = std.asExpressionStmt().getExpression();
//                    System.out.println("Replacing Variable in target method: " + expr.toString());
                    MethodCallExpr methodCallExpr = null;
                    if (expr.isMethodCallExpr()) {
                        methodCallExpr = expr.asMethodCallExpr();
                    } else if (expr.isVariableDeclarationExpr()) {
                        VariableDeclarationExpr varDecExpr = expr.asVariableDeclarationExpr();
                        for (Node e : varDecExpr.getChildNodes().get(0).getChildNodes()) {
                            if (e.getClass() == MethodCallExpr.class) {
                                MethodCallExpr methodCall = (MethodCallExpr) e;
                                String signature = methodCall.resolve().getQualifiedSignature();
                                // remove <>
                                signature = signature.replaceAll("<.*?> ?", "");
                                if (signature.equals(TARGET)) {
                                    methodCallExpr = methodCall;
                                }
                            }
                        }
                    } else {
                        return n;
                    }
                    if (methodCallExpr == null) {
                        return n;
                    }
                    n.setName(TESTCASE_NAME);
                    for (Expression argExpr : methodCallExpr.getArguments()) {
                        if (argExpr.isNameExpr()) {
                            NameExpr nameExpr = argExpr.asNameExpr();
                            if (replacementMap.containsKey(nameExpr.getNameAsString())) {
                            	nameExpr.setName(new SimpleName(replacementMap.get(nameExpr.getNameAsString())));
                            }
                        } else if (argExpr.isIntegerLiteralExpr()) {
                            IntegerLiteralExpr intExpr = argExpr.asIntegerLiteralExpr();
                            if (replacementMap.containsKey(intExpr.toString())) {
                            	NameExpr nameExpr = new NameExpr(replacementMap.get(intExpr.toString()));
                            	argExpr.replace(nameExpr);
                            }
                        } else if (argExpr.isStringLiteralExpr()) {
                        	StringLiteralExpr stringExpr = argExpr.asStringLiteralExpr();
                        	//System.out.println(replacementMap);
                        	if (replacementMap.containsKey(stringExpr.toString().replaceAll("\"", ""))) {
                        		String newName = replacementMap.get(stringExpr.toString().replaceAll("\"", ""));
                                NameExpr nameExpr = new NameExpr(newName);
                                argExpr.replace(nameExpr);
                        	}
                        } else if (argExpr.isEnclosedExpr()) {  
                        	EnclosedExpr enclosedExpr = argExpr.asEnclosedExpr();
                        	String oldName = enclosedExpr.getChildNodes().get(0).toString();
                        	if (replacementMap.containsKey(oldName)) {
                        		String newName = replacementMap.get(oldName);
                            	NameExpr nameExpr = new NameExpr(newName);
                            	argExpr.replace(nameExpr);
                        	}
                        } else if (argExpr.isBooleanLiteralExpr()) {
                        	BooleanLiteralExpr booleanLiteralExpr = argExpr.asBooleanLiteralExpr();
                        	if (replacementMap.containsKey(booleanLiteralExpr.toString())) {
                        		String newName = replacementMap.get(booleanLiteralExpr.toString());
                                NameExpr nameExpr = new NameExpr(newName); 
                        	}
                        }
                    }
                }
            }
            return n;
        }
    }

    public static class ReportMethodSignatureVisitor extends GenericVisitorAdapter<Node, Map<String, List<String>>> {
        @Override
        public Node visit(MethodDeclaration n, Map<String, List<String>> inputMap) {
            /*
             * here you can access the attributes of the method. this method will be called for all methods in this CompilationUnit,
             * including inner class methods
             */
            List<String> signatures = inputMap.containsKey(n.getNameAsString()) ? inputMap.get(n.getNameAsString()) : new ArrayList<String>();
            StringBuilder sb = new StringBuilder();
            sb.append(n.getNameAsString());
            sb.append("(");
            boolean firstParam = true;
            for (Parameter param : n.getParameters()) {
                if (firstParam) {
                    firstParam = false;
                } else {
                    sb.append(", ");
                }
                sb.append(param.getType().toString());
                if (param.isVarArgs()) {
                    sb.append("...");
                }
            }
            sb.append(")");
            signatures.add(sb.toString());
            StringBuilder classPathBuilder = new StringBuilder();
            Optional<Node> optClassNode = n.getParentNode();
            if (optClassNode.get() != null){
                ClassOrInterfaceDeclaration classNode = (ClassOrInterfaceDeclaration) optClassNode.get();
                classPathBuilder.append(classNode.getNameAsString() + ":");
            }
            classPathBuilder.append(n.getNameAsString());
            inputMap.put(classPathBuilder.toString(), signatures);
            return super.visit(n, inputMap);
        }
    }

    public static boolean parseStatement(Statement std, String TARGET) {
        if (std.isExpressionStmt()) {
            Expression expStmt = std.asExpressionStmt().getExpression();
            if (expStmt.isMethodCallExpr()) {
                String signature = expStmt.asMethodCallExpr().resolve().getQualifiedSignature();
                // remove <>
                signature = signature.replaceAll("<.*?> ?", "");
                //System.out.println("Method signature in method call: " + signature);
                return signature.equals(TARGET);
            } else if (expStmt.isVariableDeclarationExpr()) {
                VariableDeclarationExpr varDecExpr = expStmt.asVariableDeclarationExpr();
                for (Node e : varDecExpr.getChildNodes().get(0).getChildNodes()) {
                    if (e.getClass() == MethodCallExpr.class) {
                        MethodCallExpr methodCall = (MethodCallExpr) e;
                        String signature = methodCall.resolve().getQualifiedSignature();
                        // remove <>
                        signature = signature.replaceAll("<.*?> ?", "");
                        //System.out.println("Method signature in variable declartion: " + signature);
                        return signature.equals(TARGET);
                    }
                }
            }
        }
        return false;
    }
}
