# SABER: SimilAr BEhaviorR

The old SABER repository has been renamed to SABER-project-students.

### Phase 1: Initial Randoop Test Generation
Run `src/main/java/driver/RandoopDriver.java`.
* The driver will generate JUnit tests for every file under the root directory and place the following files under their original package path in `randoop_tests`, which is found in the top-most (project-level) directory:
    * Original file (`${CLASS}.java`)
    * Randoop test file (named `Test${CLASS}.java`)
    * XML parsing files (`XMLParser.java`, `BConverter.java`, `EMConverter.java`, and `IORecord.java` from `./source_codebase/saberUtil_repo`)
    * Runtime monitoring helpers (`Reporter.java`) <br>

All of the helper files are parsed by JavaParser so that they have the same (correct) package and import information as the test file.

### Phase 2: Run the Randoop Tests and Generate New Inputs
* Run JavaParser to generate main classes for each test file. <br>
* Compile, with all files under `rootDir` in the class path (also with `JUnit` jar files), the `./source_codebase/randoop_tests` repo. <br>
* More of your magic with IO Record generation.
* Please format IORecords in the format seen in `./source_codebase/io_record_repo`.

### Phase 3: Generate New Test Cases With New IORecords
Run `src/main/java/driver/JavaSymbolSolverDriver.java`, it does the following.
* Uses`./source_codebase/io_record_repo/${PACKAGE}.${CLASS}.${TESTCASE}.${METHOD}` as the IORecord base. 
* For each `${PACKAGE}.${CLASS}.${TESTCASE}.${METHOD}` entry in `io_record_repo`: 
    * Finds the correct test file, test case, and method call(s).
    * Inserts XML parser code reading from `new_input.xml` as new input.
    * Inserts code recording old output values and names at each target method call in the test case.
    * Reruns Randoop test to match old input values with variable names
    * Finds and replaces old inputs with new ones.
    * Appends the new test case into the JavaParser `CompilationUnit` of the original Randoop test case. (WIP)
* Writes the (now huge) Randoop and the generated test file into the `./source_codebase/randoop_tests` repo's correct package.
* Generates a main method that invokes all of the test cases.

### Phase 4: Analysis
PANIC!
