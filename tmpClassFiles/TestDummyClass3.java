package p5;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import p5.BConverter;
import p5.EMConverter;
import p5.IORecord;
import p5.Reporter;
import p5.XMLParser;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDummyClass3 {

    public static boolean debug = false;

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test01");
        p5.DummyClass3 dummyClass3_0 = new p5.DummyClass3();
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test02");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = dummyClass3_4.returnNewString(1, 0.0d, "hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        // Expected exception.
        }
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test03");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        double double12 = dummyClass3_4.getDecimal();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + 10.0d + "'", double12 == 10.0d);
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test04");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        java.util.List<java.lang.String> strList7 = null;
        p5.DummyClass3 dummyClass3_9 = new p5.DummyClass3("", "hi!", strList7, (double) (byte) 10);
        double double10 = dummyClass3_9.getDecimal();
        dummyClass3_9.setFirstName("");
        java.util.List<java.lang.Integer> intList13 = dummyClass3_9.getValues();
        dummyClass3_4.setValues(intList13);
        java.util.List<java.lang.Integer> intList15 = dummyClass3_4.selectionSort();
        dummyClass3_4.setDecimal((double) 0.0f);
        java.lang.String str18 = dummyClass3_4.getLastName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double10 + "' != '" + 10.0d + "'", double10 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList13);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList15);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "hi!" + "'", str18.equals("hi!"));
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test05");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        java.util.List<java.lang.String> strList7 = null;
        p5.DummyClass3 dummyClass3_9 = new p5.DummyClass3("", "hi!", strList7, (double) (byte) 10);
        double double10 = dummyClass3_9.getDecimal();
        dummyClass3_9.setFirstName("");
        java.util.List<java.lang.Integer> intList13 = dummyClass3_9.getValues();
        dummyClass3_4.setValues(intList13);
        java.util.List<java.lang.Integer> intList15 = dummyClass3_4.selectionSort();
        java.util.List<java.lang.String> strList16 = dummyClass3_4.getStrings();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double10 + "' != '" + 10.0d + "'", double10 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList13);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList15);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(strList16);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test06");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        dummyClass3_4.setFirstName("");
        java.lang.String str8 = dummyClass3_4.getFirstName();
        java.lang.String str9 = dummyClass3_4.getLastName();
        dummyClass3_4.setLastName("");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "" + "'", str8.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "hi!" + "'", str9.equals("hi!"));
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test07");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = dummyClass3_4.getStrings();
        java.lang.String str15 = dummyClass3_4.getFirstName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList14);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str15 + "' != '" + "" + "'", str15.equals(""));
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test08");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        java.util.List<java.lang.Integer> intList14 = null;
        dummyClass3_4.setValues(intList14);
        java.lang.String str16 = dummyClass3_4.getLastName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "hi!" + "'", str16.equals("hi!"));
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test09");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        dummyClass3_4.setFirstName("");
        java.util.List<java.lang.Integer> intList8 = dummyClass3_4.getValues();
        double double9 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.String> strList12 = null;
        p5.DummyClass3 dummyClass3_14 = new p5.DummyClass3("", "hi!", strList12, (double) (byte) 10);
        java.util.List<java.lang.String> strList17 = null;
        p5.DummyClass3 dummyClass3_19 = new p5.DummyClass3("", "hi!", strList17, (double) (byte) 10);
        double double20 = dummyClass3_19.getDecimal();
        dummyClass3_19.setFirstName("");
        java.util.List<java.lang.Integer> intList23 = dummyClass3_19.getValues();
        dummyClass3_14.setValues(intList23);
        java.util.List<java.lang.Integer> intList25 = dummyClass3_14.selectionSort();
        dummyClass3_4.setValues(intList25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 10.0d + "'", double9 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double20 + "' != '" + 10.0d + "'", double20 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList23);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList25);
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test10");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        dummyClass3_4.setFirstName("");
        java.util.List<java.lang.Integer> intList8 = dummyClass3_4.getValues();
        double double9 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.String> strList12 = null;
        p5.DummyClass3 dummyClass3_14 = new p5.DummyClass3("", "hi!", strList12, (double) (byte) 10);
        double double15 = dummyClass3_14.getDecimal();
        java.lang.String[] strArray18 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList19 = new java.util.ArrayList<java.lang.String>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList19, strArray18);
        dummyClass3_14.setStrings((java.util.List<java.lang.String>) strList19);
        java.util.List<java.lang.Integer> intList22 = dummyClass3_14.selectionSort();
        dummyClass3_4.setValues(intList22);
        java.util.List<java.lang.String> strList24 = dummyClass3_4.getStrings();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 10.0d + "'", double9 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double15 + "' != '" + 10.0d + "'", double15 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray18);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList22);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(strList24);
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test11");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.String> strList10 = null;
        p5.DummyClass3 dummyClass3_12 = new p5.DummyClass3("", "hi!", strList10, (double) (byte) 10);
        double double13 = dummyClass3_12.getDecimal();
        java.lang.String[] strArray16 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList17 = new java.util.ArrayList<java.lang.String>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList17, strArray16);
        dummyClass3_12.setStrings((java.util.List<java.lang.String>) strList17);
        p5.DummyClass3 dummyClass3_21 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList17, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList17);
        java.util.List<java.lang.Integer> intList23 = dummyClass3_4.selectionSort();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 10.0d + "'", double13 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray16);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList23);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test12");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str16 = dummyClass3_4.returnNewString(100, (double) 10.0f, "hi!");
        dummyClass3_4.setDecimal((double) (byte) 0);
        double double19 = dummyClass3_4.getDecimal();
        java.lang.String str20 = dummyClass3_4.getFirstName();
        java.util.List<java.lang.String> strList21 = dummyClass3_4.getStrings();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "hi!hi!10.0" + "'", str16.equals("hi!hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double19 + "' != '" + 0.0d + "'", double19 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str20 + "' != '" + "" + "'", str20.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList21);
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test13");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str16 = dummyClass3_4.returnNewString(100, (double) 10.0f, "hi!");
        dummyClass3_4.setDecimal((double) (byte) 0);
        double double19 = dummyClass3_4.getDecimal();
        double double20 = dummyClass3_4.getDecimal();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "hi!hi!10.0" + "'", str16.equals("hi!hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double19 + "' != '" + 0.0d + "'", double19 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double20 + "' != '" + 0.0d + "'", double20 == 0.0d);
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test14");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.String> strList10 = null;
        p5.DummyClass3 dummyClass3_12 = new p5.DummyClass3("", "hi!", strList10, (double) (byte) 10);
        double double13 = dummyClass3_12.getDecimal();
        java.lang.String[] strArray16 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList17 = new java.util.ArrayList<java.lang.String>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList17, strArray16);
        dummyClass3_12.setStrings((java.util.List<java.lang.String>) strList17);
        p5.DummyClass3 dummyClass3_21 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList17, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList17);
        dummyClass3_4.setLastName("hi!hi!10.0");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 10.0d + "'", double13 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray16);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test15");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.String> strList10 = null;
        p5.DummyClass3 dummyClass3_12 = new p5.DummyClass3("", "hi!", strList10, (double) (byte) 10);
        double double13 = dummyClass3_12.getDecimal();
        java.lang.String[] strArray16 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList17 = new java.util.ArrayList<java.lang.String>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList17, strArray16);
        dummyClass3_12.setStrings((java.util.List<java.lang.String>) strList17);
        p5.DummyClass3 dummyClass3_21 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList17, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList17);
        dummyClass3_4.setLastName("hi!");
        java.lang.String str25 = dummyClass3_4.getLastName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 10.0d + "'", double13 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray16);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str25 + "' != '" + "hi!" + "'", str25.equals("hi!"));
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test16");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        java.util.List<java.lang.String> strList7 = null;
        p5.DummyClass3 dummyClass3_9 = new p5.DummyClass3("", "hi!", strList7, (double) (byte) 10);
        double double10 = dummyClass3_9.getDecimal();
        dummyClass3_9.setFirstName("");
        java.util.List<java.lang.Integer> intList13 = dummyClass3_9.getValues();
        dummyClass3_4.setValues(intList13);
        java.util.List<java.lang.Integer> intList15 = dummyClass3_4.selectionSort();
        dummyClass3_4.setDecimal(0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double10 + "' != '" + 10.0d + "'", double10 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList13);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList15);
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test17");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        dummyClass3_4.setFirstName("");
        java.lang.String str8 = dummyClass3_4.getFirstName();
        java.lang.String str9 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = null;
        p5.DummyClass3 dummyClass3_16 = new p5.DummyClass3("", "hi!", strList14, (double) (byte) 10);
        double double17 = dummyClass3_16.getDecimal();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList21 = new java.util.ArrayList<java.lang.String>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList21, strArray20);
        dummyClass3_16.setStrings((java.util.List<java.lang.String>) strList21);
        p5.DummyClass3 dummyClass3_25 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList21, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList21);
        double double27 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.Integer> intList28 = dummyClass3_4.selectionSort();
        double double29 = dummyClass3_4.getDecimal();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "" + "'", str8.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "hi!" + "'", str9.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double17 + "' != '" + 10.0d + "'", double17 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray20);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double27 + "' != '" + 10.0d + "'", double27 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList28);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double29 + "' != '" + 10.0d + "'", double29 == 10.0d);
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test18");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str16 = dummyClass3_4.returnNewString(100, (double) 10.0f, "hi!");
        dummyClass3_4.setDecimal((double) (byte) 0);
        java.util.List<java.lang.String> strList23 = null;
        p5.DummyClass3 dummyClass3_25 = new p5.DummyClass3("", "hi!", strList23, (double) (byte) 10);
        double double26 = dummyClass3_25.getDecimal();
        java.lang.String[] strArray29 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList30 = new java.util.ArrayList<java.lang.String>();
        boolean boolean31 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList30, strArray29);
        dummyClass3_25.setStrings((java.util.List<java.lang.String>) strList30);
        p5.DummyClass3 dummyClass3_34 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList30, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList30);
        dummyClass3_4.setFirstName("hi!10.0");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "hi!hi!10.0" + "'", str16.equals("hi!hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double26 + "' != '" + 10.0d + "'", double26 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray29);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + true + "'", boolean31 == true);
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test19");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        dummyClass3_4.setFirstName("");
        java.lang.String str8 = dummyClass3_4.getFirstName();
        java.lang.String str9 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = null;
        p5.DummyClass3 dummyClass3_16 = new p5.DummyClass3("", "hi!", strList14, (double) (byte) 10);
        double double17 = dummyClass3_16.getDecimal();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList21 = new java.util.ArrayList<java.lang.String>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList21, strArray20);
        dummyClass3_16.setStrings((java.util.List<java.lang.String>) strList21);
        p5.DummyClass3 dummyClass3_25 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList21, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList21);
        double double27 = dummyClass3_4.getDecimal();
        dummyClass3_4.setDecimal((double) 0.0f);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "" + "'", str8.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "hi!" + "'", str9.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double17 + "' != '" + 10.0d + "'", double17 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray20);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double27 + "' != '" + 10.0d + "'", double27 == 10.0d);
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test20");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        dummyClass3_4.setFirstName("");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test21");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        dummyClass3_4.setLastName("hi!10.0");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test22");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = dummyClass3_4.getStrings();
        java.util.List<java.lang.String> strList17 = null;
        p5.DummyClass3 dummyClass3_19 = new p5.DummyClass3("", "hi!", strList17, (double) (byte) 10);
        double double20 = dummyClass3_19.getDecimal();
        java.lang.String[] strArray23 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList24 = new java.util.ArrayList<java.lang.String>();
        boolean boolean25 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList24, strArray23);
        dummyClass3_19.setStrings((java.util.List<java.lang.String>) strList24);
        java.util.List<java.lang.String> strList29 = null;
        p5.DummyClass3 dummyClass3_31 = new p5.DummyClass3("", "hi!", strList29, (double) (byte) 10);
        double double32 = dummyClass3_31.getDecimal();
        java.lang.String[] strArray35 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList36 = new java.util.ArrayList<java.lang.String>();
        boolean boolean37 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList36, strArray35);
        dummyClass3_31.setStrings((java.util.List<java.lang.String>) strList36);
        dummyClass3_19.setStrings((java.util.List<java.lang.String>) strList36);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList36);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList14);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double20 + "' != '" + 10.0d + "'", double20 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray23);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + true + "'", boolean25 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double32 + "' != '" + 10.0d + "'", double32 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray35);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
    }

    @Test
    public void test23() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test23");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = dummyClass3_4.getStrings();
        java.util.List<java.lang.String> strList17 = null;
        p5.DummyClass3 dummyClass3_19 = new p5.DummyClass3("", "hi!", strList17, (double) (byte) 10);
        double double20 = dummyClass3_19.getDecimal();
        dummyClass3_19.setFirstName("");
        java.util.List<java.lang.Integer> intList23 = dummyClass3_19.getValues();
        double double24 = dummyClass3_19.getDecimal();
        java.util.List<java.lang.String> strList27 = null;
        p5.DummyClass3 dummyClass3_29 = new p5.DummyClass3("", "hi!", strList27, (double) (byte) 10);
        double double30 = dummyClass3_29.getDecimal();
        java.lang.String[] strArray33 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList34 = new java.util.ArrayList<java.lang.String>();
        boolean boolean35 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList34, strArray33);
        dummyClass3_29.setStrings((java.util.List<java.lang.String>) strList34);
        java.util.List<java.lang.Integer> intList37 = dummyClass3_29.selectionSort();
        dummyClass3_19.setValues(intList37);
        dummyClass3_4.setValues(intList37);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList14);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double20 + "' != '" + 10.0d + "'", double20 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList23);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double24 + "' != '" + 10.0d + "'", double24 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double30 + "' != '" + 10.0d + "'", double30 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray33);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + true + "'", boolean35 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList37);
    }

    @Test
    public void test24() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test24");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str16 = dummyClass3_4.returnNewString(100, (double) 10.0f, "hi!");
        dummyClass3_4.setLastName("hi!hi!10.0");
        java.util.List<java.lang.Integer> intList19 = dummyClass3_4.getValues();
        double double20 = dummyClass3_4.getDecimal();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "hi!hi!10.0" + "'", str16.equals("hi!hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double20 + "' != '" + 10.0d + "'", double20 == 10.0d);
    }

    @Test
    public void test25() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test25");
        java.util.List<java.lang.String> strList4 = null;
        p5.DummyClass3 dummyClass3_6 = new p5.DummyClass3("", "hi!", strList4, (double) (byte) 10);
        double double7 = dummyClass3_6.getDecimal();
        java.lang.String[] strArray10 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        dummyClass3_6.setStrings((java.util.List<java.lang.String>) strList11);
        p5.DummyClass3 dummyClass3_15 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList11, 100.0d);
        dummyClass3_15.setFirstName("");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double7 + "' != '" + 10.0d + "'", double7 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray10);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test26() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test26");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str16 = dummyClass3_4.returnNewString(100, (double) 10.0f, "hi!");
        dummyClass3_4.setDecimal((double) (byte) 0);
        double double19 = dummyClass3_4.getDecimal();
        java.lang.String str20 = dummyClass3_4.getFirstName();
        java.lang.String str21 = dummyClass3_4.getLastName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "hi!hi!10.0" + "'", str16.equals("hi!hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double19 + "' != '" + 0.0d + "'", double19 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str20 + "' != '" + "" + "'", str20.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str21 + "' != '" + "hi!" + "'", str21.equals("hi!"));
    }

    @Test
    public void test27() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test27");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.lang.String str12 = dummyClass3_4.getFirstName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str12 + "' != '" + "" + "'", str12.equals(""));
    }

    @Test
    public void test28() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test28");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str16 = dummyClass3_4.returnNewString(100, (double) 10.0f, "hi!");
        dummyClass3_4.setLastName("hi!hi!10.0");
        java.util.List<java.lang.String> strList21 = null;
        p5.DummyClass3 dummyClass3_23 = new p5.DummyClass3("", "hi!", strList21, (double) (byte) 10);
        double double24 = dummyClass3_23.getDecimal();
        java.lang.String[] strArray27 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList28 = new java.util.ArrayList<java.lang.String>();
        boolean boolean29 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList28, strArray27);
        dummyClass3_23.setStrings((java.util.List<java.lang.String>) strList28);
        java.util.List<java.lang.Integer> intList31 = dummyClass3_23.selectionSort();
        java.lang.String str35 = dummyClass3_23.returnNewString(100, (double) 10.0f, "hi!");
        dummyClass3_23.setDecimal((double) (byte) 0);
        double double38 = dummyClass3_23.getDecimal();
        java.lang.String str39 = dummyClass3_23.getFirstName();
        java.util.List<java.lang.Integer> intList40 = dummyClass3_23.selectionSort();
        dummyClass3_4.setValues(intList40);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "hi!hi!10.0" + "'", str16.equals("hi!hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double24 + "' != '" + 10.0d + "'", double24 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray27);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + true + "'", boolean29 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList31);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str35 + "' != '" + "hi!hi!10.0" + "'", str35.equals("hi!hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double38 + "' != '" + 0.0d + "'", double38 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str39 + "' != '" + "" + "'", str39.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList40);
    }

    @Test
    public void test29() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test29");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str16 = dummyClass3_4.returnNewString((-1), (double) 'a', "hi!");
        dummyClass3_4.setLastName("");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "hi!hi!10.0" + "'", str16.equals("hi!hi!10.0"));
    }

    @Test
    public void test30() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test30");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        dummyClass3_4.setFirstName("");
        java.lang.String str8 = dummyClass3_4.getFirstName();
        java.lang.String str9 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = null;
        p5.DummyClass3 dummyClass3_16 = new p5.DummyClass3("", "hi!", strList14, (double) (byte) 10);
        double double17 = dummyClass3_16.getDecimal();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList21 = new java.util.ArrayList<java.lang.String>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList21, strArray20);
        dummyClass3_16.setStrings((java.util.List<java.lang.String>) strList21);
        p5.DummyClass3 dummyClass3_25 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList21, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList21);
        double double27 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.Integer> intList28 = dummyClass3_4.selectionSort();
        java.util.List<java.lang.String> strList31 = null;
        p5.DummyClass3 dummyClass3_33 = new p5.DummyClass3("", "hi!", strList31, (double) (byte) 10);
        java.util.List<java.lang.String> strList36 = null;
        p5.DummyClass3 dummyClass3_38 = new p5.DummyClass3("", "hi!", strList36, (double) (byte) 10);
        double double39 = dummyClass3_38.getDecimal();
        dummyClass3_38.setFirstName("");
        java.util.List<java.lang.Integer> intList42 = dummyClass3_38.getValues();
        dummyClass3_33.setValues(intList42);
        java.util.List<java.lang.Integer> intList44 = dummyClass3_33.selectionSort();
        dummyClass3_4.setValues(intList44);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "" + "'", str8.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "hi!" + "'", str9.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double17 + "' != '" + 10.0d + "'", double17 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray20);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double27 + "' != '" + 10.0d + "'", double27 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList28);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double39 + "' != '" + 10.0d + "'", double39 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList42);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList44);
    }

    @Test
    public void test31() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test31");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        dummyClass3_4.setFirstName("");
        java.lang.String str8 = dummyClass3_4.getFirstName();
        java.lang.String str9 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = null;
        p5.DummyClass3 dummyClass3_16 = new p5.DummyClass3("", "hi!", strList14, (double) (byte) 10);
        double double17 = dummyClass3_16.getDecimal();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList21 = new java.util.ArrayList<java.lang.String>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList21, strArray20);
        dummyClass3_16.setStrings((java.util.List<java.lang.String>) strList21);
        p5.DummyClass3 dummyClass3_25 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList21, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList21);
        dummyClass3_4.setFirstName("");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str8 + "' != '" + "" + "'", str8.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str9 + "' != '" + "hi!" + "'", str9.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double17 + "' != '" + 10.0d + "'", double17 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray20);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
    }

    @Test
    public void test32() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test32");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str16 = dummyClass3_4.returnNewString(100, (double) 10.0f, "hi!");
        dummyClass3_4.setDecimal((double) (byte) 0);
        java.util.List<java.lang.Integer> intList19 = dummyClass3_4.getValues();
        java.util.List<java.lang.Integer> intList20 = dummyClass3_4.selectionSort();
        java.lang.String[] strArray23 = new java.lang.String[] { "hi!", "" };
        java.util.ArrayList<java.lang.String> strList24 = new java.util.ArrayList<java.lang.String>();
        boolean boolean25 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList24, strArray23);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList24);
        java.lang.String str30 = dummyClass3_4.returnNewString((int) (short) -1, (double) (short) 0, "hi!");
        dummyClass3_4.setLastName("hi!10.0");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "hi!hi!10.0" + "'", str16.equals("hi!hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList20);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray23);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + true + "'", boolean25 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "hi!hi!0.0" + "'", str30.equals("hi!hi!0.0"));
    }

    @Test
    public void test33() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test33");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = dummyClass3_4.getStrings();
        dummyClass3_4.setFirstName("hi!10.0");
        java.lang.String str17 = dummyClass3_4.getLastName();
        java.lang.String str18 = dummyClass3_4.getLastName();
        java.lang.String str19 = dummyClass3_4.getFirstName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList14);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str17 + "' != '" + "hi!" + "'", str17.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "hi!" + "'", str18.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "hi!10.0" + "'", str19.equals("hi!10.0"));
    }

    @Test
    public void test34() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test34");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        java.util.List<java.lang.String> strList7 = null;
        p5.DummyClass3 dummyClass3_9 = new p5.DummyClass3("", "hi!", strList7, (double) (byte) 10);
        double double10 = dummyClass3_9.getDecimal();
        dummyClass3_9.setFirstName("");
        java.util.List<java.lang.Integer> intList13 = dummyClass3_9.getValues();
        dummyClass3_4.setValues(intList13);
        double double15 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.Integer> intList16 = dummyClass3_4.selectionSort();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double10 + "' != '" + 10.0d + "'", double10 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList13);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double15 + "' != '" + 10.0d + "'", double15 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList16);
    }

    @Test
    public void test35() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test35");
        java.util.List<java.lang.String> strList4 = null;
        p5.DummyClass3 dummyClass3_6 = new p5.DummyClass3("", "hi!", strList4, (double) (byte) 10);
        double double7 = dummyClass3_6.getDecimal();
        java.lang.String[] strArray10 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        dummyClass3_6.setStrings((java.util.List<java.lang.String>) strList11);
        p5.DummyClass3 dummyClass3_15 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList11, 100.0d);
        java.lang.String str16 = dummyClass3_15.getLastName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double7 + "' != '" + 10.0d + "'", double7 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray10);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "" + "'", str16.equals(""));
    }

    @Test
    public void test36() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test36");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        java.util.List<java.lang.String> strList7 = null;
        p5.DummyClass3 dummyClass3_9 = new p5.DummyClass3("", "hi!", strList7, (double) (byte) 10);
        double double10 = dummyClass3_9.getDecimal();
        dummyClass3_9.setFirstName("");
        java.util.List<java.lang.Integer> intList13 = dummyClass3_9.getValues();
        dummyClass3_4.setValues(intList13);
        java.util.List<java.lang.Integer> intList15 = dummyClass3_4.selectionSort();
        java.util.List<java.lang.String> strList20 = null;
        p5.DummyClass3 dummyClass3_22 = new p5.DummyClass3("", "hi!", strList20, (double) (byte) 10);
        double double23 = dummyClass3_22.getDecimal();
        java.lang.String[] strArray26 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList27 = new java.util.ArrayList<java.lang.String>();
        boolean boolean28 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList27, strArray26);
        dummyClass3_22.setStrings((java.util.List<java.lang.String>) strList27);
        p5.DummyClass3 dummyClass3_31 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList27, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList27);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double10 + "' != '" + 10.0d + "'", double10 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList13);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList15);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double23 + "' != '" + 10.0d + "'", double23 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray26);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + true + "'", boolean28 == true);
    }

    @Test
    public void test37() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test37");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = dummyClass3_4.getStrings();
        dummyClass3_4.setLastName("hi!hi!10.0");
        java.util.List<java.lang.String> strList19 = null;
        p5.DummyClass3 dummyClass3_21 = new p5.DummyClass3("", "hi!", strList19, (double) (byte) 10);
        double double22 = dummyClass3_21.getDecimal();
        java.lang.String[] strArray25 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList26 = new java.util.ArrayList<java.lang.String>();
        boolean boolean27 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList26, strArray25);
        dummyClass3_21.setStrings((java.util.List<java.lang.String>) strList26);
        java.util.List<java.lang.String> strList31 = null;
        p5.DummyClass3 dummyClass3_33 = new p5.DummyClass3("", "hi!", strList31, (double) (byte) 10);
        double double34 = dummyClass3_33.getDecimal();
        java.lang.String[] strArray37 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList38 = new java.util.ArrayList<java.lang.String>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList38, strArray37);
        dummyClass3_33.setStrings((java.util.List<java.lang.String>) strList38);
        dummyClass3_21.setStrings((java.util.List<java.lang.String>) strList38);
        java.util.List<java.lang.String> strList44 = null;
        p5.DummyClass3 dummyClass3_46 = new p5.DummyClass3("", "hi!", strList44, (double) (byte) 10);
        double double47 = dummyClass3_46.getDecimal();
        java.lang.String[] strArray50 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList51 = new java.util.ArrayList<java.lang.String>();
        boolean boolean52 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList51, strArray50);
        dummyClass3_46.setStrings((java.util.List<java.lang.String>) strList51);
        java.util.List<java.lang.String> strList56 = null;
        p5.DummyClass3 dummyClass3_58 = new p5.DummyClass3("", "hi!", strList56, (double) (byte) 10);
        double double59 = dummyClass3_58.getDecimal();
        java.lang.String[] strArray62 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList63 = new java.util.ArrayList<java.lang.String>();
        boolean boolean64 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList63, strArray62);
        dummyClass3_58.setStrings((java.util.List<java.lang.String>) strList63);
        dummyClass3_46.setStrings((java.util.List<java.lang.String>) strList63);
        dummyClass3_21.setStrings((java.util.List<java.lang.String>) strList63);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList63);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList14);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double22 + "' != '" + 10.0d + "'", double22 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + true + "'", boolean27 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double34 + "' != '" + 10.0d + "'", double34 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray37);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + true + "'", boolean39 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double47 + "' != '" + 10.0d + "'", double47 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray50);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + true + "'", boolean52 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double59 + "' != '" + 10.0d + "'", double59 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray62);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + true + "'", boolean64 == true);
    }

    @Test
    public void test38() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test38");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        dummyClass3_4.setFirstName("");
        java.util.List<java.lang.Integer> intList8 = dummyClass3_4.getValues();
        java.util.List<java.lang.String> strList9 = dummyClass3_4.getStrings();
        dummyClass3_4.setLastName("");
        dummyClass3_4.setFirstName("hi!hi!0.0");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(strList9);
    }

    @Test
    public void test39() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test39");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.String> strList10 = null;
        p5.DummyClass3 dummyClass3_12 = new p5.DummyClass3("", "hi!", strList10, (double) (byte) 10);
        double double13 = dummyClass3_12.getDecimal();
        java.lang.String[] strArray16 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList17 = new java.util.ArrayList<java.lang.String>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList17, strArray16);
        dummyClass3_12.setStrings((java.util.List<java.lang.String>) strList17);
        p5.DummyClass3 dummyClass3_21 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList17, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList17);
        java.lang.String str23 = dummyClass3_4.getFirstName();
        java.util.List<java.lang.String> strList24 = dummyClass3_4.getStrings();
        java.util.List<java.lang.Integer> intList25 = null;
        dummyClass3_4.setValues(intList25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 10.0d + "'", double13 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray16);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "" + "'", str23.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList24);
    }

    @Test
    public void test40() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test40");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        dummyClass3_4.setFirstName("");
        java.util.List<java.lang.Integer> intList8 = dummyClass3_4.getValues();
        java.util.List<java.lang.String> strList9 = dummyClass3_4.getStrings();
        dummyClass3_4.setLastName("");
        java.lang.String str12 = dummyClass3_4.getFirstName();
        java.util.List<java.lang.Integer> intList13 = dummyClass3_4.getValues();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNull(strList9);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str12 + "' != '" + "" + "'", str12.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList13);
    }

    @Test
    public void test41() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test41");
        java.util.List<java.lang.String> strList4 = null;
        p5.DummyClass3 dummyClass3_6 = new p5.DummyClass3("", "hi!", strList4, (double) (byte) 10);
        double double7 = dummyClass3_6.getDecimal();
        java.lang.String[] strArray10 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        dummyClass3_6.setStrings((java.util.List<java.lang.String>) strList11);
        java.util.List<java.lang.Integer> intList14 = dummyClass3_6.selectionSort();
        java.lang.String str18 = dummyClass3_6.returnNewString(100, (double) 10.0f, "hi!");
        dummyClass3_6.setDecimal((double) (byte) 0);
        java.util.List<java.lang.Integer> intList21 = dummyClass3_6.getValues();
        java.util.List<java.lang.Integer> intList22 = dummyClass3_6.selectionSort();
        java.lang.String[] strArray25 = new java.lang.String[] { "hi!", "" };
        java.util.ArrayList<java.lang.String> strList26 = new java.util.ArrayList<java.lang.String>();
        boolean boolean27 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList26, strArray25);
        dummyClass3_6.setStrings((java.util.List<java.lang.String>) strList26);
        p5.DummyClass3 dummyClass3_30 = new p5.DummyClass3("", "", (java.util.List<java.lang.String>) strList26, (double) 0.0f);
        java.util.List<java.lang.Integer> intList31 = dummyClass3_30.selectionSort();
        java.lang.String str32 = dummyClass3_30.getFirstName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double7 + "' != '" + 10.0d + "'", double7 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray10);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList14);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str18 + "' != '" + "hi!hi!10.0" + "'", str18.equals("hi!hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList21);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList22);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + true + "'", boolean27 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList31);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str32 + "' != '" + "" + "'", str32.equals(""));
    }

    @Test
    public void test42() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test42");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str16 = dummyClass3_4.returnNewString(100, (double) 10.0f, "hi!");
        dummyClass3_4.setDecimal((double) (byte) 0);
        java.util.List<java.lang.Integer> intList19 = dummyClass3_4.getValues();
        java.lang.String str20 = dummyClass3_4.getFirstName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "hi!hi!10.0" + "'", str16.equals("hi!hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str20 + "' != '" + "" + "'", str20.equals(""));
    }

    @Test
    public void test43() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test43");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        dummyClass3_4.setFirstName("");
        java.util.List<java.lang.Integer> intList8 = dummyClass3_4.getValues();
        double double9 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.Integer> intList10 = dummyClass3_4.getValues();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double9 + "' != '" + 10.0d + "'", double9 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList10);
    }

    @Test
    public void test44() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test44");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = dummyClass3_4.getStrings();
        dummyClass3_4.setFirstName("hi!10.0");
        java.lang.String str17 = dummyClass3_4.getLastName();
        java.util.List<java.lang.Integer> intList18 = dummyClass3_4.getValues();
        dummyClass3_4.setLastName("hi!10.0");
        dummyClass3_4.setFirstName("hi!");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList14);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str17 + "' != '" + "hi!" + "'", str17.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList18);
    }

    @Test
    public void test45() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test45");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.util.List<java.lang.String> strList10 = null;
        p5.DummyClass3 dummyClass3_12 = new p5.DummyClass3("", "hi!", strList10, (double) (byte) 10);
        double double13 = dummyClass3_12.getDecimal();
        java.lang.String[] strArray16 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList17 = new java.util.ArrayList<java.lang.String>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList17, strArray16);
        dummyClass3_12.setStrings((java.util.List<java.lang.String>) strList17);
        p5.DummyClass3 dummyClass3_21 = new p5.DummyClass3("hi!", "", (java.util.List<java.lang.String>) strList17, 100.0d);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList17);
        java.lang.String str23 = dummyClass3_4.getFirstName();
        double double24 = dummyClass3_4.getDecimal();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 10.0d + "'", double13 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray16);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + true + "'", boolean18 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str23 + "' != '" + "" + "'", str23.equals(""));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double24 + "' != '" + 10.0d + "'", double24 == 10.0d);
    }

    @Test
    public void test46() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test46");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        java.util.List<java.lang.Integer> intList14 = null;
        dummyClass3_4.setValues(intList14);
        java.lang.String str19 = dummyClass3_4.returnNewString(0, (double) 0L, "");
        java.lang.String str20 = dummyClass3_4.getFirstName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "hi!10.0" + "'", str19.equals("hi!10.0"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str20 + "' != '" + "" + "'", str20.equals(""));
    }

    @Test
    public void test47() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test47");
        java.util.List<java.lang.String> strList2 = null;
        p5.DummyClass3 dummyClass3_4 = new p5.DummyClass3("", "hi!", strList2, (double) (byte) 10);
        double double5 = dummyClass3_4.getDecimal();
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        dummyClass3_4.setStrings((java.util.List<java.lang.String>) strList9);
        java.util.List<java.lang.Integer> intList12 = dummyClass3_4.selectionSort();
        java.lang.String str13 = dummyClass3_4.getLastName();
        java.util.List<java.lang.String> strList14 = dummyClass3_4.getStrings();
        dummyClass3_4.setLastName("hi!hi!10.0");
        java.lang.String str17 = dummyClass3_4.getFirstName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double5 + "' != '" + 10.0d + "'", double5 == 10.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray8);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList14);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str17 + "' != '" + "" + "'", str17.equals(""));
    }
}
