package analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import managers.ClassManager;
import managers.MethodManager;
import utils.ClassInfoUtils;
import utils.GlobalVariableInfoUtils;
import utils.MethodInfoUtils;

public class PreAnalyzer {
	
	private static final String CLASS_EXTENSION = ".class";
	private static final String JAVA_EXTENSION = ".java";
	private static final String CLASS_FILES_DIRECTORY_STRING = "tmpClassFiles/";
	private static final String TEMP_DIRECTORY_STRING = getBaseDirectoryString() + CLASS_FILES_DIRECTORY_STRING;
	
	public PreAnalyzer() throws ZipException, ClassNotFoundException, IOException, InterruptedException {
		
		ClassWriter analysisWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, analysisWriter) {
			
			private Class clazz;
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

				try {
					File f = new File(TEMP_DIRECTORY_STRING);
					URL[] cp = {f.toURI().toURL()};
					URLClassLoader urlcl = new URLClassLoader(cp);
					String packageClassName = name.replace(File.separator, ".");
					Class clazz = urlcl.loadClass(packageClassName);
					this.clazz = clazz;
					ClassManager.addClass(clazz, version, access, name, signature, superclassName, interfaces);
				} catch (Exception e) {
					System.err.println("Exception occurred: " + e.getClass().getName());
					if(e instanceof ClassNotFoundException)
						ClassManager.addClass(version, access, name, signature, superclassName, interfaces);
				}
				
				super.visit(version, access, name, signature, superclassName, interfaces);
			}
			
			@Override
			public FieldVisitor visitField(int access, 
					String name, 
					String descriptor, 
					String signature,
					Object value) {
				
				ClassInfoUtils ciu = ClassManager.getClassInfoUtils(clazz);
				ciu.addGlobalVariable(access, name, descriptor, signature, value);
				
				FieldVisitor fv = super.visitField(access, name, descriptor, signature, value);
				return fv;
			}
			
			@Override
			public MethodVisitor visitMethod(int access,
					String methodName,
					String methodDescriptor,
					String methodSignature,
					String[] methodExceptions) {
				
				if(clazz != null) {
					Method[] methods = clazz.getDeclaredMethods();
					for(Method method : methods) {
						if(method.getName().equals(methodName) && Type.getMethodDescriptor(method).equals(methodDescriptor)) {
							MethodManager.addMethod(method, clazz, access, methodName, methodDescriptor, methodSignature, methodExceptions);
							break;
						}							
					}
				}
							
				MethodVisitor mv = super.visitMethod(access, methodName, methodDescriptor, methodSignature, methodExceptions);
				mv = new MethodVisitor(Opcodes.ASM5, mv) {
					
					@Override
					public void visitLocalVariable(String variableName,
		                    String variableDescriptor,
		                    String variableSignature,
		                    Label variableStart,
		                    Label variableEnd,
		                    int variableIndex) {
						
						if(clazz != null) {
							Method[] methods = clazz.getDeclaredMethods();
							for(Method m : methods) {
								if(m.getName().equals(methodName) && Type.getMethodDescriptor(m).equals(methodDescriptor)) {
									MethodInfoUtils miu = MethodManager.getMethodInfoUtils(m, clazz);
									miu.addLocalVariable(variableName, variableDescriptor, variableSignature, variableStart, variableEnd, variableIndex);
									break;
								}
							}
						}
						
						super.visitLocalVariable(variableName, variableDescriptor, variableSignature, variableStart, variableEnd, variableIndex);
					}					
				};
				return mv;
			}
			
			@Override
			public void visitEnd() {
				System.out.println("*************************************");
			}
		};
		
		/* TODO: Change implementation to read in a user-inputed directory for project codebase. */
		
		String directoryPath = getBaseDirectoryString();
		
		List<String> classpathStrings = getClassesInDirectory(directoryPath);
		List<String> movedClasspathStrings = getMovedClassFiles(classpathStrings);
		
		for(String classpathString : movedClasspathStrings) {
			if(!classpathString.endsWith(CLASS_EXTENSION))
				continue;
			FileInputStream inputStream = new FileInputStream(classpathString);
	        ClassReader classReader;
	        try {
	        	classReader = new ClassReader(inputStream);
		        classReader.accept(cv, ClassReader.EXPAND_FRAMES);
			} catch (Exception e) {
				System.err.println("Exception occurred: " + e.getClass().getName());
				continue;
			}
		}
	}
	
	private List<String> getClassesInDirectory(String dirName) throws ZipException, IOException, ClassNotFoundException {
	    dirName = dirName.replaceAll("\\.", File.separator);
	    List<String> classes = new ArrayList<>();	    
	    processFiles(dirName, dirName, classes);
	    return classes;
	}

	private void processFiles(String dirName, String classpathEntry, List<String> classes) throws ZipException, ClassNotFoundException, IOException {
    	if(classpathEntry.endsWith(".zip"))
    		processZipFile(dirName, classpathEntry, classes);
		else if(classpathEntry.endsWith(".jar"))
			processJarFile(dirName, classpathEntry, classes);
		else if(classpathEntry.contains(dirName) && (classpathEntry.endsWith(CLASS_EXTENSION) || classpathEntry.endsWith(JAVA_EXTENSION)))
			processClass(dirName, classpathEntry, classes);
		else if(new File(classpathEntry).isDirectory())
			processDirectory(dirName, classpathEntry, classes);
	}
	
	/* TODO: Finish implementation. */
	private void processZipFile(String baseDirName, String classpathEntry, List<String> classes) throws ZipException, IOException, ClassNotFoundException {
	    String classpath = "";
		ZipFile zip = new ZipFile(new File(classpathEntry));
		Enumeration<? extends ZipEntry> entries = zip.entries();
		while (entries.hasMoreElements()) {
			ZipEntry zipEntry = entries.nextElement();
		}
	}
	
	/* TODO: Finish implementation. */
	private void processJarFile(String baseDirName, String classpathEntry, List<String> classes) {
        String classpath = "";
		File jarFile = new File(classpathEntry);
        try {
            JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarFile));
            JarEntry entry;
            while((entry = jarInputStream.getNextJarEntry()) != null) {
                classpath = entry.getName();
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
	
	private void processClass(String baseDirName, String classpath, List<String> classes) throws ClassNotFoundException {
		classes.add(classpath);
	}
	
	private void processDirectory(String baseDirName, String dir, List<String> classes) throws ZipException, ClassNotFoundException, IOException {
		dir += dir.endsWith(File.separator) ? "" : File.separator;
		File directory = new File(dir);
		File[] files = directory.listFiles();
		for(File file : files) {
			String classFilepath = dir + file.getName();
			processFiles(baseDirName, classFilepath, classes);
		}
	}

	public List<String> getMovedClassFiles(List<String> classFilenames) {
		List<String> movedClassFiles = new ArrayList<>();

		ClassWriter analysisWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, analysisWriter) {

			@Override
			public void visit(int version, 
					int access, 
					String name,
					String signature,
					String superclassName,
					String[] interfaces) {
				String[] stringDirectoriesInPath = name.split(File.separator);
				String currentTraversedDirectoryString = TEMP_DIRECTORY_STRING;
				for(int i = 0; i < stringDirectoriesInPath.length - 1; i++) {
					currentTraversedDirectoryString += stringDirectoriesInPath[i] + File.separator;
					File currentTraversedDirectory = new File(currentTraversedDirectoryString);
					if(!currentTraversedDirectory.isDirectory())
						currentTraversedDirectory.mkdirs();
				}
				
				String stringPathToSourceWithoutExtension = TEMP_DIRECTORY_STRING + stringDirectoriesInPath[stringDirectoriesInPath.length - 1];
				String stringPathToDestinationWithoutExtension = currentTraversedDirectoryString + stringDirectoriesInPath[stringDirectoriesInPath.length - 1];
				
				File sourceClassFile = new File(stringPathToSourceWithoutExtension + CLASS_EXTENSION);
				File destinationClassFile = new File(stringPathToDestinationWithoutExtension + CLASS_EXTENSION);
				File sourceJavaFile = new File(stringPathToSourceWithoutExtension + JAVA_EXTENSION);
				File destinationJavaFile = new File(stringPathToDestinationWithoutExtension + JAVA_EXTENSION);
				movedClassFiles.add(stringPathToDestinationWithoutExtension + CLASS_EXTENSION);
				try {
					Files.move(sourceClassFile.toPath(), destinationClassFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					if(sourceJavaFile.exists())
						Files.move(sourceJavaFile.toPath(), destinationJavaFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		File classFilesDirectory = new File(TEMP_DIRECTORY_STRING);
		classFilesDirectory.mkdirs();
		for(String classFilename : classFilenames) {
			File classFileSource = new File(classFilename);
			String[] classFilenameElements = classFilename.split(File.separator);
			String className = classFilenameElements[classFilenameElements.length - 1];
			File classFileDestination = new File(TEMP_DIRECTORY_STRING + className);
			try {
				Files.copy(classFileSource.toPath(), classFileDestination.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
			}
		}
		for(String classFilename : classFilenames) {
			if(!classFilename.endsWith(CLASS_EXTENSION))
				continue;
			String[] classFilenameElements = classFilename.split(File.separator);
			String className = classFilenameElements[classFilenameElements.length - 1];
			FileInputStream inputStream;
	        ClassReader classReader;
	        try {
				inputStream = new FileInputStream(TEMP_DIRECTORY_STRING + className);
	        	classReader = new ClassReader(inputStream);
		        classReader.accept(cv, 0);
			} catch (Exception e) {
			}	        	
		}
		return movedClassFiles;
	}

	public static String getBaseDirectoryString() {
		Path currentRelativePath = Paths.get("");
		String directoryPath = currentRelativePath.toAbsolutePath().toString();
		directoryPath += directoryPath.endsWith(File.separator) ? "" : File.separator; 
		return directoryPath;
	}
}