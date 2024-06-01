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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.objectweb.asm.Type;

import conversion.TypeConversion;
import database.DatabaseUtils;
import driver.JavaSymbolSolverDriver;
import driver.EvoSuiteDriver;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.Modifier.Keyword;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.utils.Pair;
import com.sun.tools.javac.code.Attribute.Array;
import com.thoughtworks.xstream.XStream;

import complexity.ComplexityAnalyzer;
import pojo.IORecord;
import pojo.IORecordOrganizer;
import randoop.org.apache.commons.io.FileUtils;
import randoop.org.apache.commons.io.filefilter.DirectoryFileFilter;
import randoop.org.apache.commons.io.filefilter.RegexFileFilter;
import sim.SimilarityAnalyzer;
import utils.MonitorInfo;
import utils.SourceParserFileUtils;
import visitor.JavaParserMethodVisitor;
import xml.XMLTools;

public class Main {
	
	public static final String SOURCE_DIRECTORY = "src/main/java/";
	public static final String REPOSITORY_LIST_FILE = "repositoryList.txt";
	public static final String DATABASE_PROPERTIES_FILE = "database.properties";
	public static final String SOURCE_CODEBASE_DIRECTORY = "source_codebase/";
	public static final String STAMP_MAP_FILE = SOURCE_CODEBASE_DIRECTORY + "stampMap.txt";
	public static final String IO_RECORD_ORGANIZERS_MAP_FILE = SOURCE_CODEBASE_DIRECTORY + "ioRecordOrganizersMap.txt";
	public static final String EVOSUITE_TESTS_DIRECTORY = SOURCE_CODEBASE_DIRECTORY + "evosuite_tests/";
	public static final String NEW_TESTS_DIRECTORY = SOURCE_CODEBASE_DIRECTORY + "new_tests/";
	public static final String OUTPUT_IO_REPO_DIRECTORY = SOURCE_CODEBASE_DIRECTORY + "output_io_record_repo/";
	public static final String XML_DUMP_DIRECTORY = SOURCE_CODEBASE_DIRECTORY + "xml_file_dump/";
	public static final String TEST_FILE_PREFIX = "TEST_COLUMBIA_SABER_";
	public static final String CLONE_PREFIX = "CLONED_";
	public static final String CLONE_DETECTOR_DIRECTORY = SOURCE_DIRECTORY + "hitoshiIO2/clonedetector/";
	public static final String OLD_IO_REPO_DIRECTORY = CLONE_DETECTOR_DIRECTORY + "iorepo/";
	public static final String INTERMEDIATE_IO_REPO_DIRECTORY = SOURCE_CODEBASE_DIRECTORY + "temp_io_record_repo/";
	public static final String NEW_IO_REPO_DIRECTORY = SOURCE_CODEBASE_DIRECTORY + "io_record_repo/";
	public static final String IO_RECORD_FULL_CLASS_NAME = "pojo.IORecord";
	public static final String XML_WRAPPER_FULL_CLASS_NAME = "xml.XMLWrapper";
	public static final String XML_DUMP_FILE = TEST_FILE_PREFIX + "xmlFileDump.xml";
	private static List<String> repositoryPaths = new ArrayList<>();
	private static List<TypeConversion> typeConversions = new ArrayList<>();
	private static Map<String, String> ioRecordFileToTestFileMap = new HashMap<>();
	private static Map<IORecord, String> ioRecordToTestFileMap = new HashMap<>();
	private static Map<String, String> newIORecordFileToTestFileMap = new HashMap<>();
	private static Map<IORecord, String> newIORecordToTestFileMap = new HashMap<>();
	private static Map<IORecord, Set<IORecord>> generatedIORecordMap = new HashMap<>();
	private static Map<String, String> testFileToRepoMap = new HashMap<>();
	public static String projectPath, sourceCodebasePath, repositoryListPath, evosuiteTestPath, cloneDetectorPath, 
		oldIORepoPath, intermediateIORepoPath, newIORepoPath, xmlFileDumpPath, outputIORepoPath, newTestPath,
		projectSourcePath, stampMapPath, ioRecordOrganizersMapPath;
	
    private static List<IORecord> originalIORecords = new ArrayList<>();
    private static Map<String, IORecord> stampToIORecordMap = new HashMap<>();
    private static List<IORecord> newIORecords = new ArrayList<>();
    private static Options options = new Options();
    private static CommandLineParser commandLineParser = null;
    private static CommandLine commandLine = null;
    private static UseCase useCase = null;
    private static String userSubjectRepo, userSubjectClass;
    private static Map<String, IORecordOrganizer> ioRecordOrganizersMap = new HashMap<>();
    private static List<String> userSubjectMethods = new ArrayList<>();
    private static List<String> userSubjectMethodKeys = new ArrayList<>();
    private static Connection connection = null;
    
    static {
    	options.addOption("repo", true, "Required argument: name of the repository to analyze");
    	options.addOption("class", true, "Optional argument: name of the class to analyze");
    	options.addOption("methods", true, "Optional argument: signature(s) of the method(s) to analyze, separated by \":\"");
    	options.addOption("type", true, "Required argument: type of use case, "
    			+ "which can be \"debug\", \"baseline\", \"addFeature\", or \"replace\"");
    	
    	options.getOption("repo").setRequired(true);
    	options.getOption("type").setRequired(true);
    }
       
    public enum UseCase {
    	DEBUG, BASELINE, ADD_FEATURE, REPLACE
    }
	
	public static void main(String[] args) throws ClassNotFoundException, ZipException, IOException, InterruptedException, ParseException {
		XStream xstream = XMLTools.getXStream();

		// 1. Set up directories.
		setUpDirectories();
		
		// 2. Set up the database and obtain user information.
		setUpDatabase();
		obtainUserInfo(args);
		
		// 3. Generate EvoSuite tests on a set of repositories.
		executeEvoSuite();
		
		// 4. Run the seed test cases and extract the XML files.
//		runTestCases(evosuiteTestPath);
//		extractXMLFiles(evosuiteTestPath, intermediateIORepoPath);

		// 5. Generate IO record seeds to store I/O information.
//		createIORecords(xstream, new File(intermediateIORepoPath), true);
		
		// 6. Construct type conversions to generate IO records (without outputs) from the seeds.
//		performTypeConversion(xstream);
		
		// 7. Generate new test cases.
//		generateNewTestCases(xstream);
		
		// 8. Run the new test cases and extract the XML files.
//		runTestCases(newTestPath);
//		extractXMLFiles(newTestPath, outputIORepoPath);
		
		// 9. Generate new IO records to store I/O information.
		createIORecords(xstream, new File(outputIORepoPath), false);
		
		// 10. Analyze similarity and complexity.
		analyze();
		
		// 11. Clean up.
//		clean();
	}	
	
	public static void setUpDirectories() throws IOException {
		projectPath = new File(".").getCanonicalPath();
		projectPath += (projectPath.endsWith(File.separator) ? "" : File.separator);
		projectSourcePath = projectPath + SOURCE_DIRECTORY;
		sourceCodebasePath = projectPath + SOURCE_CODEBASE_DIRECTORY;
		repositoryListPath = projectPath + REPOSITORY_LIST_FILE;
		evosuiteTestPath = projectPath + EVOSUITE_TESTS_DIRECTORY;
		newTestPath = projectPath + NEW_TESTS_DIRECTORY;
		cloneDetectorPath = projectPath + CLONE_DETECTOR_DIRECTORY;
		oldIORepoPath = projectPath + OLD_IO_REPO_DIRECTORY;
		intermediateIORepoPath = projectPath + INTERMEDIATE_IO_REPO_DIRECTORY;
		newIORepoPath = projectPath + NEW_IO_REPO_DIRECTORY;
		xmlFileDumpPath = projectPath + XML_DUMP_DIRECTORY + XML_DUMP_FILE;
		outputIORepoPath = projectPath + OUTPUT_IO_REPO_DIRECTORY;
		stampMapPath = projectPath + STAMP_MAP_FILE;
		ioRecordOrganizersMapPath = projectPath + IO_RECORD_ORGANIZERS_MAP_FILE;		
	}
	
	public static void setUpDatabase() throws IOException {
		// Set up the database connection
		Properties prop = new Properties();
		FileInputStream inputStream = new FileInputStream(DATABASE_PROPERTIES_FILE);
		prop.load(inputStream);

		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		connection = DatabaseUtils.getConnection(url, username, password);
		System.out.println("Successfully created the connection to the database!");
//		DatabaseUtils.createDatabaseAndTables(connection);
//		System.out.println("Successfully created the database and associated tables!");
	}
	
	public static void obtainUserInfo(String[] args) throws ParseException {
		commandLineParser = new DefaultParser();
		commandLine = commandLineParser.parse(options, args);
		switch(commandLine.getOptionValue("type").toLowerCase()) {
			case "debug": useCase = UseCase.DEBUG; break;
			case "baseline": useCase = UseCase.BASELINE; break;
			case "addFeature": useCase = UseCase.ADD_FEATURE; break;
			case "replace": useCase = UseCase.REPLACE; break;
			default: useCase = UseCase.BASELINE;
		}
		userSubjectRepo = commandLine.getOptionValue("repo");
		userSubjectClass = commandLine.getOptionValue("class");
		userSubjectMethods.addAll(Arrays.asList(commandLine.getOptionValue("methods").split(":")));
		for(String userSubjectMethod : userSubjectMethods)
			userSubjectMethodKeys.add(userSubjectClass + "-" + userSubjectMethod);		
	}
	
	public static void executeEvoSuite() throws ClassNotFoundException, ZipException, IOException, InterruptedException {	
		// Get list of repositories and run Evosuite on each of them
		File repositoryFile = new File(repositoryListPath);
		BufferedReader bufferedReader2 = new BufferedReader(new FileReader(repositoryFile));
		String currLine = "";
		while((currLine = bufferedReader2.readLine()) != null) {
			String[] repositoryElements = currLine.split("\\s+");
			String rootDirectoryName = repositoryElements[0] + (repositoryElements[0].endsWith(File.separator) ? "" : File.separator);
			repositoryPaths.add(rootDirectoryName);
			String[] repositoryArgs = new String[2];
			
			// repositoryArgs[0] holds the parent directory housing the repository
			repositoryArgs[0] = rootDirectoryName;
			
			// repositoryArgs[1] is the name of the repository
			repositoryArgs[1] = (repositoryElements.length == 1) ? rootDirectoryName : 
				repositoryElements[1] + (repositoryElements[1].endsWith(File.separator) ? "" : File.separator);
			
			try {
		        Collection<File> files = FileUtils.listFiles(
		                new File(rootDirectoryName),
		                new RegexFileFilter(".*.java"),
		                DirectoryFileFilter.DIRECTORY
		        );
		        List<String> directoryPathsToCompile = new ArrayList<>();
		        for(File file : files) {
		        	String absolutePath = file.getAbsolutePath();
		        	CompilationUnit compilationUnit = StaticJavaParser.parse(new File(absolutePath));
			        String packageName = "";
		            if(compilationUnit.getPackageDeclaration().isPresent())
		                packageName = compilationUnit.getPackageDeclaration().get().toString().replace("package ", "").replace(";","").trim();
		            String fullClassName = packageName.isEmpty() ? "" : packageName.replace(".", File.separator) + File.separator + file.getName();

		            String destinationPath = projectSourcePath + fullClassName;
		            File destinationFile = new File(destinationPath);
		            if(!destinationFile.exists()) {
		            	String packageParentPath = absolutePath.substring(0, absolutePath.indexOf(fullClassName));
		            	String firstLevelPackageDirectory = fullClassName.split(File.separator)[0] + File.separator;
		            	String firstLevelPackagePath = packageParentPath + firstLevelPackageDirectory;
		            	String destinationFirstLevelPackagePath = projectSourcePath + firstLevelPackageDirectory;
			            FileUtils.copyDirectory(new File(firstLevelPackagePath), new File(destinationFirstLevelPackagePath));
			            if(!directoryPathsToCompile.contains(destinationFirstLevelPackagePath))
			            	directoryPathsToCompile.add(destinationFirstLevelPackagePath);
		            }
		        	CompilationUnit destinationCompilationUnit = StaticJavaParser.parse(new File(destinationPath));
		            for(TypeDeclaration typeDec : destinationCompilationUnit.getTypes()) {
		            	ClassOrInterfaceDeclaration classDec = (ClassOrInterfaceDeclaration) typeDec;
		                NodeList<ClassOrInterfaceType> implemented = classDec.getImplementedTypes();
		                NodeList<ImportDeclaration> imports = destinationCompilationUnit.getImports();
		                boolean hasSerializableImport = false;
		                for(ImportDeclaration importDec : imports) {
		                	if(importDec.toString().equals("import java.io.Serializable")) {
		                		hasSerializableImport = true;
		                		break;
		                	}
		                }
		                if(!hasSerializableImport)
		                	destinationCompilationUnit.addImport("java.io.Serializable");
		                ClassOrInterfaceType serializableInterface = StaticJavaParser.parseClassOrInterfaceType("Serializable");
		                if(!implemented.contains(serializableInterface))
		                	implemented.add(StaticJavaParser.parseClassOrInterfaceType("Serializable"));		                
		            }
	        		FileWriter fileWriter = new FileWriter(destinationFile, false);
	        		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        		PrintWriter out = new PrintWriter(bufferedWriter);				
	        		out.println(destinationCompilationUnit.toString());
	        		out.flush();
	        		out.close();
	        		bufferedWriter.close();
	        		fileWriter.close();
		        }

		        String javacCommand = "javac -cp . ";
		        for(String directoryPath : directoryPathsToCompile) {
		        	for(File file : FileUtils.listFiles(new File(directoryPath), null, true)) {
			        	if(file.getName().contains(".class"))
			        		continue;		        		
			            javacCommand = javacCommand + file.getAbsolutePath() + " ";
		        	}
		        }
		        ProcessBuilder javacPB = new ProcessBuilder(Arrays.asList(javacCommand.split(" ")));
		        javacPB.inheritIO();
		        Process javacProcess = javacPB.start();
		        javacProcess.waitFor();

//				EvoSuiteDriver.main(repositoryArgs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		bufferedReader2.close();
	}
	
	public static void runTestCases(String sourceTestPath) {
		List<String> testFilePaths = getAllTestFilePaths(sourceTestPath, "");
		for(String testFilePath : testFilePaths) {
			try {
	        	String classpathProperty = System.getProperty("java.class.path");

	        	File testFile = new File(testFilePath);
//	        	String testCloneFilePath = getClonedTestFilePath(testFilePath);
//				File testCloneFile = new File(testCloneFilePath);
				
	            String repoPath = testFileToRepoMap.get(testFilePath);
	            String javacCommand = "javac -cp \".:" + repoPath + ":" + classpathProperty + 
	            		":hamcrest-core-1.3.jar:junit-4.13.jar:json-simple-1.1.1.jar:xstream-1.4.11.jar:"
	            		+ "xstream-1.4.11.1.jar:xpp3_min-1.1.4c.jar:evosuite-standalone-runtime-1.0.7-SNAPSHOT.jar\" ";
		        for (File newFile : FileUtils.listFiles(new File(sourceTestPath), null, true)) {
		        	if(newFile.getName().contains(".class"))
		        		continue;
		            javacCommand = javacCommand + newFile.getAbsolutePath() + " ";
		        }
		        System.out.println("Java command: " + javacCommand);
		        ProcessBuilder javacPB = new ProcessBuilder(Arrays.asList(javacCommand.split(" ")));
		        javacPB.inheritIO();
		        Process javacProcess = javacPB.start();
		        javacProcess.waitFor();
//				Process processComp = Runtime.getRuntime().exec("javac -cp \".:" + repoPath + ":" + classpathProperty + "\" " + testFilePath);
//				Runtime.getRuntime().exec("rm " + testCloneFilePath);
		        
		        System.out.println("Done compiling...................................................................");

//		        InputStream isComp = javacProcess.getErrorStream();
////		        InputStream isComp = processComp.getErrorStream();
//				BufferedReader brComp = new BufferedReader(new InputStreamReader(isComp));
//				String lineComp = "";
//				while((lineComp = brComp.readLine()) != null)
//					System.out.println(lineComp);
//				javacProcess.destroy();

//				Runtime.getRuntime().exec("touch " + testCloneFilePath);
				
//				ProcessBuilder pb0 = new ProcessBuilder("cat").redirectInput(testFile).redirectOutput(testCloneFile);
//	        	Process process0 = pb0.start();
//	        	process0.waitFor();
//				process0.destroy();

				CompilationUnit compilationUnit = StaticJavaParser.parse(new File(testFilePath));
				addMainMethod(testFile, compilationUnit);

    			Runtime.getRuntime().exec("rm " + testFilePath.replace(".java", ".class"));
		        ProcessBuilder javacPB2 = new ProcessBuilder(Arrays.asList(javacCommand.split(" ")));
		        javacPB2.inheritIO();
		        Process javacProcess2 = javacPB2.start();
		        javacProcess2.waitFor();
		        javacProcess2.destroy();
//    			Runtime.getRuntime().exec("javac -cp \".:" + repoPath + ":" + classpathProperty + "\" " + testFilePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		ProcessBuilder pb1 = new ProcessBuilder();
		pb1.command("java", "-cp", "target/CloneDetector-0.0.1-SNAPSHOT.jar", "edu.columbia.cs.psl.ioclones.analysis.PreAnalyzer", "-cb", sourceTestPath);
		pb1.directory(new File(cloneDetectorPath));
		try {
			Process process1 = pb1.start();
//			InputStream is = process1.getInputStream();
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			String line = "";
//			while((line = br.readLine()) != null)
//				System.out.println(line);
			
			for(String testFilePath : testFilePaths) {
				try {
		        	String classpathProperty = System.getProperty("java.class.path");

		        	File testFile = new File(testFilePath);
//		        	String testCloneFilePath = getClonedTestFilePath(testFilePath);
//					File testCloneFile = new File(testCloneFilePath);
					
					CompilationUnit compilationUnit = StaticJavaParser.parse(new File(testFilePath));
		            String packageName = "";
		            if (compilationUnit.getPackageDeclaration().isPresent())
		                packageName = (compilationUnit.getPackageDeclaration().get().toString().replace("package ", "").replace(";","").trim()).replace(".", File.separator);

					String testClassWithPackageWithoutExtension = packageName.replace(File.separator, ".") + "." + 
					testFile.getName().substring(0, testFile.getName().indexOf(".java"));
					pb1.command("java",
								"-javaagent:target/CloneDetector-0.0.1-SNAPSHOT.jar",
								"-noverify",
								"-cp",
								"target/CloneDetector-0.0.1-SNAPSHOT.jar:" + testFileToRepoMap.get(testFilePath),
								"edu.columbia.cs.psl.ioclones.driver.IODriver",
								"-class",
								testClassWithPackageWithoutExtension,
								"-cb",
								testFileToRepoMap.get(testFilePath));
					pb1.directory(new File(cloneDetectorPath));
//					pb1.redirectErrorStream(true);
			    	process1 = pb1.start();
//			        process1.waitFor(20, TimeUnit.SECONDS);
//			        process1.wait();
					InputStream is2 = process1.getInputStream();
					BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
					String line2 = "";

					FileWriter newFileWriter = new FileWriter("/Users/adityasridhar/Documents/COLUMBIA/SABER/IODriverLog.txt", true);
					BufferedWriter newBufferedWriter = new BufferedWriter(newFileWriter);
					PrintWriter newOut = new PrintWriter(newBufferedWriter);
					while((line2 = br2.readLine()) != null) {
//						System.out.println(line2);
						newOut.println(line2);
					}
					br2.close();
					is2.close();
					newBufferedWriter.close();
					newOut.close();

					process1.destroy();

		            String javacCommand = "javac -cp \".:" + sourceTestPath + ":" + classpathProperty + 
            		":hamcrest-core-1.3.jar:junit-4.13.jar:json-simple-1.1.1.jar:xstream-1.4.11.jar:"
            		+ "xstream-1.4.11.1.jar:xpp3_min-1.1.4c.jar:evosuite-standalone-runtime-1.0.7-SNAPSHOT.jar\" ";
			        for (File newFile : FileUtils.listFiles(new File(sourceTestPath), null, true)) {
			        	if(newFile.getName().contains(".class"))
			        		continue;
			            javacCommand = javacCommand + newFile.getAbsolutePath() + " ";
			        }
			        ProcessBuilder javacPB = new ProcessBuilder(Arrays.asList(javacCommand.split(" ")));
			        javacPB.inheritIO();
			        Process javacProcess = javacPB.start();
			        javacProcess.waitFor();
			        javacProcess.destroy();
//					Runtime.getRuntime().exec("javac -cp \".:" + sourceTestPath + ":" + classpathProperty + "\" " + testFilePath);
//					Runtime.getRuntime().exec("rm " + testCloneFilePath);
//					extractXMLFiles(sourceTestPath, destinationIORepoPath, testFilePath, packageName, testFile.getName());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private static void addMainMethod(File testFile, CompilationUnit compilationUnit) throws IOException {
		JavaParser javaParser = new JavaParser();
    	ParseResult<CompilationUnit> parsedResult = javaParser.parse(testFile);
		compilationUnit = parsedResult.getResult().get();
		String testMainClassName = testFile.getName().substring(0, testFile.getName().indexOf(".java"));
		Optional<ClassOrInterfaceDeclaration> optClassDec = compilationUnit.getClassByName(testMainClassName);
		ClassOrInterfaceDeclaration classDec = optClassDec.get();
		
		List<MethodDeclaration> methodDecs = classDec.getMethods();
		
		for(FieldDeclaration fd : classDec.getFields())
			fd.addModifier(Keyword.STATIC);
		for(MethodDeclaration md : classDec.getMethods())
			md.addModifier(Keyword.STATIC);

		List<MethodDeclaration> methodDecsForMain = classDec.getMethodsByName("main");
		MethodDeclaration methodDecMain = new MethodDeclaration();
		if(methodDecsForMain == null || methodDecsForMain.size() == 0) {
			methodDecMain = classDec.addMethod("main", Keyword.PUBLIC, Keyword.STATIC);
			methodDecMain.addParameter(String[].class, "args");
			methodDecMain.addThrownException(Throwable.class);
		}
		else {
			for(MethodDeclaration methodDecForMain : methodDecsForMain) {
				if(methodDecForMain.hasModifier(Keyword.PUBLIC)
						&& methodDecForMain.hasModifier(Keyword.STATIC)
						&& methodDecForMain.getType().equals(Type.VOID_TYPE)
						&& methodDecForMain.hasParametersOfType(String[].class)
						&& methodDecForMain.getParameters().size() == 1) {
					methodDecMain = methodDecForMain;
					methodDecMain.addThrownException(Throwable.class);
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
	}
	
	public static List<String> getAllTestFilePaths(String baseDirectory, String repoPath) {
		List<String> testFilePaths = new ArrayList<>();
		for(File file : new File(baseDirectory).listFiles()) {
			String filePath = file.toString();
			String fileName = file.getName();
			if(fileName.startsWith(TEST_FILE_PREFIX) && fileName.endsWith(".java")) {
				testFilePaths.add(filePath);
				testFileToRepoMap.put(filePath, repoPath + (repoPath.endsWith(File.separator) ? "" : File.separator));
			}
			else if(new File(filePath).isDirectory()) {
				String newRepoPath = repoPath;
				if(repoPath.equals(""))
					newRepoPath = filePath;
				testFilePaths.addAll(getAllTestFilePaths(filePath, newRepoPath));
			}
		}
		return testFilePaths;
	}
	
	public static String getClonedTestFilePath(String filePath) {
		String parentDirectoryPath = filePath.substring(0, filePath.indexOf(TEST_FILE_PREFIX));
		String clonedTestFilePath = parentDirectoryPath + CLONE_PREFIX + filePath.substring(filePath.indexOf(TEST_FILE_PREFIX));
		return clonedTestFilePath;
	}
	
	private static void extractXMLFiles(String sourceTestPath, String destinationIORepoPath) {
		List<String> testFilePaths = getAllTestFilePaths(sourceTestPath, "");
		for(String testFilePath : testFilePaths) {
        	File testFile = new File(testFilePath);
        	String testFileName = testFile.getName();
			
			CompilationUnit compilationUnit = null;
			try {
				compilationUnit = StaticJavaParser.parse(new File(testFilePath));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
            String packageName = "";
            if (compilationUnit.getPackageDeclaration().isPresent())
                packageName = (compilationUnit.getPackageDeclaration().get().toString().replace("package ", "").replace(";","").trim()).replace(".", File.separator);
            
    		String oldIORepoZipPath = oldIORepoPath + packageName.replace(File.separator, ".") + "." + testFileName.substring(0, testFileName.indexOf(".java")) + ".zip";
    		File intermediateIORepo = new File(destinationIORepoPath);
    		String destinationPath = destinationIORepoPath + testFilePath.substring(sourceTestPath.length(), testFilePath.indexOf(packageName + File.separator + testFileName)) + File.separator;
    		if(!intermediateIORepo.exists())
    			intermediateIORepo.mkdirs();

    		FileInputStream fileInputStream;
            byte[] buffer = new byte[1024];
            try {
                fileInputStream = new FileInputStream(oldIORepoZipPath);
                ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
                ZipEntry zipEntry = zipInputStream.getNextEntry();
                while(zipEntry != null) {
                    String filename = zipEntry.getName();
                    File file = new File(destinationPath + filename);
                    new File(file.getParent()).mkdirs();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0)
                    	fileOutputStream.write(buffer, 0, len);
                    fileOutputStream.close();
                    zipInputStream.closeEntry();
                    String[] filenameElements = zipEntry.getName().split(File.separator);
                    filenameElements[filenameElements.length - 1] = "NEW_" + new Random().nextInt(Integer.MAX_VALUE) + "_" + 
                    		new Date().getTime() + "_" + filenameElements[filenameElements.length - 1];
                    String newFilename  = String.join(File.separator, filenameElements);
                    System.out.println("Putting into IO record file to test file map: ");
                    System.out.println("\tKey: " + file.getAbsolutePath().replace(filename, newFilename).replace(sourceCodebasePath, ""));
                    System.out.println("\tValue: " + testFilePath.replace(sourceCodebasePath, ""));
                    if(sourceTestPath.equals(evosuiteTestPath))
                    	ioRecordFileToTestFileMap.put(file.getAbsolutePath().replace(filename, newFilename), testFilePath);
                    else if(sourceTestPath.equals(newTestPath))
                    	newIORecordFileToTestFileMap.put(file.getAbsolutePath().replace(filename, newFilename), testFilePath);
                    
                    File newFile = new File(destinationPath + newFilename);
    				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile, false));
    				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
    				
    				String line;
    				while((line = bufferedReader.readLine()) != null) {
    					if(line.contains("edu.columbia.cs.psl.ioclones.pojo.IORecord")) {
    						String newLine = line.replace("edu.columbia.cs.psl.ioclones.pojo.IORecord", IO_RECORD_FULL_CLASS_NAME);
    						bufferedWriter.append(newLine + "\n");
    					}
    					else if(line.contains("<dhComparator class=\"edu.columbia.cs.psl.ioclones.sim.AbstractSim$DHSComparator\"/>"))
    						continue;
    					else if(line.contains("edu.columbia.cs.psl.ioclones.pojo.XMLWrapper")) {
    						String newLine = line.replace("edu.columbia.cs.psl.ioclones.pojo.XMLWrapper", XML_WRAPPER_FULL_CLASS_NAME);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
	
	private static void createIORecords(XStream xstream, File destinationDirectory, boolean originalRecords) {
        for(File file : destinationDirectory.listFiles()) {
        	if(file.isDirectory())
        		createIORecords(xstream, file, originalRecords);
        	else if(!file.toString().contains("NEW_"))
        		continue;
        	else {
       			IORecord ioRecord = (IORecord) xstream.fromXML(file);
     		    if(originalRecords) {
        			ioRecord.stamp = new Random().nextInt(Integer.MAX_VALUE) + "_" + new Date().getTime();
        		    originalIORecords.add(ioRecord);
        		    stampToIORecordMap.put(ioRecord.stamp, ioRecord);
                    System.out.println("Checking if " + file.getAbsolutePath() + " exists: " + ioRecordFileToTestFileMap.containsKey(file.getAbsolutePath()));
        		    if(ioRecordFileToTestFileMap.containsKey(file.getAbsolutePath())) {
        		    	String testFilePath = ioRecordFileToTestFileMap.get(file.getAbsolutePath());
        		    	ioRecordToTestFileMap.put(ioRecord, testFilePath);
                        System.out.println("Putting into IO record to test file map: ");
                        System.out.println("\tKey: IO record with method key " + ioRecord.methodKey);
                        System.out.println("\tValue: " + testFilePath);
        		    }
        		    DatabaseUtils.addOriginalIORecord(connection, ioRecord);
     		    }
     		    else {
        		    newIORecords.add(ioRecord);
        		    if(newIORecordFileToTestFileMap.containsKey(file.getAbsolutePath())) {
        		    	String testFilePath = ioRecordFileToTestFileMap.get(file.getAbsolutePath());
        		    	newIORecordToTestFileMap.put(ioRecord, testFilePath);
        		    }
        		    DatabaseUtils.addNewIORecord(connection, ioRecord);
     		    }
        	}
        }
        
        try {
        	if(originalRecords) {
    			FileOutputStream fileOutputStream = new FileOutputStream(stampMapPath);
    			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    			objectOutputStream.writeObject(stampToIORecordMap);
    			objectOutputStream.close();
    			fileOutputStream.close();
	        }
	        else {
    			FileInputStream fileInputStream = new FileInputStream(stampMapPath);
    			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    			stampToIORecordMap = (HashMap) objectInputStream.readObject();
    			objectInputStream.close();
    			fileInputStream.close();	        	
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}        	
	}
	
	public static void performTypeConversion(XStream xstream) {
        System.out.println("Successfully created " + originalIORecords.size() + " original IO records!");

		for(int i = 0; i < originalIORecords.size(); i++) {
			IORecord ioRecord = originalIORecords.get(i);
			String methodKey = ioRecord.methodKey;
			if(!ioRecordOrganizersMap.containsKey(methodKey)) {
				IORecordOrganizer ioRecordOrganizer = new IORecordOrganizer();
				ioRecordOrganizer.methodKey = methodKey;
				ioRecordOrganizer.addSubjectIORecord(ioRecord);
				ioRecordOrganizersMap.put(methodKey, ioRecordOrganizer);
			}
			else
				ioRecordOrganizersMap.get(methodKey).addSubjectIORecord(ioRecord);
			System.out.println("Method key in IRO map: " + methodKey);
		}
		List<String> allMethodKeys = new ArrayList<>(ioRecordOrganizersMap.keySet());
		System.out.println("All method keys:");
		for(String x : allMethodKeys)
			System.out.println("\t" + x);
		List<String> otherMethodKeys = new ArrayList<>(allMethodKeys);
		otherMethodKeys.removeAll(userSubjectMethodKeys);
		System.out.println("Other method keys:");
		for(String x : otherMethodKeys)
			System.out.println("\t" + x);
		
		// Generate alpha IO records without outputs
		for(String userSubjectMethodKey : userSubjectMethodKeys) {
			System.out.println("User subject method key: " + userSubjectMethodKey);
			IORecordOrganizer ioRecordOrganizer = ioRecordOrganizersMap.get(userSubjectMethodKey);
			for(String otherMethodKey : otherMethodKeys) {
				IORecord otherIORecordTemplate = ioRecordOrganizersMap.get(otherMethodKey).subjectIORecords.get(0);
				for(IORecord subjectIORecord : ioRecordOrganizer.subjectIORecords) {
					TypeConversion typeConversion = new TypeConversion(otherIORecordTemplate, subjectIORecord);
					typeConversions.add(typeConversion);
					List<IORecord> alphaIORecords = typeConversion.getGeneratedRecordsPair().b;
					if(!generatedIORecordMap.containsKey(otherIORecordTemplate))
						generatedIORecordMap.put(otherIORecordTemplate, new HashSet<>());
					generatedIORecordMap.get(otherIORecordTemplate).addAll(alphaIORecords);
				}
			}
		}
		
		// Generate beta IO records without outputs
		for(String userSubjectMethodKey : userSubjectMethodKeys) {
			IORecordOrganizer ioRecordOrganizer = ioRecordOrganizersMap.get(userSubjectMethodKey);
			IORecord subjectIORecordTemplate = ioRecordOrganizer.subjectIORecords.get(0);
			for(String otherMethodKey : otherMethodKeys) {
				for(IORecord otherIORecord : ioRecordOrganizersMap.get(otherMethodKey).subjectIORecords) {
					TypeConversion typeConversion = new TypeConversion(subjectIORecordTemplate, otherIORecord);
					List<IORecord> betaIORecords = typeConversion.getGeneratedRecordsPair().b;
					if(!generatedIORecordMap.containsKey(subjectIORecordTemplate))
						generatedIORecordMap.put(subjectIORecordTemplate, new HashSet<>());
					generatedIORecordMap.get(subjectIORecordTemplate).addAll(betaIORecords);
				}
			}
		}
		
		System.out.println();
		System.out.println("Generated IO Record map: ");
		for(IORecord ioRecord : generatedIORecordMap.keySet()) {
			System.out.println("\tIO Record key: " + ioRecord.methodKey + "  " + ioRecord.id + "  " + ioRecord.stamp);
			for(IORecord nestedIORecord : generatedIORecordMap.get(ioRecord)) {
				System.out.println("\t\tIO Record Value: " + nestedIORecord.methodKey + "  " + nestedIORecord.id + "  " + nestedIORecord.stamp);
			}
		}
		
        try {
			FileOutputStream fileOutputStream = new FileOutputStream(ioRecordOrganizersMapPath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(ioRecordOrganizersMap);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void generateNewTestCases(XStream xstream) {
		// Create a file to store new IO records (without outputs) one by one
		try {
			FileUtils.copyDirectory(new File(evosuiteTestPath), new File(newTestPath));
						
			CompilationUnit compilationUnit = new CompilationUnit(); 
			BufferedWriter bufferedWriter;
						
			System.out.println("Number of original IO records: " + generatedIORecordMap.keySet().size());
			
			List<String> testFilesSeen = new ArrayList<>();
			for(IORecord originalIORecord : generatedIORecordMap.keySet()) {
				if(originalIORecord.methodKey.contains("ESTest_scaffolding"))
					continue;
				System.out.println("Original IO record: " + originalIORecord.methodKey);
				System.out.println("IO record to test file map has original IO record: " + ioRecordToTestFileMap.containsKey(originalIORecord));
				String testFilePath = ioRecordToTestFileMap.get(originalIORecord);

				String packageName = "";
				File testFile = new File(testFilePath);
				String newTestFilePath = testFilePath.replace(EVOSUITE_TESTS_DIRECTORY, NEW_TESTS_DIRECTORY);
				File newTestFile = new File(newTestFilePath);
				System.out.println("Test file path: " + testFilePath);
				System.out.println("New test file path: " + newTestFilePath);
				compilationUnit = StaticJavaParser.parse(newTestFile);
				compilationUnit.addImport("xml.XMLParser");
	            if (compilationUnit.getPackageDeclaration().isPresent())
	                packageName = compilationUnit.getPackageDeclaration().get().toString().replace("package ", "").replace(";","").replace(".", File.separator).trim();
	            String testClass = packageName.isEmpty() ? newTestFile.getName() : packageName + File.separator + newTestFile.getName();
	            String sourceDirectoryFullPath = newTestFilePath.substring(0, newTestFilePath.indexOf(testClass));
	            
	            File newTestsSourceXMLDirectory = new File(sourceDirectoryFullPath + "xml");
	            File newTestsSourcePojoDirectory = new File(sourceDirectoryFullPath + "pojo");
	            System.out.println("XML directory injected into: " + newTestsSourceXMLDirectory.getAbsolutePath());
	            System.out.println("Pojo directory injected into: " + newTestsSourcePojoDirectory.getAbsolutePath());
	            if(!newTestsSourceXMLDirectory.exists()) {
	    			// Move pojo and xml to Temporary Repo
	    	        FileUtils.copyDirectory(new File(projectSourcePath + "xml"), newTestsSourceXMLDirectory);
	            }
	            if(!newTestsSourcePojoDirectory.exists()) {
	    			// Move pojo and xml to Temporary Repo
	    	        FileUtils.copyDirectory(new File(projectSourcePath + "pojo"), newTestsSourcePojoDirectory);
	            }
	            	            
				String testMainClassName = newTestFile.getName().substring(0, newTestFile.getName().indexOf(".java"));
				Optional<ClassOrInterfaceDeclaration> optClassDec = compilationUnit.getClassByName(testMainClassName);
				ClassOrInterfaceDeclaration classDec = optClassDec.get();
				List<MethodDeclaration> methodDecs = classDec.getMethods();
				
				List<MethodDeclaration> methodDecsForMain = classDec.getMethodsByName("main");
				MethodDeclaration methodDecMain = new MethodDeclaration();
				BlockStmt blockStmtMain = new BlockStmt();

				if(!testFilesSeen.contains(newTestFile.getAbsolutePath())) {
					for(MethodDeclaration methodDecForMain : methodDecsForMain) {
						if(methodDecForMain.hasModifier(Keyword.PUBLIC)
								&& methodDecForMain.hasModifier(Keyword.STATIC)
								&& methodDecForMain.hasParametersOfType(String[].class)) {
							System.out.println("Main method found");
							boolean removed = classDec.remove(methodDecForMain);
							System.out.println("Main method removed? " + removed);
							System.out.println("Main method deleted");
							break;
						}
					}
					methodDecMain = classDec.addMethod("main", Keyword.PUBLIC, Keyword.STATIC);
					methodDecMain.addParameter(String[].class, "args");
					methodDecMain.addThrownException(Throwable.class);
					System.out.println("Main method is born again");

					testFilesSeen.add(newTestFile.getAbsolutePath());					
				}
				else {
					for(MethodDeclaration methodDecForMain : methodDecsForMain) {
						if(methodDecForMain.hasModifier(Keyword.PUBLIC)
								&& methodDecForMain.hasModifier(Keyword.STATIC)
								&& methodDecForMain.hasParametersOfType(String[].class)) {
							methodDecMain = methodDecForMain;
							break;
						}
					}
					blockStmtMain = methodDecMain.getBody().get();					
				}				
				
				for(IORecord newIORecordWithoutOutput : generatedIORecordMap.get(originalIORecord)) {					
					String modifiedXMLFileDumpPath = xmlFileDumpPath.replace(".xml", "_" + newIORecordWithoutOutput.stamp + ".xml");
					System.out.println("XML file dump path: " + modifiedXMLFileDumpPath);
					Runtime.getRuntime().exec("touch " + modifiedXMLFileDumpPath);
					bufferedWriter = new BufferedWriter(new FileWriter(modifiedXMLFileDumpPath, false));
					bufferedWriter.write(xstream.toXML(newIORecordWithoutOutput));
					bufferedWriter.flush();
					bufferedWriter.close();
					MethodDeclaration newMethodDec = JavaSymbolSolverDriver.run(originalIORecord, newIORecordWithoutOutput, modifiedXMLFileDumpPath);
					classDec.addMember(newMethodDec);
					try {
						MethodCallExpr methodCallExpr = new MethodCallExpr(newMethodDec.getNameAsString());
						blockStmtMain.addStatement(methodCallExpr);
					} catch(Exception e) {
						System.err.println("Error: " + e);
					}
				}
				methodDecMain.setBody(blockStmtMain);
				
				FileWriter fileWriter = new FileWriter(newTestFile, false);
				bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter out = new PrintWriter(bufferedWriter);				
				out.println(compilationUnit.toString());
				out.flush();
				out.close();
				bufferedWriter.close();
				fileWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void analyze() {
		try {
			FileInputStream fileInputStream = new FileInputStream(ioRecordOrganizersMapPath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			ioRecordOrganizersMap = (HashMap) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<String> allMethodKeys = new ArrayList<>(ioRecordOrganizersMap.keySet());
		List<String> otherMethodKeys = new ArrayList<>(allMethodKeys);
		otherMethodKeys.removeAll(userSubjectMethodKeys);
		
		// Organize alpha IO records with outputs
//		System.out.println("Number of method keys: " + allMethodKeys.size());
//		System.out.println("Number of other method keys: " + otherMethodKeys.size());
//		System.out.println("Number of IO records: " + newIORecords.size());
		for(IORecord newIORecord : newIORecords) {
			String newMethodKey = newIORecord.methodKey;
			if(newMethodKey.startsWith("xml.XMLParser") || 
					newMethodKey.startsWith("xml.BConverter") ||
					newMethodKey.startsWith("xml.EMConverter"))
				continue;			
			
//			System.out.println("New IO record: " + newIORecord);
//			System.out.println("\tMethod key: " + newMethodKey);
//			System.out.println("\tStack info: " + newIORecord.stackInfo);
//			System.out.println("\tStack info elements: " + newIORecord.stackInfo.split(":"));

			
			String[] stackInfoElements = newIORecord.stackInfo.split(":");
	        if(stackInfoElements.length < 3)
	        	continue;
	        String testCaseName = stackInfoElements[stackInfoElements.length - 2];
	        if(testCaseName.indexOf("SABER") < 0)
	        	continue;

	        String[] testCaseNameElements = testCaseName.substring(testCaseName.indexOf("SABER")).split("_");
	        if(testCaseNameElements.length < 4)
	        	continue;
	        
//	        System.out.println();
//	        System.out.println("New IO record method key: " + newMethodKey); 
//	        System.out.println("User subject method keys: " + userSubjectMethodKeys);
//	        System.out.println("Other method keys: " + otherMethodKeys);
	        
	        String expectedMethodName = testCaseNameElements[testCaseNameElements.length - 2];
	        String actualMethodName = newMethodKey.substring(newMethodKey.indexOf("-") + 1, newMethodKey.indexOf("("));
	        
//	        System.out.println("Expected method name: " + expectedMethodName);
//	        System.out.println("Actual method name: " + actualMethodName);
//	        System.out.println();
	        
	        if(!expectedMethodName.equals(actualMethodName))
	        	continue;
	        
	        String stamp = testCaseNameElements[testCaseNameElements.length - 4] + "_" +
	        		testCaseNameElements[testCaseNameElements.length - 3];
	        	        
	        if(!stampToIORecordMap.containsKey(stamp))
	        	continue;
	        
	        IORecord originalIORecordWithStamp = stampToIORecordMap.get(stamp);
	        String originalMethodKey = originalIORecordWithStamp.methodKey;

//	        System.out.println();
//	        System.out.println("Original IO record with stamp: " + originalIORecordWithStamp); 
//	        System.out.println("Original IO record method key: " + originalMethodKey); 
//	        System.out.println("New IO record method key: " + newMethodKey); 
//	        System.out.println("User subject method keys: " + userSubjectMethodKeys);
//	        System.out.println("Other method keys: " + otherMethodKeys);
//	        System.out.println();
	        
	        if(userSubjectMethodKeys.contains(originalMethodKey) &&	otherMethodKeys.contains(newMethodKey)) {
	        	IORecordOrganizer ioRecordOrganizer = ioRecordOrganizersMap.get(originalMethodKey);
	        	List<IORecord> alphaIORecords = new ArrayList<>();
	        	alphaIORecords.add(newIORecord);
				ioRecordOrganizer.addAlphaIORecords(originalIORecordWithStamp, alphaIORecords, newMethodKey);
				ioRecordOrganizersMap.put(originalMethodKey, ioRecordOrganizer);
	        }
	        else if(userSubjectMethodKeys.contains(newMethodKey) &&	otherMethodKeys.contains(originalMethodKey)) {
	        	IORecordOrganizer ioRecordOrganizer = ioRecordOrganizersMap.get(newMethodKey);
	        	List<IORecord> betaIORecords = new ArrayList<>();
	        	betaIORecords.add(newIORecord);
				ioRecordOrganizer.addBetaIORecords(originalIORecordWithStamp, betaIORecords, originalMethodKey);
				ioRecordOrganizersMap.put(newMethodKey, ioRecordOrganizer);
	        }
		}
		
		// Perform similarity analysis
		SimilarityAnalyzer similarityAnalyzer = new SimilarityAnalyzer(ioRecordOrganizersMap);		
		System.out.println("IO record organizers map:");
		for(String methodKey : ioRecordOrganizersMap.keySet()) {
			System.out.println("\tMethod key: " + methodKey);
			System.out.println("\tIO record organizer:");
			IORecordOrganizer ioRecordOrganizer = ioRecordOrganizersMap.get(methodKey);
			System.out.println();
			System.out.println(ioRecordOrganizer.printOrganizer());
			System.out.println();
		}
		similarityAnalyzer.analyze();
		Map<String, List<Double>> alphaIORecordSimilaritiesMap = similarityAnalyzer.getAlphaIORecordSimilaritiesMap();
		Map<String, List<Double>> betaIORecordSimilaritiesMap = similarityAnalyzer.getBetaIORecordSimilaritiesMap();
		Map<String, Double> methodSimilarities = similarityAnalyzer.getMethodSimilarities();
		System.out.println("Method similarities:");
		for(String methodSimilarityKey : methodSimilarities.keySet()) {
			System.out.println("\tNew method similarity");
			System.out.println("\t\tMethods: " + methodSimilarityKey);
			System.out.println("\t\tAlpha Similarities: " + alphaIORecordSimilaritiesMap.get(methodSimilarityKey));
			System.out.println("\t\tBeta Similarities: " + betaIORecordSimilaritiesMap.get(methodSimilarityKey));
			System.out.println("\t\tSimilarity: " + methodSimilarities.get(methodSimilarityKey));
		}
		
		// Perform complexity analysis
		ComplexityAnalyzer complexityAnalyzer = new ComplexityAnalyzer(otherMethodKeys);
		complexityAnalyzer.analyzeMethods();
		Map<String, Integer> methodKeyToComplexityMap = complexityAnalyzer.getMethodKeyToComplexityMap();
		
		// Update the database
		System.out.println();
		System.out.println("Results");
		System.out.println();
		for(String methodSimilarityKey : methodSimilarities.keySet()) {
			String subjectMethodKey = methodSimilarityKey.split(SimilarityAnalyzer.DELIMITER)[0];
			String otherMethodKey = methodSimilarityKey.split(SimilarityAnalyzer.DELIMITER)[1];
			double ioSimilarity = methodSimilarities.get(methodSimilarityKey);
			int complexity = methodKeyToComplexityMap.get(otherMethodKey);
			System.out.println("Method key 1: " + subjectMethodKey);
			System.out.println("Method key 2: " + otherMethodKey);
			System.out.println("Similarity: " + ioSimilarity);
			System.out.println("Complexity: " + complexity);
			DatabaseUtils.addMethodSimilarity(connection,
											  subjectMethodKey,
											  otherMethodKey,
											  ioSimilarity,
											  0.0,
											  0.0,
											  complexity,
											  useCase);
		}
	}
	
	private static void clean() {
//		FileUtils.deleteDirectory(intermediateIORepoPath);
	}
}