/*
 * This file was automatically generated by EvoSuite
 * Sun Mar 08 16:45:52 GMT 2020
 */
package temp.replacement_repo;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.LinkedList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import temp.replacement_repo.CustomObject;
import temp.replacement_repo.OriginalClass;
import xml.XMLParser;
import java.util.List;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class TEST_COLUMBIA_SABER_OriginalClass extends OriginalClass_ESTest_scaffolding {

    @Test(timeout = 4000)
    public static void test0() throws Throwable {
        OriginalClass originalClass0 = new OriginalClass();
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        Integer integer0 = Integer.valueOf(209);
        linkedList0.add(integer0);
        CustomObject customObject0 = new CustomObject();
        linkedList0.add(integer0);
        String string0 = originalClass0.objectMethod(linkedList0, customObject0);
        assertEquals("0_2", string0);
    }

    @Test(timeout = 4000)
    public static void test1() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_1328037091_1583698251024_originalMethod_1.xml");
        java.lang.Integer newInput0 = (java.lang.Integer) xmlParser.getNewInputByIndex(0);
        OriginalClass originalClass0 = new OriginalClass();
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        Reporter reporter = new Reporter("temp.replacement_repo.OriginalClass.test1.originalMethod");
        GenericsType<List<Integer>> g152 = new GenericsType<>();
        g152.set(linkedList0, "linkedList0", "temp.replacement_repo.OriginalClass.originalMethod", 0);
        reporter.addTargetParam(g152);
        GenericsType<Integer> g153 = new GenericsType<>();
        g153.set(0, "0", "temp.replacement_repo.OriginalClass.originalMethod", 1);
        reporter.addTargetParam(g153);
        GenericsType<OriginalClass> g154 = new GenericsType<>();
        g154.set(originalClass0, "originalClass0", "temp.replacement_repo.OriginalClass.originalMethod", 0);
        reporter.addLocalVar(g154);
        GenericsType<LinkedList<Integer>> g155 = new GenericsType<>();
        g155.set(linkedList0, "linkedList0", "temp.replacement_repo.OriginalClass.originalMethod", 1);
        reporter.addLocalVar(g155);
        GenericsType<java.lang.Integer> g156 = new GenericsType<>();
        g156.set(newInput0, "newInput0", "temp.replacement_repo.OriginalClass.originalMethod", 2);
        reporter.addLocalVar(g156);
        GenericsType<XMLParser> g157 = new GenericsType<>();
        g157.set(xmlParser, "xmlParser", "temp.replacement_repo.OriginalClass.originalMethod", 3);
        reporter.addLocalVar(g157);
        reporter.report();
        int int0 = originalClass0.originalMethod(linkedList0, 0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public static void test2() throws Throwable {
        OriginalClass originalClass0 = new OriginalClass();
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        int int0 = originalClass0.originalMethod(linkedList0, 472);
        assertEquals(472, int0);
    }

    @Test(timeout = 4000)
    public static void test3() throws Throwable {
        OriginalClass originalClass0 = new OriginalClass();
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        linkedList0.offerFirst((Integer) null);
        // Undeclared exception!
        try {
            originalClass0.originalMethod(linkedList0, (-2774));
            fail("Expecting exception: NullPointerException");
        } catch (NullPointerException e) {
            // 
            // no message in exception (getMessage() returned null)
            // 
            verifyException("temp.replacement_repo.OriginalClass", e);
        }
    }

    @Test(timeout = 4000)
    public static void test4() throws Throwable {
        OriginalClass originalClass0 = new OriginalClass();
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        // Undeclared exception!
        try {
            originalClass0.objectMethod(linkedList0, (CustomObject) null);
            fail("Expecting exception: NullPointerException");
        } catch (NullPointerException e) {
            // 
            // no message in exception (getMessage() returned null)
            // 
            verifyException("temp.replacement_repo.OriginalClass", e);
        }
    }

    @Test(timeout = 4000)
    public static void test5() throws Throwable {
        OriginalClass originalClass0 = new OriginalClass();
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        CustomObject customObject0 = new CustomObject();
        Integer integer0 = new Integer((-22));
        linkedList0.add(integer0);
        linkedList0.push(integer0);
        linkedList0.add(integer0);
        String string0 = originalClass0.objectMethod(linkedList0, customObject0);
        assertEquals("list size greater than 2", string0);
    }

    @Test(timeout = 4000)
    public static void test6() throws Throwable {
        OriginalClass originalClass0 = new OriginalClass();
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        Integer integer0 = new Integer((-22));
        linkedList0.add(integer0);
        int int0 = originalClass0.originalMethod(linkedList0, (-22));
        assertEquals((-44), int0);
    }

    public static void main(String[] args) throws Throwable {
        test0();
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }
}
