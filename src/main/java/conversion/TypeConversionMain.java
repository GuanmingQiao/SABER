package conversion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;

import com.thoughtworks.xstream.XStream;

import pojo.IORecord;
import sim.IOSimilarity;
import xml.XMLTools;
import xml.XMLWrapper;

public class TypeConversionMain {
	public static void main(String[] args) {
		List<Object> abcd = new ArrayList<Object>();
		abcd.add("Hello");
		abcd.add(4);
		System.out.println(abcd.getClass().getName());
		for(Object x : abcd)
			System.out.println(x.getClass().getName());

		Queue x = new PriorityQueue<String>();
		System.out.println(x.getClass().getCanonicalName());

		ArrayList<String>[] abc = new ArrayList[10];
		System.out.println(abc.getClass().getCanonicalName());

		int[][][][] abcde = new int[10][43][23][133];
		String abcdeName = abcde.getClass().getCanonicalName();
		System.out.println(abcdeName);
		System.out.println("Number of occurrences of [] in abcde: " + TypeConversion.countOccurrences(abcdeName, "[]"));		

		String r = "hello";
		int y = 7;		

		Map<Integer, String> ab = new HashMap<>();
		ab.put(23, "hello");
		Set<Entry<Integer, String>> wxyz = ab.entrySet();
		for(Entry r1 : wxyz) {
			System.out.println(r1.getClass().getSuperclass().getCanonicalName());
		}

		//		String newR = r.getClass().cast(y);
		//		System.out.println("newR value: " + newR + ", type: " + newR.getClass().getCanonicalName());

		//		List<Integer> a = new ArrayList<>();
		//		a.add(138);
		//		a.add(23);
		//		a.add(82);
		//		a.add(1);
		//		a.add(91);
		//		a.add(19);
		//		a.add(125);
		//		a.add(7);
		//		List<String> b = Lists.transform(a, s -> String.valueOf(s));
		//		System.out.println(a);
		//		System.out.println(b);
		//		System.out.println();
		//		
		//		List<String> c = new ArrayList<>();
		//		c.add("138");
		//		c.add("23");
		//		c.add("82");
		//		c.add("1");
		//		c.add("91");
		//		c.add("19");
		//		c.add("125");
		//		c.add("7");
		//		List<Integer> d = Lists.transform(c, Integer::parseInt);
		//		List<Double> d2 = Lists.transform(c, Double::parseDouble);
		//		System.out.println(c);
		//		System.out.println(d);
		//		System.out.println(d2);
		//		System.out.println();
		//				
		//		Map<String, Double> abc = new HashMap<>();
		//		TreeMap<Double, Integer> def = new TreeMap<>();
		//		
		//		def.put(4.2, 8);
		//		def.put(14.2, 823);
		//		def.put(14.0, 29);
		//		def.put(12., -12);
		//		def.put(2.9, 0);
		//		def.put(9.2, 10012);

		//		Maps.transformValues(fromMap, function)

		Map.Entry<String, Object> entry2 = new MyEntry<String, Object>("Hello", 123);
		System.out.println("MyEntry entry class name: " + entry2.getClass().getSuperclass().getCanonicalName());

		List<Object> a = new ArrayList<>();
		a.add("Hello");
		a.add("World");
		a.add("How");
		a.add("Are");
		a.add("You");
		a.add("Doing");
		//		List<List<Object>> permutations = getPermutations(a, 8);
		//		System.out.println("Original list: \n" + a);
		//		System.out.println();
		//		System.out.println("Number of permutations: " + permutations.size());
		//		System.out.println();
		//		System.out.println("Permutations:");
		//		for(int i = 0; i < permutations.size(); i++)
		//			System.out.println(permutations.get(i));

		//		Byte byteB = 123;
		//		Integer bb = Integer.parseInt(byteB.toString());
		//		System.out.println(bb + " " + bb.getClass().getCanonicalName());

		Double d = 12.238;
		System.out.println(d.intValue());

		Character ax = 't';
		System.out.println(((int) ax));

		HashMap aMap = new HashMap<>();
		//		Map bMap = (TreeMap) aMap;
		Map cMap = aMap;
		Map dMap = (Map) aMap;
		System.out.println("aMap type: " + aMap.getClass().getCanonicalName() + " " + aMap.getClass().getDeclaredMethods().length);
		//		System.out.println("bMap type: " + bMap.getClass().getCanonicalName());
		System.out.println("cMap type: " + cMap.getClass().getCanonicalName() + " " + cMap.getClass().getDeclaredMethods().length);		

		Object aObj = new A();        
		B b1 = new B();
		b1.show();
		
		C cObj = new C();

		// casts object
//		Object aObj = A.class.cast(b1);
//		((A) aObj).show();
//		
//		System.out.println(obj.getClass());
//		System.out.println(b1.getClass());
//		System.out.println(aObj.getClass()); 

		
		ArrayList newList = new ArrayList();
		newList.add("Hello");
		newList.add(4);
//		String typeOfList = newList.getClass().getCanonicalName();
//		List newVector = new Vector();
//		newVector.add("Hello");
//		newVector.add(4);
//		Vector improvedVector = (Vector) convertString("hello", newVector);
//		
//		System.out.println(newVector + " " + newVector.getClass().getCanonicalName());
//		System.out.println(improvedVector + " " + improvedVector.getClass().getCanonicalName());
		
		String xStr = "1";
		ArrayList<Integer> newArrayList = (ArrayList<Integer>) convertString(xStr, newList);
		System.out.println(newArrayList.get(0) + " " + newArrayList.get(0).getClass().getCanonicalName());		
		
		Serializable ser = new HashMap<>();
		
		Map map1 = new HashMap<>();
		TreeMap map2 = new TreeMap<>();
		ArrayList a2 = new ArrayList<>();

		System.out.println("Map 1 interfaces: " + map1.getClass().getInterfaces()[0].getCanonicalName());
		System.out.println("Map 1 superclass: " + map1.getClass().getSuperclass().getCanonicalName());
		System.out.println("Map 2 interfaces: " + map2.getClass().getInterfaces()[0].getCanonicalName());
		System.out.println("Map 1 superclass: " + map1.getClass().getSuperclass().getCanonicalName());
		
		List<Class<?>> listOfClasses1 = new ArrayList<>();
		listOfClasses1.add(map1.getClass());
		listOfClasses1.add(map2.getClass());
		List<Class<?>> listOfClasses2 = new ArrayList<>();
		listOfClasses2.add(map1.getClass());
		listOfClasses2.add(a2.getClass());
		List<Class<?>> listOfClasses3 = new ArrayList<>();
		listOfClasses3.add(aObj.getClass());
		listOfClasses3.add(b1.getClass());
		List<Class<?>> listOfClasses4 = new ArrayList<>();
		listOfClasses4.add(aObj.getClass());
		listOfClasses4.add(aObj.getClass());
		List<Class<?>> listOfClasses5 = new ArrayList<>();
		listOfClasses5.add(b1.getClass());
		listOfClasses5.add(cObj.getClass());
		List<Class<?>> listOfClasses6 = new ArrayList<>();
		listOfClasses6.add(new Object().getClass());
		listOfClasses6.add(new Object().getClass());
		
		System.out.println();
//		System.out.println("Common ancestors of Map and TreeMap: " + TypeConversion.getCommonAncestors(listOfClasses1).get(0).getCanonicalName());
//		System.out.println("Common ancestors of Map and ArrayList: " + TypeConversion.getCommonAncestors(listOfClasses2).get(0).getCanonicalName());
//		System.out.println("Common ancestors of A and B: " + TypeConversion.getCommonAncestors(listOfClasses3).get(0).getCanonicalName());
//		System.out.println("Common ancestors of A and A: " + TypeConversion.getCommonAncestors(listOfClasses4).get(0).getCanonicalName());
//		System.out.println("Common ancestors of B and C: " + TypeConversion.getCommonAncestors(listOfClasses4).get(0).getCanonicalName());
		System.out.println("Common ancestors of HashMap and TreeMap:");
		System.out.println("\tAncestral walk factor: " + IOSimilarity.ancestralWalk(map1, map2));
		System.out.println("\t" + TypeConversion.getCommonAncestorMap(listOfClasses1, true));
		System.out.println("Common ancestors of HashMap and ArrayList:");
		System.out.println("\tAncestral walk factor: " + IOSimilarity.ancestralWalk(map1, a2));
		System.out.println("\t" + TypeConversion.getCommonAncestorMap(listOfClasses2, true));
		System.out.println("Common ancestors of A and B:");
		System.out.println("\tAncestral walk factor: " + IOSimilarity.ancestralWalk(aObj, b1));
		System.out.println("\t" + TypeConversion.getCommonAncestorMap(listOfClasses3, true));
		System.out.println("Common ancestors of A and A:");
		System.out.println("\tAncestral walk factor: " + IOSimilarity.ancestralWalk(aObj, aObj));
		System.out.println("\t" + TypeConversion.getCommonAncestorMap(listOfClasses4, true));
		System.out.println("Common ancestors of B and C:");
		System.out.println("\tAncestral walk factor: " + IOSimilarity.ancestralWalk(aObj, cObj));
		System.out.println("\t" + TypeConversion.getCommonAncestorMap(listOfClasses5, true));
		System.out.println("Common ancestors of Object and Object:");
		System.out.println("\tAncestral walk factor: " + IOSimilarity.ancestralWalk(new Object(), new Object()));
		System.out.println("\t" + TypeConversion.getCommonAncestorMap(listOfClasses6, true));
		System.out.println();
		
		System.out.println("**********************************");
		HashMap<String, Integer> map1a = new HashMap<>();
		Map<String, Integer> map1b = (Map<String, Integer>) map1a;
		Map<String, Integer> map2a = new HashMap<>();
		HashMap<String, Integer> map2b = (HashMap<String, Integer>) map2a;
		
		
		ArrayList<String> abc1 = new ArrayList<>();
		abc1.add("foo");
		abc1.add("bar");		
		
		Vector<String> v = new Vector<>();
		v.add("Hello");
		v.add("World");
		
		Object abc2 = (Object) abc1;
		Object v2 = (Object) v;
		
		List<Object> vwxyz = null, vwxyz2 = null, v3 = null;
		try {
			vwxyz = (List<Object>) TypeConversion.deepCopy(abc2);
			vwxyz2 = (List<Object>) TypeConversion.deepCopy(v2);
			vwxyz2.clear();
			vwxyz2.addAll(vwxyz);
			v3 = (Vector) vwxyz2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Contents of v: " + v);
		System.out.println("Type of v: " + v.getClass().getCanonicalName());		
		System.out.println("Contents of v2: " + v2);
		System.out.println("Type of v2: " + v2.getClass().getCanonicalName());		
		System.out.println("Contents of abc1: " + abc1);
		System.out.println("Type of abc1: " + abc1.getClass().getCanonicalName());		
		System.out.println("Contents of abc2: " + abc2);
		System.out.println("Type of abc2: " + abc2.getClass().getCanonicalName());		
		System.out.println("Contents of vwxyz: " + vwxyz);
		System.out.println("Type of vwxyz: " + vwxyz.getClass().getCanonicalName());
		System.out.println("Contents of vwxyz2: " + vwxyz2);
		System.out.println("Type of vwxyz2: " + vwxyz2.getClass().getCanonicalName());
		System.out.println("Contents of v3: " + v3);
		System.out.println("Type of v3: " + v3.getClass().getCanonicalName());		
		System.out.println("Contents of v: " + v);
		System.out.println("Type of v: " + v.getClass().getCanonicalName());		
		System.out.println("Contents of v2: " + v2);
		System.out.println("Type of v2: " + v2.getClass().getCanonicalName());
		System.out.println("Contents of abc1: " + abc1);
		System.out.println("Type of abc1: " + abc1.getClass().getCanonicalName());		
		System.out.println("Contents of abc2: " + abc2);
		System.out.println("Type of abc2: " + abc2.getClass().getCanonicalName());
		
		System.out.println();
		Set<String> aSet = new HashSet<>();
		aSet.add("Hello");
		Set<Object> aSet2;
		try {
			aSet2 = (Set<Object>) TypeConversion.deepCopy(aSet);
			System.out.println(aSet + " " + aSet.getClass().getCanonicalName() + "/" + new ArrayList(aSet).get(0).getClass().getCanonicalName());
			System.out.println(aSet2 + " " + aSet2.getClass().getCanonicalName() + "/" + new ArrayList(aSet2).get(0).getClass().getCanonicalName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
		List<List<Object>> bigList1 = new ArrayList<>();
		Vector<Object> sublist1 = new Vector<>();
		sublist1.add("Tiger");
		sublist1.add("The sky is blue");
		bigList1.add(sublist1);
		List<Object> sublist2 = new ArrayList<>();
		List<Integer> listObjInSublist2 = new ArrayList<>();
		listObjInSublist2.add(138);
		listObjInSublist2.add(82);
		listObjInSublist2.add(1);
		listObjInSublist2.add(19);
		listObjInSublist2.add(125);
		listObjInSublist2.add(7);
		sublist2.add(listObjInSublist2);
		sublist2.add("Columbia is an Ivy League university");
		sublist2.add(123.83);
		sublist2.add(new Long(1231910));
		sublist2.add(new XMLWrapper(new A()));
		sublist2.add(new XMLWrapper(new B()));
		bigList1.add(sublist2);
		List<Object> bigList2 = new ArrayList<>();
		bigList2.add("Beautiful day");
		bigList2.add("Football");
		bigList2.add("");
		bigList2.add(new XMLWrapper(new C()));
		
		IORecord ioRecord1 = new IORecord();
		ioRecord1.id = 1;
		ioRecord1.methodKey = "some random method";
		ioRecord1.sortedInputs.add(new Short((short) 123));
		ioRecord1.sortedInputs.add(bigList1);
		
		IORecord ioRecord2 = new IORecord();
		ioRecord2.id = 2;
		ioRecord2.methodKey = "another random method";
		ioRecord2.sortedInputs.add(bigList2);

//		System.out.println("IO Record " + ioRecord1.id);
//		System.out.println("   Method: " + ioRecord1.methodKey);
//		System.out.println("   Inputs: " + ioRecord1.sortedInputs);
//		System.out.println("   Outputs: " + ioRecord1.sortedOutputs);
//		System.out.println("IO Record " + ioRecord2.id);
//		System.out.println("   Method: " + ioRecord2.methodKey);
//		System.out.println("   Inputs: " + ioRecord2. sortedInputs);
//		System.out.println("   Outputs: " + ioRecord2.sortedOutputs);
		
		IORecord ioRecord3 = new IORecord();
		ioRecord3.id = 1;
		ioRecord3.methodKey = "some random method";
		ioRecord3.sortedInputs.add(new XMLWrapper(new A()));
		
		IORecord ioRecord4 = new IORecord();
		ioRecord4.id = 2;
		ioRecord4.methodKey = "another random method";
		ioRecord4.sortedInputs.add(new XMLWrapper(new C()));
		
		IORecord ioRecord5 = new IORecord();
		ioRecord5.id = 3;
		ioRecord5.methodKey = "yet another random method";
		ioRecord5.sortedInputs.add(new XMLWrapper(new D()));

		System.out.println("IO Record " + ioRecord4.id);
		System.out.println("   Method: " + ioRecord4.methodKey);
		System.out.println("   Inputs: " + ioRecord4.sortedInputs);
		System.out.println("   Outputs: " + ioRecord4.sortedOutputs);
		System.out.println("IO Record " + ioRecord5.id);
		System.out.println("   Method: " + ioRecord5.methodKey);
		System.out.println("   Inputs: " + ioRecord5. sortedInputs);
		System.out.println("   Outputs: " + ioRecord5.sortedOutputs);

//		TypeConversion tc1 = new TypeConversion(ioRecord1, ioRecord2);
//		TypeConversion tc1 = new TypeConversion(ioRecord4, ioRecord5);
//		Map<IORecord, List<IORecord>> recordsMap1 = tc1.getGeneratedRecordsMap();
//		String originalIORecordsFilename = "/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/src/ioRepoFiles/originalIORecords/ioRecord";
//		String newIORecordsFilename = "/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/src/ioRepoFiles/newIORecords/newIORecord";
//		int numGeneratedIORecords = 0;
//		int numOriginalIORecords = 0;
//		try {
//			XStream xstream = XMLTools.getXStream();
//
//			for(IORecord ioRecordKey : recordsMap1.keySet()) {
//		        File file1 = new File(originalIORecordsFilename + numOriginalIORecords);
//				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1, false));
//				bufferedWriter.write(xstream.toXML(ioRecordKey));
//				bufferedWriter.close();
//				numOriginalIORecords++;
//				for(IORecord generatedIORecord : recordsMap1.get(ioRecordKey)) {
//			        File newFile = new File(newIORecordsFilename + numGeneratedIORecords);
//					bufferedWriter = new BufferedWriter(new FileWriter(newFile, false));
//					bufferedWriter.write(xstream.toXML(generatedIORecord));
//					bufferedWriter.close();
//					numGeneratedIORecords++;
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public static Object convertString(String str, Object obj) {
		List<Object> list = (List<Object>) obj;
		Object o = list.get(0);
		list.clear();
		list.add(convertString2(str, o));
		return list;
	}
	
	public static Object convertString2(String str, Object obj) {
		try {
			return Integer.parseInt(str);
		} catch(Exception e) {
			return (Integer) 0;
		}
	}
	
	static final class MyEntry<K, V> implements Map.Entry<K, V> {
		private final K key;
		private V value;

		public MyEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V old = this.value;
			this.value = value;
			return old;
		}
	}

	static class C implements Serializable {
		
		private List<Integer> cListOfIntegers = Arrays.asList(new Integer[] {12, 43, 811, 230});
		private String cSomeString = "hey there";
		private double cSomeDouble;
		private double cSomeOtherDouble = 2.6;
		
		public static void show() {
			System.out.println("Class C show() function");
		}
	}	
	
	static class A extends C {
		private List<String> aListOfStrings = Arrays.asList(new String[] {"Goodbye", "Never say never", "Trees are cool"});
		private short aShort = 2;

		public static void show() {
			System.out.println("Class A show() function");
		}
	}

	static class B extends C {
		private Set<Integer> cListOfStrings = new HashSet<>(Arrays.asList(new Integer[] {1981, 182}));
		private int bSomeInteger = 1202;

		public static void show() {
			System.out.println("Class B show() function");
		}
	}

	static class D implements Serializable {
		private List<String> dListOfStrings = new ArrayList<>(Arrays.asList(new String[] {"Goodbye", "Never say never", "Trees are cool"}));
		private int dInt = 1;
		private double dDouble = 1.0;
		private String dString = "I like pie";
		private Integer dInteger = 23;
		private Double dDouble2 = 345.219;
//		private int[] dInts = {21, 9, 230, 43, 4};
		private short dShort = 2;
		private C cObj = new C();

		public static void show() {
			System.out.println("Class D show() function");
		}
	}
}