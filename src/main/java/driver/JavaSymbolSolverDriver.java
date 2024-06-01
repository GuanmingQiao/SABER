package driver;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import coveredclass.org.apache.commons.io.FileUtils;
import pojo.IORecord;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.SignatureConverter;
import utils.MonitorInfo;
import utils.SourceParserFileUtils;
import visitor.JavaParserMethodVisitor;
import xml.XMLParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class JavaSymbolSolverDriver {
    private static  final String ROOT_PATH = "./source_codebase/";

    private static final String RUNTIME_PATH = "./source_codebase/runtime_result_repo/";

    private static final String IO_RECORD_PATH = "./source_codebase/io_record_repo";

    private static final String JSON_IO_PATH = "./source_codebase/io_replacement_repo/";

    private static final String SOURCE_PATH = ROOT_PATH + "evosuite_tests/source_repo/";

    private static final String TEMP_PATH = ROOT_PATH + "temp_repo/";

    public static MethodDeclaration run(IORecord originalIORecord, IORecord newIORecord, String ioRecordPath) throws Exception {
        // Create runtime_result_repo
        ProcessBuilder createRepoPB = new ProcessBuilder(Arrays.asList(String.format("mkdir %s", RUNTIME_PATH).split(" ")));
        createRepoPB.inheritIO();
        Process createRepoProcess = createRepoPB.start();
        createRepoProcess.waitFor();

        // Create IO Replacement Repo
        createRepoPB = new ProcessBuilder(Arrays.asList(String.format("mkdir %s", JSON_IO_PATH).split(" ")));
        createRepoPB.inheritIO();
        createRepoProcess = createRepoPB.start();
        createRepoProcess.waitFor();

        XMLParser xmlParser = new XMLParser(originalIORecord, newIORecord);

        List<Object> oldInputs = xmlParser.getOldInput().sortedInputs;
        List<Object> newInputs = xmlParser.getNewInput().sortedInputs;
        
        for (int i = 0; i < oldInputs.size(); i++) {
            Object o = oldInputs.get(i);
            if (o.getClass().equals(xml.XMLWrapper.class)) {
                xml.XMLWrapper xmlWrapper = (xml.XMLWrapper) o;
                oldInputs.set(i, xmlWrapper.obj);
            }
        }
        
        for (int i = 0; i < newInputs.size(); i++) {
            Object o = newInputs.get(i);
            if (o.getClass().equals(xml.XMLWrapper.class)) {
                xml.XMLWrapper xmlWrapper = (xml.XMLWrapper) o;
                newInputs.set(i, xmlWrapper.obj);
            }
        }

        // Add Premain generate input from iORecord
        String[] stackInfoElements = originalIORecord.stackInfo.split(":");
        String[] methodKeyElements = originalIORecord.methodKey.split("-");
        String[] classPathElements = methodKeyElements[0].split("\\.");
        String methodSignature = methodKeyElements[1];
                
        String packageName = "";
        for (int classPathElementIndex = 0;  classPathElementIndex < classPathElements.length - 1; classPathElementIndex++) {
        	packageName += classPathElements[classPathElementIndex] + ".";
        }
        packageName = packageName.substring(0, packageName.length() - 1);
        String className = classPathElements[classPathElements.length - 1];
        String testCaseName = stackInfoElements[stackInfoElements.length - 2];
        String fileName = "TEST_COLUMBIA_SABER_" + className + ".java";
        String sourcePath = SOURCE_PATH + packageName.replace(".", "/");
        
        //System.out.println("packageName: " + packageName.replace(".", "/"));
        //System.out.println("className:" + className);
        //System.out.println("sourcePath:" + sourcePath);
        
        Map<String, Object> infoMap = new HashMap<>();
		infoMap.put("IORECORD_PATH", ioRecordPath);
        infoMap.put("NEW_INPUTS", newInputs);
        infoMap.put("TEST_CASE", testCaseName);
        
        TypeSolver typeSolver = new ReflectionTypeSolver();
        TypeSolver contextTypeSolver = new JavaParserTypeSolver(new File(ROOT_PATH + "source_repo/"));
        typeSolver.setParent(contextTypeSolver);
        TypeSolver jarTypeSolver1 = new JarTypeSolver("hamcrest-core-1.3.jar");
        TypeSolver jarTypeSolver2 = new JarTypeSolver("junit-4.13.jar");

        CombinedTypeSolver combinedSolver = new CombinedTypeSolver();
        combinedSolver.add(typeSolver);
        combinedSolver.add(contextTypeSolver);
        combinedSolver.add(jarTypeSolver1);
        combinedSolver.add(jarTypeSolver2);

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedSolver);
        StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);
        
        CompilationUnit cu = StaticJavaParser.parse(new File(sourcePath + "/" + fileName));
        cu.getTypes().forEach(classDec -> classDec.getMembers().forEach(method -> method.accept(new JavaParserMethodVisitor.AddNewInputVisitor(), infoMap)));

        CompilationUnit cuClone = cu.clone();

        // Add Premain Monitoring Code
        //System.out.println("Method Name: " + packageName + "." + className + "." + methodSignature);
        final String fullMethodName = SignatureConverter.convertSignatureToJSSFormat(packageName + "." + className + "." + methodSignature);
        cu.getTypes().forEach(classDec -> classDec.getMembers().forEach(method -> method.accept(new JavaParserMethodVisitor.AddMonitoringCodeVisitor(), new MonitorInfo(fullMethodName, testCaseName, new HashSet<>()))));

        // Move Testing file, original file, and reporter to Temporary Repo
        //System.out.println(sourcePath);
        FileUtils.copyDirectory(new File(sourcePath), new File("source_codebase/temp_repo/temp/replacement_repo"));

        // Move pojo and xml to Temporary Repo
        FileUtils.copyDirectory(new File("src/main/java/xml"), new File("source_codebase/temp_repo/xml"));
        FileUtils.copyDirectory(new File("src/main/java/pojo"), new File("source_codebase/temp_repo/pojo"));
        cu.addImport("xml.XMLParser");
        cu.addImport("java.util.List");
        SourceParserFileUtils.write_normal(TEMP_PATH + packageName.replace(".", "/") + "/", fileName, cu.toString());

        // Compile all files
        String javacCommand = "javac -cp hamcrest-core-1.3.jar:junit-4.13.jar:json-simple-1.1.1.jar:xstream-1.4.11.jar:xstream-1.4.11.1.jar:xpp3_min-1.1.4c.jar:evosuite-standalone-runtime-1.0.7-SNAPSHOT.jar:. ";
        for(File name : FileUtils.listFiles(new File("source_codebase/temp_repo"), null, true)) {
        	if(name.getName().contains(".class")) {
        		continue;
        	}
            javacCommand = javacCommand + name.getPath() + " ";
        }
                
        ProcessBuilder javacPB = new ProcessBuilder(Arrays.asList(javacCommand.split(" ")));
        javacPB.inheritIO();
        Process javacProcess = javacPB.start();
        javacProcess.waitFor();
        
//        System.out.println("Running code");

        // Run the test code
        String javaCommand = String.format("java -cp hamcrest-core-1.3.jar:json-simple-1.1.1.jar:junit-4.13.jar:xstream-1.4.11.jar:xstream-1.4.11.1.jar:xpp3_min-1.1.4c.jar:xmlpull-1.1.3.1.jar:evosuite-standalone-runtime-1.0.7-SNAPSHOT.jar:%s %s", TEMP_PATH, packageName + "." + fileName.replace(".java", ""));
//        System.out.println(javaCommand);
        ProcessBuilder javaPB = new ProcessBuilder(Arrays.asList(javaCommand.split(" ")));
        javaPB.inheritIO();
        Process javaProcess = javaPB.start();
        javaProcess.waitFor();

        // Delete temp codebase
        ProcessBuilder deletePB = new ProcessBuilder(Arrays.asList(String.format("rm -rf %s", TEMP_PATH + sourcePath).split(" ")));
        deletePB.inheritIO();
        Process deleteProcess = deletePB.start();
        deleteProcess.waitFor();

        MethodDeclaration methodDec = findAndReplace(cuClone, oldInputs, newInputs, testCaseName, testCaseName + ":" + originalIORecord.methodKey, fullMethodName, newIORecord.stamp);

        // Delete runtime result repo
        deletePB = new ProcessBuilder(Arrays.asList(String.format("rm -rf %s", RUNTIME_PATH).split(" ")));
        deletePB.inheritIO();
        deleteProcess = deletePB.start();
        deleteProcess.waitFor();

        // Delete io_replacement_repo
        deletePB = new ProcessBuilder(Arrays.asList(String.format("rm -rf %s", JSON_IO_PATH).split(" ")));
        deletePB.inheritIO();
        deleteProcess = deletePB.start();
        deleteProcess.waitFor();
        
        return methodDec;
    }

    private static MethodDeclaration findAndReplace(CompilationUnit cuClone, List<Object> oldInputs, List<Object> newInputs, String testCaseName, String ioRecordPairName, String methodSignature, String stamp) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        for (File file : Objects.requireNonNull(new File(RUNTIME_PATH).listFiles())) {
            try {
                Map<Object, List<String>> localVariableMap = new HashMap<>();
                Map<Object, List<String>> targetParamsMap = new HashMap<>();
                // Runtime old inputs
                JSONObject targetIO = (JSONObject) parser.parse(new FileReader(file));

                // Load runtime value of all local variables
                JSONArray localVariables = (JSONArray) targetIO.get("localVar");
                for (Object lv : localVariables) {
                    JSONObject localVariable = (JSONObject) parser.parse(lv.toString());
                    List<String> valuedNames = localVariableMap.getOrDefault(localVariable.get("value"), new ArrayList<>());
                    valuedNames.add(localVariable.get("variableName").toString());
                    localVariableMap.put(localVariable.get("value"), valuedNames);
                }

                // Load runtime value of target variables
                JSONArray targetParams = (JSONArray) targetIO.get("targetParam");
                for (Object lv : targetParams) {
                    JSONObject targetParam = (JSONObject) parser.parse(lv.toString());
                    List<String> valuedNames = targetParamsMap.getOrDefault(targetParam.get("value"), new ArrayList<>());
                    valuedNames.add(targetParam.get("variableName").toString());
                    targetParamsMap.put(targetParam.get("value"), valuedNames);
                }

                Map<String, String> replacementMap = new HashMap<>();
                // For each old input in targetParams, find its index in oldInputs list
                //System.out.println("All variables: " + localVariableMap.toString());
                //System.out.println("Old input variables" + targetParamsMap.toString());
                for (int i = 0; i < oldInputs.size(); i++) {
                    // Find its corresponding newInput by index
                    Object newInput = newInputs.get(i);
                    Object oldInput = oldInputs.get(i);
                    //System.out.println("new input: " + newInput.toString());
                    //System.out.println("old input: " + oldInput.toString());
                    if (!targetParamsMap.containsKey(oldInput.toString())) {
                    	continue;
                    }
                    // Find names of value newInput in localVariables
                    List<String> newInputNames = localVariableMap.get(newInput.toString());
                    List<String> oldInputNames = targetParamsMap.get(oldInput.toString());
                    replacementMap.put(oldInputNames.get(0), newInputNames.get(0));
                }

                Map<String, Object> infoMap = new HashMap<>();
                infoMap.put("TARGET_METHOD", methodSignature);
                infoMap.put("REPLACEMENT_MAP", replacementMap);
                infoMap.put("TEST_CASE", testCaseName);
                infoMap.put("NEW_Name", testCaseName + "_" + stamp);
                
                MethodDeclaration methodDeclarationToReturn = null;
                
                // Replace
                for(TypeDeclaration typeDec : cuClone.getTypes()) {
                	//System.out.println("TypeDec: " + typeDec.getNameAsString());
                	for (Object methodDecObj: typeDec.getMembers()) {
                    	//System.out.println("MethodDecObj: " + methodDecObj.getClass().getName());
                		MethodDeclaration methodDec = (MethodDeclaration) methodDecObj;
                    	//System.out.println("MethodDec: " + methodDec.getNameAsString());
                    	if (methodDec.getNameAsString().equals(testCaseName)) {
                			methodDec.accept(new JavaParserMethodVisitor.VariableReplacementVisitor(), infoMap);
                			for(Statement blockStmt : methodDec.getBody().get().getStatements()) {
                				if(blockStmt.toString().startsWith("assert") || blockStmt.toString().startsWith("Assert")) {
                					methodDec.getBody().get().remove(blockStmt);
                					break;
                				}
                			}
                            return methodDec;
                        }
                	}
                }
                	
//                cuClone.getTypes().forEach(classDec -> classDec.getMembers().forEach(method -> {
//                    // Check if is target test case (important for monitoring only the IOs for current IORecord
//                    if (method.asMethodDeclaration().getNameAsString().equals(testCaseName)) {
//                        method.accept(new JavaParserMethodVisitor.VariableReplacementVisitor(), infoMap);
//                        //return method.asMethodDeclaration();
//                    }
//                }));
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }
}