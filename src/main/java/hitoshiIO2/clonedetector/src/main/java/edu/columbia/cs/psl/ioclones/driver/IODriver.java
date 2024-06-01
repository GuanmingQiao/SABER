package edu.columbia.cs.psl.ioclones.driver;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.runner.JUnitCore; // used for evosuite integration

import edu.columbia.cs.psl.ioclones.config.IOCloneConfig;
import edu.columbia.cs.psl.ioclones.utils.GlobalInfoRecorder;
import edu.columbia.cs.psl.ioclones.utils.IOUtils;
import edu.columbia.cs.psl.ioclones.utils.ShutdownLogger;

public class IODriver {

    private static final Logger logger = LogManager.getLogger(IODriver.class);

    public static final String iorepoDir = "iorepo";

    public static final String profileDir = "classinfo";

    public static final String evosuiteCommandTemplate = "java -jar target/dependency/evosuite-master-1.0.6.jar -class %s -projectCP %s -Dtarget_method=\"%s\" -criterion method";

//     public static final String evosuitePreAnalyzerCommand = "java -cp target/CloneDetector-0.0.1-SNAPSHOT.jar edu.columbia.cs.psl.ioclones.analysis.PreAnalyzer -cb evosuite-tests";

    private static Options options = new Options();

    static {
        options.addOption("class", true, "Class to be instrumented");
        options.addOption("cb", true, "Path to your bytecodebase");
        options.addOption("classArgs", true, "Optional command-line arguments to be passed to the class, if entering from the main method");
        options.addOption("targetMethod", true, "Optional target method to generate tests for and execute");
//         options.addOption("targetMethodList", true, "Optional list of target methods, separated by colons, to generate tests for and execute");

        options.getOption("class").setRequired(true);
        options.getOption("cb").setRequired(true);
        options.getOption("classArgs").setArgs(Option.UNLIMITED_VALUES);
    }

    private static String parseClassName(String className) {
        int lastDot = className.lastIndexOf(".");

        if(lastDot == -1) {
            return className; 
        }

        return className.substring(lastDot + 1);
    }

    public static void main(String args[]) {
        IOCloneConfig config = IOCloneConfig.getInstance();
        logger.info("Configuration: ");
        logger.info(config);

        logger.info("Loading class info");
        //IOUtils.unzipClassInfo();
        IOUtils.loadMethodIODeps("cb");

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            String className = cmd.getOptionValue("class");
            String[] newArgs = {};

            if (cmd.hasOption("classArgs"))
                newArgs = cmd.getOptionValues("classArgs");

            File iorepo = new File(iorepoDir);
            if (!iorepo.exists()) {
                iorepo.mkdir();
            }

            // add codebase to classpath
            String bytecodebasePath = cmd.getOptionValue("cb");
            logger.info("Bytecodebase path: " + bytecodebasePath);
            File bytecodebaseDirectory = new File(bytecodebasePath);
            URL bytecodebaseUrl = bytecodebaseDirectory.toURL();
            System.out.println("Bytecodebase: " + bytecodebasePath);
            System.out.println("Class: " + className);
//            URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
//            Class urlClass = URLClassLoader.class;
//            Method addUrlMethod = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
//            addUrlMethod.setAccessible(true);
//            addUrlMethod.invoke(urlClassLoader, new Object[]{bytecodebaseUrl});

            System.out.println("Loading the following class in IODriver: " + className);
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Class targetClass = classLoader.loadClass(className);
//            Class targetClass = urlClassLoader.loadClass(className);

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    ShutdownLogger.appendMessage("Reporting IOs");
                    try {
                        int recordCount = GlobalInfoRecorder.getRecordCounter();
                        int exactCount = GlobalInfoRecorder.reportIOs(iorepo.getCanonicalPath(), targetClass.getName());

                        ShutdownLogger.appendMessage("Record count: " + recordCount);
                        ShutdownLogger.appendMessage("Exact count: " + exactCount);
                    } catch (Exception ex) {
                        ShutdownLogger.appendException(ex);
                    }
                    ShutdownLogger.appendMessage("Finish io clone reporting");
                    ShutdownLogger.finalFlush();
                }
            });

            // execute either main method with args, or generate evosuite tests, 
            if (!cmd.hasOption("targetMethod")) {
                // execute class from main method
                logger.info("Executing main method for " + className);

                Method mainMethod = targetClass.getMethod("main", String[].class);
                mainMethod.setAccessible(true);
                mainMethod.invoke(null, (Object)newArgs);
            }
            else {
                if (!cmd.hasOption("cb")) {
                    logger.error("Could not find -cb option; please include path to bytecodebase. Exiting now...");
                    System.exit(-1);
                }

                // find all target methods that satisfy method name
                String targetMethod = cmd.getOptionValue("targetMethod");
                logger.info("Executing target method " + targetMethod + " for " + className);

                List<String> methodKeys = IOUtils.getMethodKeys("cb", targetMethod, className);
                if (methodKeys.isEmpty()) {
                    logger.error("Unable to find your target methods. Exiting...");
                    System.exit(-1);
                }

                // generate tests for target methods via evosuite
                String evosuiteCommand = String.format(evosuiteCommandTemplate, className, bytecodebasePath, String.join(":", methodKeys));
                logger.info("Evosuite test generation command: \"" + evosuiteCommand + "\"");
                ProcessBuilder evosuitePB = new ProcessBuilder(Arrays.asList(evosuiteCommand.split(" ")));
                evosuitePB.inheritIO();
                Process evosuiteProcess = evosuitePB.start();
                evosuiteProcess.waitFor();

                // build test directory filepath
                String evosuiteTestDir = "evosuite-tests/" + targetClass.getPackage().toString().substring("package ".length()).replace(".", "/");
                if (evosuiteTestDir.charAt(evosuiteTestDir.length() - 1) != '/') {
                    evosuiteTestDir = evosuiteTestDir + "/";
                }

                // get all .java files to link and compile
                File[] evosuiteTests = new File(evosuiteTestDir.substring(0, evosuiteTestDir.length() - 1)).listFiles();
                List<String> evosuiteTestNames = new ArrayList<String>();

                String parsedClassName = parseClassName(className);
                logger.warn("Looking for tests for class name: \"" + parsedClassName + "\"");
                for (File f:evosuiteTests) {
                    if (!f.isFile())
                        continue;

                    String fName = f.getName();
                    logger.warn("File found: \"" + fName + "\"");
                    if (fName.equals(parsedClassName + "_ESTest.java") || fName.equals(parsedClassName + "_ESTest_scaffolding.java"))
                        evosuiteTestNames.add(evosuiteTestDir + fName);
                }

                // compile tests
                String evosuiteCompileCommand = "javac " + String.join(" ", evosuiteTestNames);
                ProcessBuilder evosuiteCompilePB = new ProcessBuilder(Arrays.asList(evosuiteCompileCommand.split(" ")));
                evosuiteCompilePB.inheritIO();

                // add classpath
                Map<String, String> env = evosuiteCompilePB.environment();

                if (env.get("CLASSPATH") == null)
                    env.put("CLASSPATH", "");
                else
                    env.put("CLASSPATH", env.get("CLASSPATH") + ":");

                env.put("CLASSPATH", env.get("CLASSPATH") +
                                     "target/classes:" +
                                     "target/dependency/evosuite-standalone-runtime-1.0.6.jar:" +
                                     "evosuite-tests:" +
                                     "target/dependency/junit-4.11.jar:" +
                                     "target/dependency/hamcrest-core-1.3.jar:" +
                                     bytecodebasePath + ":" +
                                     System.getProperty("java.class.path"));

                logger.warn("CLASSPATH for Evosuite test compilation is: \"" + env.get("CLASSPATH") + "\"");

                logger.info("Evosuite test compile command: \"" + evosuiteCompileCommand + "\"");
                Process evosuiteCompileProcess = evosuiteCompilePB.start();
                evosuiteCompileProcess.waitFor();

//                 // run preanalyzer on evosuite tests
//                 logger.info("Evosuite test preanalyze command: \"" + evosuitePreAnalyzerCommand + "\"");
//                 ProcessBuilder evosuitePreAnalyzerPB = new ProcessBuilder(Arrays.asList(evosuitePreAnalyzerCommand.split(" ")));
//                 evosuitePreAnalyzerPB.inheritIO();
//                 Process evosuitePreAnalyzerProcess = evosuitePreAnalyzerPB.start();
//                 evosuitePreAnalyzerProcess.waitFor();

                // add evosuite test directory to classpath
                File evosuiteDirectory = new File("./evosuite-tests");
                URL evosuiteDirectoryURL = evosuiteDirectory.toURL();
//                addUrlMethod.invoke(urlClassLoader, new Object[]{evosuiteDirectoryURL});

                // invoke evosuite tests
                JUnitCore jc = new JUnitCore();
                String testClassName = className + "_ESTest";
                logger.info("Invoking JUnitCore for test class: \"" + testClassName + "\"");
                Class testClass = Class.forName(testClassName); // test class or actual class?
                jc.run(testClass);

                // clean up evosuite artifacts
//                 String evosuiteCleanupCommand = "rm -rf " + evosuiteTestDir.substring(0, evosuiteTestDir.length() - 1);
//                 logger.info("Evosuite test cleanup command: \"" + evosuiteCleanupCommand + "\"");
//                 ProcessBuilder evosuiteCleanupPB = new ProcessBuilder(Arrays.asList(evosuiteCleanupCommand.split(" ")));
//                 evosuiteCleanupPB.inheritIO();
//                 Process evosuiteCleanupProcess = evosuiteCleanupPB.start();
//                 evosuiteCleanupProcess.waitFor();
            }

            logger.info("IODriver completed successfully.");
        } catch (Exception ex) {
            logger.error("Error: ", ex);
        }
    }
}