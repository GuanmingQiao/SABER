/*
 * This file was automatically generated by EvoSuite
 * Sun Mar 08 16:45:16 GMT 2020
 */
package temp.replacement_repo;

import org.junit.Test;
import static org.junit.Assert.*;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;
import temp.replacement_repo.CustomObject;
import xml.XMLParser;

@RunWith(EvoRunner.class)
@EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true)
public class TEST_COLUMBIA_SABER_CustomObject extends CustomObject_ESTest_scaffolding {

    @Test(timeout = 4000)
    public static void test0() throws Throwable {
        CustomObject customObject0 = new CustomObject();
        customObject0.setValue(201);
        int int0 = customObject0.getValue();
        assertEquals(201, int0);
    }

    @Test(timeout = 4000)
    public static void test1() throws Throwable {
        CustomObject customObject0 = new CustomObject();
        customObject0.setFoo("");
        String string0 = customObject0.getFoo();
        assertEquals("", string0);
    }

    @Test(timeout = 4000)
    public static void test2() throws Throwable {
        CustomObject customObject0 = new CustomObject();
        customObject0.setFoo("temp.replacement_repo.CustomObject");
        String string0 = customObject0.getFoo();
        assertEquals("temp.replacement_repo.CustomObject", string0);
    }

    @Test(timeout = 4000)
    public static void test3() throws Throwable {
        CustomObject customObject0 = new CustomObject();
        int int0 = customObject0.getValue();
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public static void test4() throws Throwable {
        CustomObject customObject0 = new CustomObject();
        String string0 = customObject0.getFoo();
        assertNull(string0);
    }

    @Test(timeout = 4000)
    public static void test5() throws Throwable {
        CustomObject customObject0 = new CustomObject();
        customObject0.setValue((-1));
        int int0 = customObject0.getValue();
        assertEquals((-1), int0);
    }

    public static void main(String[] args) throws Throwable {
        test5_SABER_635115106_1583698251006_getValue_1();
        test5_SABER_1328037091_1583698251024_getValue_2();
        test5_SABER_1328037091_1583698251024_getValue_1();
        test0_SABER_635115106_1583698251006_setValue_1();
        test0_SABER_1328037091_1583698251024_setValue_2();
        test0_SABER_1328037091_1583698251024_setValue_1();
        test2_SABER_1328037091_1583698251024_setFoo_2();
        test2_SABER_1328037091_1583698251024_setFoo_1();
        test2_SABER_635115106_1583698251006_setFoo_1();
        test1_SABER_635115106_1583698251006_getFoo_1();
        test1_SABER_1328037091_1583698251024_getFoo_2();
        test1_SABER_1328037091_1583698251024_getFoo_1();
    }

    @Test(timeout = 4000)
    public static void test5_SABER_635115106_1583698251006_getValue_1() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_635115106_1583698251006_getValue_1.xml");
        java.lang.Integer newInput0 = (java.lang.Integer) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setValue((-1));
        int int0 = customObject0.getValue();
    }

    @Test(timeout = 4000)
    public static void test5_SABER_1328037091_1583698251024_getValue_2() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_1328037091_1583698251024_getValue_2.xml");
        java.lang.Integer newInput0 = (java.lang.Integer) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setValue((-1));
        int int0 = customObject0.getValue();
    }

    @Test(timeout = 4000)
    public static void test5_SABER_1328037091_1583698251024_getValue_1() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_1328037091_1583698251024_getValue_1.xml");
        java.lang.Integer newInput0 = (java.lang.Integer) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setValue((-1));
        int int0 = customObject0.getValue();
    }

    @Test(timeout = 4000)
    public static void test0_SABER_635115106_1583698251006_setValue_1() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_635115106_1583698251006_setValue_1.xml");
        java.lang.Integer newInput0 = (java.lang.Integer) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setValue(newInput0);
        int int0 = customObject0.getValue();
    }

    @Test(timeout = 4000)
    public static void test0_SABER_1328037091_1583698251024_setValue_2() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_1328037091_1583698251024_setValue_2.xml");
        java.lang.Integer newInput0 = (java.lang.Integer) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setValue(newInput0);
        int int0 = customObject0.getValue();
    }

    @Test(timeout = 4000)
    public static void test0_SABER_1328037091_1583698251024_setValue_1() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_1328037091_1583698251024_setValue_1.xml");
        java.lang.Integer newInput0 = (java.lang.Integer) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setValue(newInput0);
        int int0 = customObject0.getValue();
    }

    @Test(timeout = 4000)
    public static void test2_SABER_1328037091_1583698251024_setFoo_2() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_1328037091_1583698251024_setFoo_2.xml");
        java.lang.String newInput0 = (java.lang.String) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setFoo(newInput0);
        String string0 = customObject0.getFoo();
    }

    @Test(timeout = 4000)
    public static void test2_SABER_1328037091_1583698251024_setFoo_1() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_1328037091_1583698251024_setFoo_1.xml");
        java.lang.String newInput0 = (java.lang.String) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setFoo(newInput0);
        String string0 = customObject0.getFoo();
    }

    @Test(timeout = 4000)
    public static void test2_SABER_635115106_1583698251006_setFoo_1() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_635115106_1583698251006_setFoo_1.xml");
        java.lang.String newInput0 = (java.lang.String) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setFoo(newInput0);
        String string0 = customObject0.getFoo();
    }

    @Test(timeout = 4000)
    public static void test1_SABER_635115106_1583698251006_getFoo_1() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_635115106_1583698251006_getFoo_1.xml");
        java.lang.String newInput0 = (java.lang.String) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setFoo("");
        String string0 = customObject0.getFoo();
    }

    @Test(timeout = 4000)
    public static void test1_SABER_1328037091_1583698251024_getFoo_2() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_1328037091_1583698251024_getFoo_2.xml");
        java.lang.String newInput0 = (java.lang.String) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setFoo("");
        String string0 = customObject0.getFoo();
    }

    @Test(timeout = 4000)
    public static void test1_SABER_1328037091_1583698251024_getFoo_1() throws Throwable {
        XMLParser xmlParser = new XMLParser("/Users/adityasridhar/Documents/COLUMBIA/SABER/source_codebase/xml_file_dump/TEST_COLUMBIA_SABER_xmlFileDump_SABER_1328037091_1583698251024_getFoo_1.xml");
        java.lang.String newInput0 = (java.lang.String) xmlParser.getNewInputByIndex(0);
        CustomObject customObject0 = new CustomObject();
        customObject0.setFoo("");
        String string0 = customObject0.getFoo();
    }
}

