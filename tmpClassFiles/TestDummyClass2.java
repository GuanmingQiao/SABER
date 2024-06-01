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
public class TestDummyClass2 {

    public static boolean debug = false;

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test01");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList20);
        java.lang.String[] strArray31 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList32 = new java.util.ArrayList<java.lang.String>();
        boolean boolean33 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList32, strArray31);
        p5.DummyClass2 dummyClass2_35 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList32, (double) (-1.0f));
        dummyClass2_35.setName("");
        java.lang.String[] strArray44 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList45 = new java.util.ArrayList<java.lang.String>();
        boolean boolean46 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList45, strArray44);
        p5.DummyClass2 dummyClass2_48 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList45, (double) (-1.0f));
        dummyClass2_35.setListOfItems((java.util.List<java.lang.String>) strList45);
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList45);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray31);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + true + "'", boolean33 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray44);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + true + "'", boolean46 == true);
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test02");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.lang.String str11 = dummyClass2_10.getType();
        dummyClass2_10.setName("hi!");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str11 + "' != '" + "hi!" + "'", str11.equals("hi!"));
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test03");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        double double11 = dummyClass2_10.getProbability();
        java.util.List<java.lang.String> strList12 = dummyClass2_10.getListOfItems();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double11 + "' != '" + (-1.0d) + "'", double11 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList12);
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test04");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("");
        int int16 = dummyClass2_10.addToAndReturnUpdatedValue(100, (int) (byte) 10, "");
        java.util.List<java.lang.Integer> intList17 = dummyClass2_10.insertionSort();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 135 + "'", int16 == 135);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList17);
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test05");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList11 = dummyClass2_10.insertionSort();
        java.util.List<java.lang.String> strList12 = dummyClass2_10.getListOfItems();
        int int13 = dummyClass2_10.getValue();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 35 + "'", int13 == 35);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test06");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList20);
        java.util.List<java.lang.Integer> intList25 = dummyClass2_10.insertionSort();
        dummyClass2_10.setValue((int) 'a');
        int int28 = dummyClass2_10.getValue();
        int int32 = dummyClass2_10.addToAndReturnUpdatedValue(10, (int) (short) -1, "");
        java.lang.String str33 = dummyClass2_10.getType();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 97 + "'", int28 == 97);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int32 + "' != '" + 107 + "'", int32 == 107);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str33 + "' != '" + "hi!" + "'", str33.equals("hi!"));
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test07");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList20);
        java.util.List<java.lang.Integer> intList25 = dummyClass2_10.insertionSort();
        int int26 = dummyClass2_10.getNumOperations();
        double double27 = dummyClass2_10.getProbability();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 1 + "'", int26 == 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double27 + "' != '" + (-1.0d) + "'", double27 == (-1.0d));
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test08");
        java.lang.String[] strArray7 = new java.lang.String[] { "1hi!351", "hi!", "hi!" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        p5.DummyClass2 dummyClass2_11 = new p5.DummyClass2("1hi!351", "", 100, 100, (java.util.List<java.lang.String>) strList8, (double) 0L);
        int int15 = dummyClass2_11.addToAndReturnUpdatedValue(107, (int) '#', "");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray7);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 207 + "'", int15 == 207);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test09");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList20);
        java.util.List<java.lang.Integer> intList25 = dummyClass2_10.insertionSort();
        dummyClass2_10.setValue((int) 'a');
        int int28 = dummyClass2_10.getValue();
        dummyClass2_10.setValue((int) (byte) 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 97 + "'", int28 == 97);
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test10");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList20);
        java.util.List<java.lang.Integer> intList25 = dummyClass2_10.insertionSort();
        int int26 = dummyClass2_10.getNumOperations();
        java.util.List<java.lang.Integer> intList27 = dummyClass2_10.getListOfNumbers();
        java.util.List<java.lang.String> strList28 = dummyClass2_10.getListOfItems();
        dummyClass2_10.setName("1hi!351");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 1 + "'", int26 == 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList27);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList28);
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test11");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList11 = dummyClass2_10.insertionSort();
        double double12 = dummyClass2_10.getDistanceOfItems();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_23.setName("");
        java.lang.String[] strArray32 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList33 = new java.util.ArrayList<java.lang.String>();
        boolean boolean34 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList33, strArray32);
        p5.DummyClass2 dummyClass2_36 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList33, (double) (-1.0f));
        dummyClass2_23.setListOfItems((java.util.List<java.lang.String>) strList33);
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList33);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + 0.0d + "'", double12 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray32);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test12");
        p5.DummyClass2 dummyClass2_0 = new p5.DummyClass2();
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test13");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList11 = dummyClass2_10.insertionSort();
        dummyClass2_10.setName("");
        int int14 = dummyClass2_10.getNumOperations();
        java.lang.String str17 = dummyClass2_10.getInterestingItem("hi!", 35);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 1 + "'", int14 == 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str17 + "' != '" + "35hi!351" + "'", str17.equals("35hi!351"));
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test14");
        java.lang.String[] strArray11 = new java.lang.String[] { "1hi!351", "hi!", "hi!" };
        java.util.ArrayList<java.lang.String> strList12 = new java.util.ArrayList<java.lang.String>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList12, strArray11);
        p5.DummyClass2 dummyClass2_15 = new p5.DummyClass2("1hi!351", "", 100, 100, (java.util.List<java.lang.String>) strList12, (double) 0L);
        p5.DummyClass2 dummyClass2_17 = new p5.DummyClass2("hi!", "1hi!351", 100, (int) ' ', (java.util.List<java.lang.String>) strList12, (double) (short) -1);
        double double18 = dummyClass2_17.getProbability();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double18 + "' != '" + (-1.0d) + "'", double18 == (-1.0d));
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test15");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList20);
        java.lang.String[] strArray35 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList36 = new java.util.ArrayList<java.lang.String>();
        boolean boolean37 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList36, strArray35);
        p5.DummyClass2 dummyClass2_39 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList36, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList40 = dummyClass2_39.insertionSort();
        java.util.List<java.lang.String> strList41 = dummyClass2_39.getListOfItems();
        p5.DummyClass2 dummyClass2_43 = new p5.DummyClass2("hi!", "", (int) (byte) 0, 107, strList41, (double) (byte) -1);
        dummyClass2_10.setListOfItems(strList41);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray35);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList40);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList41);
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test16");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        double double11 = dummyClass2_10.getProbability();
        java.util.List<java.lang.Integer> intList12 = dummyClass2_10.getListOfNumbers();
        dummyClass2_10.setName("35hi!351");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double11 + "' != '" + (-1.0d) + "'", double11 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test17");
        java.lang.String[] strArray11 = new java.lang.String[] { "1hi!351", "hi!", "hi!" };
        java.util.ArrayList<java.lang.String> strList12 = new java.util.ArrayList<java.lang.String>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList12, strArray11);
        p5.DummyClass2 dummyClass2_15 = new p5.DummyClass2("1hi!351", "", 100, 100, (java.util.List<java.lang.String>) strList12, (double) 0L);
        p5.DummyClass2 dummyClass2_17 = new p5.DummyClass2("hi!", "1hi!351", (int) (byte) 100, 35, (java.util.List<java.lang.String>) strList12, (double) (byte) 100);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test18");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList11 = dummyClass2_10.insertionSort();
        dummyClass2_10.setName("");
        int int14 = dummyClass2_10.getNumOperations();
        int int18 = dummyClass2_10.addToAndReturnUpdatedValue(0, (int) ' ', "1hi!351");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 1 + "'", int14 == 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 42 + "'", int18 == 42);
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test19");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        double double11 = dummyClass2_10.getProbability();
        double double12 = dummyClass2_10.getProbability();
        java.lang.String str13 = dummyClass2_10.getType();
        dummyClass2_10.setValue((int) (byte) 10);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double11 + "' != '" + (-1.0d) + "'", double11 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + (-1.0d) + "'", double12 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test20");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList20);
        java.util.List<java.lang.Integer> intList25 = dummyClass2_10.insertionSort();
        int int26 = dummyClass2_10.getNumOperations();
        java.util.List<java.lang.Integer> intList27 = dummyClass2_10.getListOfNumbers();
        java.lang.String[] strArray34 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList35 = new java.util.ArrayList<java.lang.String>();
        boolean boolean36 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList35, strArray34);
        p5.DummyClass2 dummyClass2_38 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList35, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList39 = dummyClass2_38.insertionSort();
        dummyClass2_10.setListOfNumbers(intList39);
        double double41 = dummyClass2_10.getProbability();
        dummyClass2_10.setProbability(10.0d);
        dummyClass2_10.setValue((-1));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 1 + "'", int26 == 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList27);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray34);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + true + "'", boolean36 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList39);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double41 + "' != '" + (-1.0d) + "'", double41 == (-1.0d));
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test21");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        double double11 = dummyClass2_10.getProbability();
        java.util.List<java.lang.Integer> intList12 = dummyClass2_10.getListOfNumbers();
        double double13 = dummyClass2_10.getProbability();
        java.util.List<java.lang.Integer> intList14 = dummyClass2_10.getListOfNumbers();
        dummyClass2_10.setName("hi!");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double11 + "' != '" + (-1.0d) + "'", double11 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + (-1.0d) + "'", double13 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList14);
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test22");
        java.lang.String[] strArray11 = new java.lang.String[] { "1hi!351", "hi!", "hi!" };
        java.util.ArrayList<java.lang.String> strList12 = new java.util.ArrayList<java.lang.String>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList12, strArray11);
        p5.DummyClass2 dummyClass2_15 = new p5.DummyClass2("1hi!351", "", 100, 100, (java.util.List<java.lang.String>) strList12, (double) 0L);
        p5.DummyClass2 dummyClass2_17 = new p5.DummyClass2("35hi!351", "", 207, (int) (short) 100, (java.util.List<java.lang.String>) strList12, (double) (short) 1);
        double double18 = dummyClass2_17.getProbability();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double18 + "' != '" + 1.0d + "'", double18 == 1.0d);
    }

    @Test
    public void test23() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test23");
        java.lang.String[] strArray10 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        p5.DummyClass2 dummyClass2_14 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList11, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList15 = dummyClass2_14.insertionSort();
        java.util.List<java.lang.String> strList16 = dummyClass2_14.getListOfItems();
        p5.DummyClass2 dummyClass2_18 = new p5.DummyClass2("hi!", "", (int) (byte) 0, 107, strList16, (double) (byte) -1);
        dummyClass2_18.setProbability(0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray10);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList15);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList16);
    }

    @Test
    public void test24() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test24");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList11 = dummyClass2_10.insertionSort();
        dummyClass2_10.setName("");
        dummyClass2_10.setValue(0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList11);
    }

    @Test
    public void test25() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test25");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList11 = dummyClass2_10.insertionSort();
        java.util.List<java.lang.String> strList12 = dummyClass2_10.getListOfItems();
        java.lang.Integer[] intArray23 = new java.lang.Integer[] { 42, 135, 135, 207, 0, 97, 35, 100, 135, 0 };
        java.util.ArrayList<java.lang.Integer> intList24 = new java.util.ArrayList<java.lang.Integer>();
        boolean boolean25 = java.util.Collections.addAll((java.util.Collection<java.lang.Integer>) intList24, intArray23);
        dummyClass2_10.setListOfNumbers((java.util.List<java.lang.Integer>) intList24);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intArray23);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + true + "'", boolean25 == true);
    }

    @Test
    public void test26() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test26");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList11 = dummyClass2_10.insertionSort();
        double double12 = dummyClass2_10.getDistanceOfItems();
        double double13 = dummyClass2_10.getProbability();
        int int14 = dummyClass2_10.getValue();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + 0.0d + "'", double12 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + (-1.0d) + "'", double13 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 35 + "'", int14 == 35);
    }

    @Test
    public void test27() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test27");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList11 = dummyClass2_10.insertionSort();
        double double12 = dummyClass2_10.getDistanceOfItems();
        dummyClass2_10.setNumOperations((int) ' ');
        dummyClass2_10.setType("1hi!351");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + 0.0d + "'", double12 == 0.0d);
    }

    @Test
    public void test28() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test28");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        double double11 = dummyClass2_10.getProbability();
        int int12 = dummyClass2_10.getNumOperations();
        double double13 = dummyClass2_10.getDistanceOfItems();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double11 + "' != '" + (-1.0d) + "'", double11 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 1 + "'", int12 == 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + 0.0d + "'", double13 == 0.0d);
    }

    @Test
    public void test29() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test29");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        double double11 = dummyClass2_10.getProbability();
        java.util.List<java.lang.Integer> intList12 = dummyClass2_10.getListOfNumbers();
        dummyClass2_10.setNumOperations(97);
        int int18 = dummyClass2_10.addToAndReturnUpdatedValue(100, (int) (short) 10, "hi!");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double11 + "' != '" + (-1.0d) + "'", double11 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int18 + "' != '" + 138 + "'", int18 == 138);
    }

    @Test
    public void test30() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test30");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("hi!");
        int int13 = dummyClass2_10.getValue();
        java.lang.String[] strArray20 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList21 = new java.util.ArrayList<java.lang.String>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList21, strArray20);
        p5.DummyClass2 dummyClass2_24 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList21, (double) (-1.0f));
        dummyClass2_24.setName("");
        java.lang.String[] strArray33 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList34 = new java.util.ArrayList<java.lang.String>();
        boolean boolean35 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList34, strArray33);
        p5.DummyClass2 dummyClass2_37 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList34, (double) (-1.0f));
        dummyClass2_24.setListOfItems((java.util.List<java.lang.String>) strList34);
        java.util.List<java.lang.Integer> intList39 = dummyClass2_24.insertionSort();
        dummyClass2_10.setListOfNumbers(intList39);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 35 + "'", int13 == 35);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray20);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray33);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + true + "'", boolean35 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList39);
    }

    @Test
    public void test31() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test31");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        double double11 = dummyClass2_10.getProbability();
        double double12 = dummyClass2_10.getProbability();
        java.lang.String str13 = dummyClass2_10.getType();
        int int14 = dummyClass2_10.getValue();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double11 + "' != '" + (-1.0d) + "'", double11 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + (-1.0d) + "'", double12 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str13 + "' != '" + "hi!" + "'", str13.equals("hi!"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 35 + "'", int14 == 35);
    }

    @Test
    public void test32() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test32");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        int int14 = dummyClass2_10.addToAndReturnUpdatedValue((int) (short) 100, (int) (short) 100, "");
        dummyClass2_10.setName("hi!");
        dummyClass2_10.setNumOperations(135);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 135 + "'", int14 == 135);
    }

    @Test
    public void test33() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test33");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList11 = dummyClass2_10.insertionSort();
        dummyClass2_10.setName("");
        java.lang.String str16 = dummyClass2_10.getInterestingItem("", 1);
        java.lang.String str19 = dummyClass2_10.getInterestingItem("", (int) '4');
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1hi!351" + "'", str16.equals("1hi!351"));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str19 + "' != '" + "52hi!351" + "'", str19.equals("52hi!351"));
    }

    @Test
    public void test34() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test34");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("hi!");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        double double24 = dummyClass2_23.getProbability();
        java.util.List<java.lang.Integer> intList25 = dummyClass2_23.getListOfNumbers();
        double double26 = dummyClass2_23.getProbability();
        java.util.List<java.lang.Integer> intList27 = dummyClass2_23.getListOfNumbers();
        dummyClass2_10.setListOfNumbers(intList27);
        java.lang.String[] strArray35 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList36 = new java.util.ArrayList<java.lang.String>();
        boolean boolean37 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList36, strArray35);
        p5.DummyClass2 dummyClass2_39 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList36, (double) (-1.0f));
        dummyClass2_39.setName("hi!");
        java.lang.String[] strArray48 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList49 = new java.util.ArrayList<java.lang.String>();
        boolean boolean50 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList49, strArray48);
        p5.DummyClass2 dummyClass2_52 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList49, (double) (-1.0f));
        dummyClass2_52.setName("");
        java.lang.String[] strArray61 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList62 = new java.util.ArrayList<java.lang.String>();
        boolean boolean63 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList62, strArray61);
        p5.DummyClass2 dummyClass2_65 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList62, (double) (-1.0f));
        dummyClass2_52.setListOfItems((java.util.List<java.lang.String>) strList62);
        java.util.List<java.lang.Integer> intList67 = dummyClass2_52.insertionSort();
        int int68 = dummyClass2_52.getNumOperations();
        java.util.List<java.lang.String> strList69 = dummyClass2_52.getListOfItems();
        dummyClass2_39.setListOfItems(strList69);
        dummyClass2_10.setListOfItems(strList69);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double24 + "' != '" + (-1.0d) + "'", double24 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double26 + "' != '" + (-1.0d) + "'", double26 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList27);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray35);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray48);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + true + "'", boolean50 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray61);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean63 + "' != '" + true + "'", boolean63 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList67);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int68 + "' != '" + 1 + "'", int68 == 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList69);
    }

    @Test
    public void test35() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test35");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setNumOperations((int) (short) 0);
        dummyClass2_10.setType("hi!");
        java.util.List<java.lang.String> strList15 = dummyClass2_10.getListOfItems();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList15);
    }

    @Test
    public void test36() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test36");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        int int14 = dummyClass2_10.addToAndReturnUpdatedValue((int) (short) 100, (int) (short) 100, "");
        java.util.List<java.lang.Integer> intList15 = dummyClass2_10.insertionSort();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 135 + "'", int14 == 135);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList15);
    }

    @Test
    public void test37() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test37");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        double double11 = dummyClass2_10.getProbability();
        java.util.List<java.lang.Integer> intList12 = dummyClass2_10.getListOfNumbers();
        double double13 = dummyClass2_10.getProbability();
        int int14 = dummyClass2_10.getValue();
        java.util.List<java.lang.Integer> intList15 = dummyClass2_10.getListOfNumbers();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double11 + "' != '" + (-1.0d) + "'", double11 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + (-1.0d) + "'", double13 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 35 + "'", int14 == 35);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList15);
    }

    @Test
    public void test38() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test38");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList20);
        java.util.List<java.lang.Integer> intList25 = dummyClass2_10.insertionSort();
        dummyClass2_10.setValue((int) 'a');
        int int28 = dummyClass2_10.getValue();
        int int32 = dummyClass2_10.addToAndReturnUpdatedValue(10, (int) (short) -1, "");
        java.lang.String str35 = dummyClass2_10.getInterestingItem("1hi!351", 138);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 97 + "'", int28 == 97);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int32 + "' != '" + 107 + "'", int32 == 107);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str35 + "' != '" + "138hi!1071" + "'", str35.equals("138hi!1071"));
    }

    @Test
    public void test39() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test39");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("hi!");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_23.setName("");
        java.lang.String[] strArray32 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList33 = new java.util.ArrayList<java.lang.String>();
        boolean boolean34 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList33, strArray32);
        p5.DummyClass2 dummyClass2_36 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList33, (double) (-1.0f));
        dummyClass2_23.setListOfItems((java.util.List<java.lang.String>) strList33);
        java.util.List<java.lang.Integer> intList38 = dummyClass2_23.insertionSort();
        int int39 = dummyClass2_23.getNumOperations();
        java.util.List<java.lang.String> strList40 = dummyClass2_23.getListOfItems();
        dummyClass2_10.setListOfItems(strList40);
        dummyClass2_10.setName("1hi!351");
        int int44 = dummyClass2_10.getValue();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray32);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList38);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int39 + "' != '" + 1 + "'", int39 == 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList40);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int44 + "' != '" + 35 + "'", int44 == 35);
    }

    @Test
    public void test40() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test40");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        java.util.List<java.lang.Integer> intList11 = dummyClass2_10.insertionSort();
        double double12 = dummyClass2_10.getDistanceOfItems();
        double double13 = dummyClass2_10.getProbability();
        java.lang.String str14 = dummyClass2_10.getType();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList11);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double12 + "' != '" + 0.0d + "'", double12 == 0.0d);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double13 + "' != '" + (-1.0d) + "'", double13 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str14 + "' != '" + "hi!" + "'", str14.equals("hi!"));
    }

    @Test
    public void test41() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test41");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        double double11 = dummyClass2_10.getProbability();
        java.util.List<java.lang.Integer> intList12 = dummyClass2_10.getListOfNumbers();
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        double double24 = dummyClass2_23.getProbability();
        java.util.List<java.lang.Integer> intList25 = dummyClass2_23.getListOfNumbers();
        dummyClass2_10.setListOfNumbers(intList25);
        dummyClass2_10.setName("hi!");
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double11 + "' != '" + (-1.0d) + "'", double11 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList12);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double24 + "' != '" + (-1.0d) + "'", double24 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList25);
    }

    @Test
    public void test42() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test42");
        java.lang.String[] strArray10 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        p5.DummyClass2 dummyClass2_14 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList11, (double) (-1.0f));
        dummyClass2_14.setName("");
        java.lang.String[] strArray23 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList24 = new java.util.ArrayList<java.lang.String>();
        boolean boolean25 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList24, strArray23);
        p5.DummyClass2 dummyClass2_27 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList24, (double) (-1.0f));
        dummyClass2_14.setListOfItems((java.util.List<java.lang.String>) strList24);
        p5.DummyClass2 dummyClass2_30 = new p5.DummyClass2("35hi!351", "", (int) '#', (int) (byte) 10, (java.util.List<java.lang.String>) strList24, 0.0d);
        dummyClass2_30.setName("");
        java.lang.String str33 = dummyClass2_30.getName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray10);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray23);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + true + "'", boolean25 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str33 + "' != '" + "" + "'", str33.equals(""));
    }

    @Test
    public void test43() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test43");
        java.lang.String[] strArray10 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        p5.DummyClass2 dummyClass2_14 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList11, (double) (-1.0f));
        p5.DummyClass2 dummyClass2_16 = new p5.DummyClass2("1hi!351", "35hi!351", (int) (byte) 10, 100, (java.util.List<java.lang.String>) strList11, (double) (byte) 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray10);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test44() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test44");
        java.lang.String[] strArray10 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        p5.DummyClass2 dummyClass2_14 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList11, (double) (-1.0f));
        double double15 = dummyClass2_14.getProbability();
        int int16 = dummyClass2_14.getNumOperations();
        java.util.List<java.lang.String> strList17 = dummyClass2_14.getListOfItems();
        p5.DummyClass2 dummyClass2_19 = new p5.DummyClass2("1hi!351", "35hi!351", 35, (int) (byte) 10, strList17, (double) (short) 10);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray10);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + double15 + "' != '" + (-1.0d) + "'", double15 == (-1.0d));
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 1 + "'", int16 == 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strList17);
    }

    @Test
    public void test45() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test45");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setNumOperations((int) (short) 0);
        int int13 = dummyClass2_10.getNumOperations();
        dummyClass2_10.setName("hi!");
        int int16 = dummyClass2_10.getNumOperations();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 0 + "'", int13 == 0);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 0 + "'", int16 == 0);
    }

    @Test
    public void test46() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test46");
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        p5.DummyClass2 dummyClass2_10 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList7, (double) (-1.0f));
        dummyClass2_10.setName("");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        p5.DummyClass2 dummyClass2_23 = new p5.DummyClass2("hi!", "hi!", (int) '#', (int) (short) 1, (java.util.List<java.lang.String>) strList20, (double) (-1.0f));
        dummyClass2_10.setListOfItems((java.util.List<java.lang.String>) strList20);
        java.util.List<java.lang.Integer> intList25 = dummyClass2_10.insertionSort();
        int int26 = dummyClass2_10.getNumOperations();
        java.util.List<java.lang.Integer> intList27 = dummyClass2_10.getListOfNumbers();
        dummyClass2_10.setNumOperations((int) (byte) 100);
        java.lang.String str30 = dummyClass2_10.getName();
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray6);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(strArray19);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList25);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 1 + "'", int26 == 1);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertNotNull(intList27);
        // Regression assertion (captures the current behavior of the code)
        org.junit.Assert.assertTrue("'" + str30 + "' != '" + "" + "'", str30.equals(""));
    }
}
