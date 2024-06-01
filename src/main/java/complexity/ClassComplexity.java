package complexity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier.Keyword;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;

import managers.ClassManager;
import managers.MethodManager;
import utils.ClassInfoUtils;
import utils.MethodInfoUtils;

public class ClassComplexity {
	
	public ClassComplexity(String classFile) throws FileNotFoundException {
		ClassWriter analysisWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, analysisWriter) {
			
			private int version;
			private int access;
			private String name;
			private String signature;
			private String superclassName;
			private String[] interfaces;
			
			@Override
			public void visit(int version, 
					int access, 
					String name, 
					String signature,
					String superclassName,
					String[] interfaces) {
				
				this.version = version;
				this.access = access;
				this.name = name;
				this.signature = signature;
				this.superclassName = superclassName;
				this.interfaces = interfaces;

				super.visit(version, access, name, signature, superclassName, interfaces);
			}
			
			@Override
			public FieldVisitor visitField(int access, 
					String name, 
					String descriptor, 
					String signature,
					Object value) {
				
				FieldVisitor fv = super.visitField(access, name, descriptor, signature, value);
				return fv;
			}
			
			@Override
			public MethodVisitor visitMethod(int access,
					String methodName,
					String methodDescriptor,
					String methodSignature,
					String[] methodExceptions) {
		
				System.out.println("Method: " + methodName);
				MethodVisitor mv = super.visitMethod(access, methodName, methodDescriptor, methodSignature, methodExceptions);
				mv = new MethodVisitor(Opcodes.ASM5, mv) {
					
					@Override
					public void visitLocalVariable(String variableName,
		                    String variableDescriptor,
		                    String variableSignature,
		                    Label variableStart,
		                    Label variableEnd,
		                    int variableIndex) {
						super.visitLocalVariable(variableName, variableDescriptor, variableSignature, variableStart, variableEnd, variableIndex);
					}
					
					@Override
					public void visitFieldInsn(int opcode,
		                    String owner,
		                    String name,
		                    String desc) {
						System.out.println("\tField: owner = " + owner + ", name = " + name);
						super.visitFieldInsn(opcode, owner, name, desc);
					}
				};
				return mv;
			}
			
			@Override
			public void visitEnd() {}			
		};
		
		FileInputStream inputStream = new FileInputStream(classFile);
        ClassReader classReader;
        try {
        	classReader = new ClassReader(inputStream);
	        classReader.accept(cv, ClassReader.EXPAND_FRAMES);
		} catch (Exception e) {
			System.err.println("Exception occurred: " + e.getClass().getName());
		}        
	}
	
	public static double getLCOM(Class clazz) {
		return 1.0;
	}
	
	public static List<Method> getMethods(Class clazz) {
		Method[] methodsArray = clazz.getDeclaredMethods();
		List<Method> methodsList = new ArrayList<>();
		for(Method method : methodsArray)
			methodsList.add(method);
		return methodsList;
	}
}
