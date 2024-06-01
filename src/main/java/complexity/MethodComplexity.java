package complexity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.xml.soap.Node;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import utils.SignatureConverter;

public class MethodComplexity {
	
	private String sourceDirectoryPath, fullClassName, methodSignature;
	
	public MethodComplexity(String sourceDirectoryPath, String fullClassName, String methodSignature) {
		this.sourceDirectoryPath = sourceDirectoryPath;
		this.fullClassName = fullClassName;
		this.methodSignature = methodSignature;
	}
	
	public int getComplexity() {
		try {
			TypeSolver typeSolver = new ReflectionTypeSolver();
	        TypeSolver contextTypeSolver = new JavaParserTypeSolver(new File(sourceDirectoryPath));
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
	        CompilationUnit compilationUnit = StaticJavaParser.parse(new File(sourceDirectoryPath + fullClassName.replace(".", File.separator) + ".java"));
	        System.out.println("Full class name: " + fullClassName);
			Optional<ClassOrInterfaceDeclaration> optClassDec = compilationUnit.getClassByName(fullClassName.split("\\.")[fullClassName.split("\\.").length - 1]);
			ClassOrInterfaceDeclaration classDec = optClassDec.get();
			
			String convertedSignature = SignatureConverter.convertSignatureToJSSFormat(methodSignature);
			List<MethodDeclaration> methodDecs = classDec.getMethods();
			for(MethodDeclaration methodDec : methodDecs) {
				String jssSignature = methodDec.resolve().getSignature().replaceAll("<.*?> ?", "");
				if(jssSignature.contentEquals(convertedSignature)) {
					int numStatements = methodDec.getBody().get().getStatements().size();
					int numLinesOfCode = methodDec.getBody().get().toString().split("\r\n|\r|\n").length - 2;
					return numStatements + numLinesOfCode;
				}
			}
		} catch (Exception e) {
			return Integer.MAX_VALUE;			
		}
		return Integer.MAX_VALUE;
	}
	
	public static void main(String[] args) {
		MethodComplexity mc = new MethodComplexity("/Users/adityasridhar/Documents/COLUMBIA/SABER/src/main/java/",
				"complexity.DummyClass2",
				"setListOfNumbers(Ljava.util.List;)V");
		System.out.println(mc.getComplexity());		
	}
}