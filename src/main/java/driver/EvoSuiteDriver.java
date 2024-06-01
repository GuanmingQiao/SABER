package driver;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.google.common.base.Strings;

import main.Main;

import org.apache.commons.cli.Options;
import randoop.org.apache.commons.io.FileUtils;
import randoop.org.apache.commons.io.filefilter.DirectoryFileFilter;
import randoop.org.apache.commons.io.filefilter.RegexFileFilter;
import utils.SourceParserFileUtils;
import visitor.JavaParserMethodVisitor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;

public class EvoSuiteDriver {
    static final String evosuiteCommandTemplate = "java -jar %s -class %s -projectCP %s -Dsearch_budget 30";
    private static Options options = new Options();
    /*
     * Generate and compile EvoSuite tests, return test name for JUnitCore runner
     */
    public static void generateEvoSuiteTests(String rootDir, String repoDir, String absoluteCodePath, String relativeCodePath, String fileName) throws Exception {
        try {
            // Guan's 1st stage
            // For each source code file:
            String outputRelativePath = Main.EVOSUITE_TESTS_DIRECTORY + repoDir.replace(".", "").concat(relativeCodePath.replace(fileName, ""));
            
            // - Use JavaParser to get package names
            CompilationUnit cu = StaticJavaParser.parse(new File(absoluteCodePath));
            String packageName = "";
            if (cu.getPackageDeclaration().isPresent()) {
                packageName = cu.getPackageDeclaration().get().toString().replace("package ", "").replace(";","").trim();
            }
            String testClass = packageName.isEmpty() ? fileName.replace(".java", "") : packageName + "." + fileName.replace(".java", "");

            // - use JavaParser to add "implement Serializable" to each class
            cu.getTypes().forEach(classDec -> classDec.accept(new JavaParserMethodVisitor.ClassDeclarationVisitor(), null));
            NodeList<ImportDeclaration> imports = cu.getImports();
            boolean hasSerializableImport = false;
            for(ImportDeclaration importDec : imports) {
            	if(importDec.toString().equals("import java.io.Serializable")) {
            		hasSerializableImport = true;
            		break;
            	}
            }
            if(!hasSerializableImport)
            	cu.addImport("java.io.Serializable");

            // - run EvoSuite with testClass = "package.class"
            // generate unit tests for target class via Randoop
            String evosuite_jar = "./target/dependency/evosuite-master-1.0.7-SNAPSHOT.jar";
            String sourcePath = absoluteCodePath.replace(testClass.replace(".", File.separator) + ".java", "");  
            System.out.println("Test class: " + testClass);
            String evosuiteCommand = String.format(
            		evosuiteCommandTemplate,
                    evosuite_jar,
                    testClass,
                    sourcePath
            );

            // - move the test file to the same dir in "temp_codebase". Use package_className as test file name
            System.out.println("Evosuite test generation command: \"" + evosuiteCommand + "\"");

            ProcessBuilder evosuitePB = new ProcessBuilder(Arrays.asList(evosuiteCommand.split(" ")));
            evosuitePB.inheritIO();
            Process evosuiteProcess = evosuitePB.start();
            evosuiteProcess.waitFor();
            
            SourceParserFileUtils.create_dir(outputRelativePath);
            
            // - write the modified source code file to its equivalent dir in "temp_codebase/"
            SourceParserFileUtils.write_normal(outputRelativePath, fileName, cu.toString());
            
            String EvosuiteOutputPrefix = fileName.replace(".java", "");
            String testFileName = escapeString(Main.TEST_FILE_PREFIX + fileName.replace(".java", "")) + ".java";
            String sourceFile = String.format("./evosuite-tests/%s%s", 
            										relativeCodePath.replace(fileName, ""), 
            										String.format("%s_ESTest.java", EvosuiteOutputPrefix));
            String targetFile = String.format("%s%s", outputRelativePath, testFileName);
            
            FileUtils.copyFile(new File(sourceFile), new File(targetFile));

            sourceFile = String.format("./evosuite-tests/%s%s", 
					relativeCodePath.replace(fileName, ""), 
					String.format("%s_ESTest_scaffolding.java", EvosuiteOutputPrefix));
            targetFile = String.format("%s%s", outputRelativePath, String.format("%s_ESTest_scaffolding.java", EvosuiteOutputPrefix));
            FileUtils.copyFile(new File(sourceFile), new File(targetFile));
            
            ProcessBuilder classFileCleaner = new ProcessBuilder("/bin/sh", "-c", "find *.class -print0 | xargs -0 rm");
            classFileCleaner.inheritIO();
            Process classFileCleanerProcess = classFileCleaner.start();
            classFileCleanerProcess.waitFor();
            
//            ProcessBuilder deleteDirCleaner = new ProcessBuilder("rm -rf ./evosuite-tests ./evosuite-report".split(" "));
//            deleteDirCleaner.inheritIO();
//            Process deleteDirCleanerProcess = deleteDirCleaner.start();
//            deleteDirCleanerProcess.waitFor();

            // - use Javaparser to import Utility Files to test file
            CompilationUnit testCu = StaticJavaParser.parse(new File(outputRelativePath + "/" + testFileName));
            testCu.setPackageDeclaration(StaticJavaParser.parsePackageDeclaration("package " + packageName + ";"));
            testCu.getTypes().forEach(classDec -> classDec.setName(StaticJavaParser.parseSimpleName(testFileName.replace(".java", ""))));
            SourceParserFileUtils.write_normal(outputRelativePath, testFileName, testCu.toString());

            if (!packageName.isEmpty()) {
                // Import Helpers with correct package
                cu = StaticJavaParser.parse(new File( "src/main/java/utils/Reporter.java"));
                cu.setPackageDeclaration(StaticJavaParser.parsePackageDeclaration("package " + packageName + ";"));
                SourceParserFileUtils.write_normal(outputRelativePath, "Reporter.java", cu.toString());
            }

            // Aditya's 2nd stage
            // For each test code file:
            // - use JavaParser to add main method to given test class
            // - use HitoshiIO to execute individual tests (probably have to manually specify -cp ALL_FILES:jUnit.jar for HitoshiIO).
            // - generate more IO Records per test case per method.
            // - use permutation to pair up original IO with new IO. Structure them like "package.TestFile.TestCaseName.MethodName/0"

            // Guan's 3rd stage
            // For each pair of IORecord
            // - use JavaParser to add xmlParser statements to its corresponding package.TestFile.TestCaseName, initializing new values
            // - use JavaParser to add monitoring statements to the testCase, recording runtime value of original variables
            // - replace old values with new values, thus creating one new testCase per IORecord
            // - put new testCases + old ones into a GIANT new test file (with correct package)
        } catch (Exception ex) {
            throw new Exception("EvoSuite error: ", ex);
        }
    }

    private static String escapeString(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '.' || c == '(' || c == ')' || c == '[' || c == ']') {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String formatRegex(String s) {
        String result = "^(";
        String[] str = s.split(":");
        for (int i = 0; i < str.length; i++) {
            if (i > 0) {
                result += "|" + escapeString(str[i]);
            } else {
                result += escapeString(str[i]);
            }
        }
        result += ")$";
        return result;
    }

    public static void main(String args[]) throws Exception {
        try {
            String rootDir = args[0];
            String repoDir = args[1];

            // Generate tests for all classes
            Collection<File> files = FileUtils.listFiles(
                    new File(rootDir),
                    new RegexFileFilter(".*.java"),
                    DirectoryFileFilter.DIRECTORY
            );

            String javacCommand = String.format("javac -cp json-simple-1.1.1.jar:xstream-1.4.11.jar:xstream-1.4.11.1.jar:xpp3_min-1.1.4c.jar:evosuite-standalone-runtime-1.0.7-SNAPSHOT.jar:%s ", rootDir);
            for (File name : files) {
                if (name.getName().contains(".class")) {
                    continue;
                }
                javacCommand = javacCommand + name.getPath() + " ";
            }
            
            // Compile all files
            ProcessBuilder javacPB = new ProcessBuilder(Arrays.asList(javacCommand.split(" ")));
            javacPB.inheritIO();
            Process javacProcess = javacPB.start();
            javacProcess.waitFor();

            for (File f : files) {
                String relativePath = new File(rootDir).toURI().relativize(f.toURI()).getPath();
                EvoSuiteDriver.generateEvoSuiteTests(rootDir, repoDir, f.getAbsolutePath(), relativePath, f.getName());
            }

            // - Move all monitoring/IORecord files to a top level directory inside "evosuite_tests/root_dir"

            System.out.println("IODriver completed successfully.");
        } catch (Exception ex) {
            throw ex;
        }
    }
}