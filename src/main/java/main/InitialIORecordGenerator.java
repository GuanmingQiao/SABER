package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.objectweb.asm.Type;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier.Keyword;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.thoughtworks.xstream.XStream;

import conversion.TypeConversion;
import pojo.IORecord;
import xml.XMLTools;

public class InitialIORecordGenerator {
	
//	private static final String PKG_NAME = "";
	private static final String PKG_NAME = "p5/";
//	private static final String CLASS = "DummyClass2";
	private static final String CLASS = PKG_NAME + "DummyClass2";
	private static final String CLASS2 = PKG_NAME + "DummyClass3";
	
	private static final String CLONE_ENDING = "__CLONE";
	private static final String PROJECT = "/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/";
	private static final String SRC_NAME = "src/";
	private static final String SRC = PROJECT + SRC_NAME;
//	private static final String BYTECODEBASE = "/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/bytecodebase/";
	private static final String BYTECODEBASE = "/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/bytecodebase2/";
//	private static final String BYTECODEBASE = PROJECT + "bytecodebase/";
//	private static final String BYTECODEBASE = "/Users/adityasridhar/Documents/COLUMBIA/hitoshiIO2/bytecodebase2/";
	private static final String APPLICATION_WITHOUT_EXTENSION = CLASS;
	private static final String APPLICATION_WITH_EXTENSION = APPLICATION_WITHOUT_EXTENSION + ".java";
	private static final String TEST_WITHOUT_EXTENSION = CLASS + "Test";
	private static final String TEST_WITH_EXTENSION = TEST_WITHOUT_EXTENSION + ".java";
	private static final String TEST_WITH_CLASS_EXTENSION = TEST_WITHOUT_EXTENSION + ".class";
	private static final String TEST_CLONE_WITHOUT_EXTENSION = TEST_WITHOUT_EXTENSION + CLONE_ENDING;
	private static final String TEST_CLONE_WITH_EXTENSION = TEST_CLONE_WITHOUT_EXTENSION + ".java";
    private static final String EVOSUITE_COMMAND = "java -jar target/dependency/evosuite-master-1.0.6.jar -class %s -projectCP %s -Dtools_jar_location=%s -Dtarget_method=\"%s\" -criterion method";
	private static final String TEST2_WITHOUT_EXTENSION = CLASS2 + "Test";
	private static final String TEST2_WITH_EXTENSION = TEST2_WITHOUT_EXTENSION + ".java";
	private static final String TEST2_CLONE_WITHOUT_EXTENSION = TEST2_WITHOUT_EXTENSION + CLONE_ENDING;
	private static final String TEST2_CLONE_WITH_EXTENSION = TEST2_CLONE_WITHOUT_EXTENSION + ".java";
    
    private static List<IORecord> ioRecords = new ArrayList<>();
	
	public InitialIORecordGenerator() {
		setup();
	}
	
	private void setup() {
		JavaParser javaParser = new JavaParser();
		try {
        	String classpathProperty = System.getProperty("java.class.path");
			File appFile = new File(BYTECODEBASE + APPLICATION_WITH_EXTENSION);
			File testFile = new File(BYTECODEBASE + TEST_WITH_EXTENSION);
			File testCloneFile = new File(BYTECODEBASE + TEST_CLONE_WITH_EXTENSION);

			Runtime.getRuntime().exec("javac -cp \".:" + BYTECODEBASE + ":" + classpathProperty + "\" " + BYTECODEBASE + APPLICATION_WITH_EXTENSION);
			Runtime.getRuntime().exec("javac -cp \".:" + BYTECODEBASE + ":" + classpathProperty + "\" " + BYTECODEBASE + TEST_WITH_EXTENSION);
			Runtime.getRuntime().exec("touch " + BYTECODEBASE + TEST_CLONE_WITH_EXTENSION);
			
			ProcessBuilder pb0 = new ProcessBuilder("cat").redirectInput(testFile).redirectOutput(testCloneFile);
        	Process process0 = pb0.start();
        	process0.waitFor();
			process0.destroy();
			
        	ParseResult<CompilationUnit> parsedResult = javaParser.parse(testFile);
			CompilationUnit compilationUnit = parsedResult.getResult().get();
			String testName = TEST_WITHOUT_EXTENSION;
			if(testName.contains(File.separator))
				testName = testName.split(File.separator)[testName.split(File.separator).length - 1];
			Optional<ClassOrInterfaceDeclaration> optClassDec = compilationUnit.getClassByName(testName);
			ClassOrInterfaceDeclaration classDec = optClassDec.get();
			
			List<MethodDeclaration> methodDecs = classDec.getMethods();
			
			for(FieldDeclaration fd : classDec.getFields()) {
				fd.addModifier(Keyword.STATIC);
			}
			for(MethodDeclaration md : classDec.getMethods()) {
				md.addModifier(Keyword.STATIC);
				md.walk(VariableDeclarator.class, e -> System.out.println());
//				List<VariableDeclarator> x;
//				for(VariableDeclarator vd : x) {
//					System.out.println(vd.getInitializer().get());
//				}
				System.out.println();
			}
			List<MethodDeclaration> methodDecsForMain = classDec.getMethodsByName("main");
			MethodDeclaration methodDecMain = new MethodDeclaration();
			if(methodDecsForMain == null || methodDecsForMain.size() == 0) {
				methodDecMain = classDec.addMethod("main", Keyword.PUBLIC, Keyword.STATIC);
				methodDecMain.addParameter(String[].class, "args");
			}
			else {
				for(MethodDeclaration methodDecForMain : methodDecsForMain) {
					if(methodDecForMain.hasModifier(Keyword.PUBLIC)
							&& methodDecForMain.hasModifier(Keyword.STATIC)
							&& methodDecForMain.getType().equals(Type.VOID_TYPE)
							&& methodDecForMain.hasParametersOfType(String[].class)
							&& methodDecForMain.getParameters().size() == 1) {
						methodDecMain = methodDecForMain;
						break;
					}
				}
			}
			BlockStmt blockStmtMain = new BlockStmt();
			for(MethodDeclaration methodDec : methodDecs) {
				MethodCallExpr methodCallExpr = new MethodCallExpr(methodDec.getNameAsString());
				blockStmtMain.addStatement(methodCallExpr);
			}
			methodDecMain.setBody(blockStmtMain);
			
			FileWriter fileWriter = new FileWriter(testFile, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			PrintWriter out = new PrintWriter(bufferedWriter);				
			out.println(compilationUnit.toString());
			out.flush();
			out.close();
			bufferedWriter.close();
			fileWriter.close();
			
            try {
    			Runtime.getRuntime().exec("rm " + BYTECODEBASE + TEST_WITH_CLASS_EXTENSION);            	
    			Runtime.getRuntime().exec("javac -cp \".:" + BYTECODEBASE + ":" + classpathProperty + "\" " + BYTECODEBASE + TEST_WITH_EXTENSION);
    			
				ProcessBuilder pb1 = new ProcessBuilder();
				pb1.command("java", "-cp", "target/CloneDetector-0.0.1-SNAPSHOT.jar", "edu.columbia.cs.psl.ioclones.analysis.PreAnalyzer", "-cb", BYTECODEBASE);
				pb1.directory(new File("/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/src/hitoshiIO2/clonedetector"));
				Process process1 = pb1.start();
				process1.waitFor();
		    	InputStream is = process1.getInputStream();
				String line = "";
		    	BufferedReader br = new BufferedReader(new InputStreamReader(is));
				while((line = br.readLine()) != null) {
					System.out.println(line);
				}
				System.out.println();
				System.out.println("**********************************************");
				System.out.println();
				pb1.command("java",
							"-javaagent:target/CloneDetector-0.0.1-SNAPSHOT.jar",
							"-noverify",
							"-cp",
							"target/CloneDetector-0.0.1-SNAPSHOT.jar:" + BYTECODEBASE,
							"edu.columbia.cs.psl.ioclones.driver.IODriver",
							"-class",
							TEST_WITHOUT_EXTENSION.replace(File.separator, "."),
							"-cb",
							BYTECODEBASE);
				pb1.directory(new File("/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/src/hitoshiIO2/clonedetector"));
		    	process1 = pb1.start();
				is = process1.getInputStream();
				br = new BufferedReader(new InputStreamReader(is));
				while((line = br.readLine()) != null)
					System.out.println(line);
				br.close();
				is.close();
				process1.destroy();

    			ProcessBuilder pb2 = new ProcessBuilder("cat").redirectInput(testCloneFile).redirectOutput(testFile);
	        	Process process2 = pb2.start();
	        	process2.waitFor();
    			process2.destroy();
    			
    			Runtime.getRuntime().exec("javac -cp \".:" + BYTECODEBASE + ":" + classpathProperty + "\" " + BYTECODEBASE + TEST_WITH_EXTENSION);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Runtime.getRuntime().exec("javac -cp \".:" + BYTECODEBASE + ":" + classpathProperty + "\" " + BYTECODEBASE + TEST_WITH_EXTENSION);
			Runtime.getRuntime().exec("rm " + BYTECODEBASE + TEST_CLONE_WITH_EXTENSION);

			retrieveIORecords();
			
			System.out.println();
			for(IORecord ioRecord : ioRecords) {
				System.out.println("IO Record " + ioRecord.id);
				System.out.println("   Method: " + ioRecord.methodKey);
				System.out.println("   Inputs: " + ioRecord.sortedInputs);
				System.out.println("   Outputs: " + ioRecord.sortedOutputs);
			}
			
			if(new File("typeConversionLog.txt").exists())
				new File("typeConversionLog.txt").delete();
			for(int i = 3; i < ioRecords.size(); i++) {
				for(int j = i + 1; j < ioRecords.size(); j++) {
					FileWriter newFileWriter = new FileWriter(new File("typeConversionLog.txt"), true);
					BufferedWriter newBufferedWriter = new BufferedWriter(newFileWriter);
					PrintWriter newOut = new PrintWriter(newBufferedWriter);
					newOut.println("Generating permutations for IO records " + ioRecords.get(i).id + " and " + ioRecords.get(j).id);
					newOut.println();
					newOut.println("IO Record " + ioRecords.get(i).id);
					newOut.println("   Method: " + ioRecords.get(i).methodKey);
					newOut.println("   Inputs: " + ioRecords.get(i).sortedInputs);
					newOut.println("   Outputs: " + ioRecords.get(i).sortedOutputs);
					newOut.println("IO Record " + ioRecords.get(j).id);
					newOut.println("   Method: " + ioRecords.get(j).methodKey);
					newOut.println("   Inputs: " + ioRecords.get(j).sortedInputs);
					newOut.println("   Outputs: " + ioRecords.get(j).sortedOutputs);
					newOut.println();
					newOut.flush();
					newOut.close();
					newBufferedWriter.close();
					newFileWriter.close();
					new TypeConversion(ioRecords.get(i), ioRecords.get(j));
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	private static void retrieveIORecords() {
		String ioRepoDirectoryString = "/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/src/hitoshiIO2/clonedetector/iorepo/";
		File ioRepoDirectory = new File(ioRepoDirectoryString);
		String ioRepoZipString = ioRepoDirectoryString
			+ TEST_WITHOUT_EXTENSION.replace(File.separator, ".")
			+ ".zip";
		String destinationDirectoryString = "/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/src/ioRepoFiles/";
		File destinationDirectory = new File(destinationDirectoryString);
		FileInputStream fileInputStream;
        byte[] buffer = new byte[1024];
        try {
            fileInputStream = new FileInputStream(ioRepoZipString);
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while(zipEntry != null) {
                String filename = zipEntry.getName();
                File file = new File(destinationDirectoryString + filename);
                System.out.println("Unzipping " + filename + " to " + file.getAbsolutePath());
                System.out.println("New file before: " + file.isFile());
                new File(file.getParent()).mkdirs();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                int len;
                while ((len = zipInputStream.read(buffer)) > 0)
                	fileOutputStream.write(buffer, 0, len);
                fileOutputStream.close();
                zipInputStream.closeEntry();
                System.out.println("New file after: " + file.isFile());                
                String[] filenameElements = zipEntry.getName().split(File.separator);
                filenameElements[filenameElements.length - 1] = "NEW_" + filenameElements[filenameElements.length - 1];
                String newFilename  = String.join(File.separator, filenameElements);
                File newFile = new File(destinationDirectoryString + newFilename);
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile, false));
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				
				String line;
				while((line = bufferedReader.readLine()) != null) {
					if(line.contains("edu.columbia.cs.psl.ioclones.pojo.IORecord")) {
						String newLine = line;
						newLine = newLine.replace("edu.columbia.cs.psl.ioclones.pojo.IORecord", "pojo.IORecord");
						bufferedWriter.append(newLine + "\n");
					}
					else if(line.contains("<dhComparator class=\"edu.columbia.cs.psl.ioclones.sim.AbstractSim$DHSComparator\"/>"))
						continue;
					else if(line.contains("edu.columbia.cs.psl.ioclones.pojo.XMLWrapper")) {
						String newLine = line;
						newLine = newLine.replace("edu.columbia.cs.psl.ioclones.pojo.XMLWrapper", "xml.XMLWrapper");
						bufferedWriter.append(newLine + "\n");
					}
					else
						bufferedWriter.append(line + "\n");
				}
				bufferedReader.close();
				bufferedWriter.close();              
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
            fileInputStream.close();
            
            createIORecords(XMLTools.getXStream(), destinationDirectory);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static void createIORecords(XStream xstream, File destinationDirectory) {
        for(File file : destinationDirectory.listFiles()) {
    		System.out.println("File: " + file.toString());
        	if(file.isDirectory())
        		createIORecords(xstream, file);
        	else if(!file.toString().contains("NEW_"))
        		continue;
        	else {
    			IORecord ioRecord = (IORecord) xstream.fromXML(file);
    		    ioRecords.add(ioRecord);
        	}
        }
	}
}
