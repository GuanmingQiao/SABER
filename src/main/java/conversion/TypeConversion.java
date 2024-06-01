package conversion;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.github.javaparser.utils.Pair;

import pojo.IORecord;
import xml.XMLWrapper;

// Structures/Types: Object, Integer, String, Double, Character, Float, Byte, Long, Short, Map, List, Set, Array, Tree, Stack, Queue 

public class TypeConversion {

	private IORecord ioRecord1, ioRecord2;
	private List<Object> ioRecord1Inputs = new ArrayList<>();
	private List<Object> ioRecord2Inputs = new ArrayList<>();
	private Pair<IORecord, List<IORecord>> generatedRecordsPair = null;
	private static final int COMBINATION_INPUT_SIZE_THRESHOLD = 10;
	private static final int PERMUTATION_INPUT_SIZE_THRESHOLD = 10;

	public TypeConversion(IORecord ioRecord1, IORecord ioRecord2) {
		setIORecords(ioRecord1, ioRecord2);
		generateNewIORecords();
	}

	public void setIORecords(IORecord ioRecord1, IORecord ioRecord2) {
		generatedRecordsPair = new Pair<IORecord, List<IORecord>>(ioRecord1, new ArrayList<>());
		this.ioRecord1 = ioRecord1;
		this.ioRecord2 = ioRecord2;
		ioRecord1Inputs = ioRecord1.sortedInputs;
		ioRecord2Inputs = ioRecord2.sortedInputs;
	}

	public List<IORecord> getIORecords() {
		List<IORecord> ioRecords = new ArrayList<>();
		ioRecords.add(ioRecord1);
		ioRecords.add(ioRecord2);

		return ioRecords;
	}

	/*
	 * Generate new IO records based on different permutations of the values of IO record 2's 
	 * inputs and conversions to the types of IO record 1's inputs.
	 */
	public void generateNewIORecords() {
		List<Object> clonedIORecord1Inputs = (List<Object>) (((ArrayList) ioRecord1Inputs).clone());
		List<Object> clonedIORecord2Inputs = (List<Object>) (((ArrayList) ioRecord2Inputs).clone());
		int ir1InputsSize = clonedIORecord1Inputs.size();

		List<List<Object>> ioRecord2Permutations = getPermutations(clonedIORecord2Inputs, ir1InputsSize);
		printPermutations("IO record " + ioRecord2.id, ioRecord2Permutations);
		for(List<Object> ioRecord2Permutation : ioRecord2Permutations) {
			List<Object> newInputs = new ArrayList<>();
			for(int i = 0; i < ioRecord2Permutation.size(); i++) {
				if(ioRecord2Permutation.get(i) == null) {
					newInputs.add(clonedIORecord1Inputs.get(i));
					continue;
				}
				Object newInput = convert(ioRecord2Permutation.get(i), clonedIORecord1Inputs.get(i));
				if(newInput != null)
					newInputs.add(newInput);
				else
					newInputs.add(clonedIORecord1Inputs.get(i));
			}
			
			List<List<Object>> inputCombinations = new ArrayList<>();
			analyzeGroups(inputCombinations, newInputs, new ArrayList<>(), 0);

			// An IO record can be created if the inputs do not already exist or do not match the original
			for(List<Object> inputCombination : inputCombinations) {			
				IORecord newIORecord = new IORecord();
				newIORecord.methodKey = ioRecord1.methodKey;
				newIORecord.stackInfo = ioRecord1.stackInfo;
				newIORecord.id = ioRecord1.id;
				newIORecord.sortedInputs = new ArrayList<>(inputCombination);
				newIORecord.stamp = "SABER_" + ioRecord2.stamp + "_" + ioRecord1.methodKey.substring(
						ioRecord1.methodKey.indexOf("-") + 1, ioRecord1.methodKey.indexOf("(")) +
						"_" + (generatedRecordsPair.b.size() + 1); 
				
				boolean newIORecordExists = false;
				if(ioRecord1.sortedInputs.equals(inputCombination))
					newIORecordExists = true;
				else {
					for(IORecord existingIORecord : generatedRecordsPair.b) {
						if(existingIORecord.sortedInputs.equals(inputCombination)) {
							newIORecordExists = true;
							break;
						}
					}
				}
				
				if(!newIORecordExists)
					generatedRecordsPair.b.add(newIORecord);
			}
		}
	}
	
	/*
	 * Recursively determine all input sets after analyzing existing groups
	 */
	public static void analyzeGroups(List<List<Object>> inputCombinations, List<Object> inputs, List<Object> currList, int listNum) {
		if(listNum == inputs.size())
			inputCombinations.add(currList);
		else {
			Object input = inputs.get(listNum);
			int newListNum = listNum + 1;
			if(input.getClass().getCanonicalName().equals("conversion.Group")) {
				Group group = (Group) input;
				for(int i = 0; i < group.size(); i++) {
					List<Object> newList = new ArrayList<>(currList);
					newList.add(new ArrayList<>(group.getConversions()).get(i));
					analyzeGroups(inputCombinations, inputs, newList, newListNum);					
				}
			}
			else {
				List<Object> newList = new ArrayList<>(currList);
				newList.add(input);
				analyzeGroups(inputCombinations, inputs, newList, newListNum);
			}
		}
	}

	public static List<List<Object>> getPermutations(List<Object> objects, int r) {
		List<List<Object>> permutations = new ArrayList<>();
		List<List<Object>> combinations = new ArrayList<>();
		List<Object> newObjects = new ArrayList<>(objects);
		while(newObjects.size() < r)
			newObjects.add(null);
		generateCombinations(newObjects, 0, combinations, new ArrayList<>(), r, 0, 0);
		for(List<Object> combination : combinations) {
			List<List<Object>> orders = getAllOrders(combination, 0);
			permutations.addAll(orders);
		}
		return permutations;
	}

	public static void generateCombinations(List<Object> set, int pos, List<List<Object>> combinations, List<Object> result, int r, int resultPos, int numCombinationCalls) {
		if ((resultPos == r && !combinations.contains(result)) || numCombinationCalls > COMBINATION_INPUT_SIZE_THRESHOLD) {
			combinations.add(result);
			return;
		}
		if (pos >= set.size())
			return;

		while(result.size() < r)
			result.add(null);
		result.set(resultPos, set.get(pos));

		List<Object> result1 = new ArrayList<>();
		List<Object> result2 = new ArrayList<>();
		result1.addAll(result);
		result2.addAll(result);

		numCombinationCalls++;
		generateCombinations(set, pos + 1, combinations, result1, r, resultPos + 1, numCombinationCalls);
		generateCombinations(set, pos + 1, combinations, result2, r, resultPos, numCombinationCalls);
	}

	public static List<List<Object>> getAllOrders(List<Object> original, int numOrderCalls) {
		if (original.size() == 0) {
			List<List<Object>> result = new ArrayList<List<Object>>(); 
			result.add(new ArrayList<Object>()); 
			return result;
		}
		Object firstElement = original.remove(0);
		List<List<Object>> returnValue = new ArrayList<List<Object>>();
		if(numOrderCalls > PERMUTATION_INPUT_SIZE_THRESHOLD) {
			returnValue.add(original);
			return returnValue;
		}
		List<List<Object>> orders = getAllOrders(original, numOrderCalls++);
		for (List<Object> order : orders) {
			for (int i = 0; i <= order.size(); i++) {
				List<Object> temp = new ArrayList<>(order);
				temp.add(i, firstElement);
				if(!returnValue.contains(temp))
					returnValue.add(temp);
			}
		}
		return returnValue;
	}

	/*
	 * Convert obj1 to another object with the type of obj2, i.e.,
	 * create an object like obj2 that has obj1's type-converted value.
	 */
	public static Object convert(Object obj1, Object obj2) {		
		String type = obj2.getClass().getCanonicalName();
		String typeWithoutArraySuffix = type.split("\\[\\]")[0];
		int arrayDimension = countOccurrences(type, "[]");
		
		if(arrayDimension == 0) {
			switch(typeWithoutArraySuffix) {
			case "java.lang.Boolean":
			case "boolean":
				return new BooleanType().getObject(obj1, (Boolean) obj2);
			case "java.lang.Byte":
			case "byte":
				return new ByteType().getObject(obj1, (Byte) obj2);
			case "java.lang.Character":
			case "char":
				return new CharacterType().getObject(obj1, (Character) obj2);
			case "java.lang.Double":
			case "double":
				return new DoubleType().getObject(obj1, (Double) obj2);
			case "java.lang.Float":
			case "float":
				return new FloatType().getObject(obj1, (Float) obj2);
			case "java.lang.Integer":
			case "int":
				return new IntegerType().getObject(obj1, (Integer) obj2);
			case "java.lang.Long":
			case "long":
				return new LongType().getObject(obj1, (Long) obj2);
			case "java.lang.Short":
			case "short":
				return new ShortType().getObject(obj1, (Short) obj2);
			case "java.lang.String":
				return new StringType().getObject(obj1, (String) obj2);
			case "java.util.LinkedList":
			case "java.util.ArrayList":
			case "java.util.Vector":
			case "java.util.AbstractList":
			case "java.util.List":
				return new ListType().getObject(obj1, (List<?>) obj2);
			case "java.util.HashSet":
			case "java.util.TreeSet":
			case "java.util.LinkedHashSet":
			case "java.util.AbstractSet":
			case "java.util.Set":
				return new SetType().getObject(obj1, (Set<?>) obj2);
			case "java.util.HashMap":
			case "java.util.TreeMap":
			case "java.util.IdentityHashMap":
			case "java.util.LinkedHashMap":
			case "java.util.WeakHashMap":
			case "java.util.Hashtable":
			case "java.util.AbstractMap":
			case "java.util.Map":
				return new MapType().getObject(obj1, (Map<?, ?>) obj2);
			case "java.util.PriorityQueue":
			case "java.util.AbstractQueue":
			case "java.util.Queue":
				return new QueueType().getObject(obj1, (Queue<?>) obj2);
			case "java.util.Stack":
				return new StackType().getObject(obj1, (Stack<?>) obj2);
			case "java.util.Collection":
				return new CollectionType().getObject(obj1, (Collection<?>) obj2);
			case "java.util.Comparator":
				return new ComparatorType().getObject(obj1, (Comparator<?>) obj2);
			case "java.util.Enumeration":
				return new EnumerationType().getObject(obj1, (Enumeration<?>) obj2);
			case "java.util.Iterator":
				return new IteratorType().getObject(obj1, (Iterator<?>) obj2);
			case "java.util.ListIterator":
				return new ListIteratorType().getObject(obj1, (ListIterator<?>) obj2);
			case "xml.XMLWrapper":
				return new ObjectType().getObject(obj1, (XMLWrapper) obj2);
			default:
				return null;
			}	
		}
		return null;
	}
	
	public static int countOccurrences(String fullString, String fullPattern) {
		int numOccurrences = 0;
		for (int i = 0; i <= fullString.length() - fullPattern.length(); i++) {
			int j;
			for (j = 0; j < fullPattern.length(); j++)
				if (fullString.charAt(i + j) != fullPattern.charAt(j))
					break;
			if (j == fullPattern.length()) {
				j = 0;
				numOccurrences++;
			}
		}
		return numOccurrences;
	}
	
	public static int getIndent(String fullString) {
		int indentSize = 0;
		for(int i = 0; i < fullString.length(); i++) {
			if(!fullString.substring(i, i + 1).equals(" "))
				break;
			indentSize++;
		}
		return indentSize;
	}
	
	public Pair<IORecord, List<IORecord>> getGeneratedRecordsPair() {
		return generatedRecordsPair;
	}

	public void printPermutations(String ioRecordInfo, List<List<Object>> permutations) {
		FileWriter newFileWriter;
		try {
			newFileWriter = new FileWriter(new File("typeConversionLog.txt"), true);
			BufferedWriter newBufferedWriter = new BufferedWriter(newFileWriter);
			PrintWriter newOut = new PrintWriter(newBufferedWriter);
			newOut.println(ioRecordInfo + " Permutations:");
			for(List<Object> permutation : permutations)
				newOut.println("  " + permutation);
			newOut.println();
			newOut.flush();
			newOut.close();
			newBufferedWriter.close();
			newFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Set<Class<?>> getClassesBFS(Class<?> clazz, boolean includeObjectClass) {
	    Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
	    Set<Class<?>> nextLevel = new LinkedHashSet<Class<?>>();
	    nextLevel.add(clazz);
	    do {
    		classes.addAll(nextLevel);
	        Set<Class<?>> thisLevel = new LinkedHashSet<Class<?>>(nextLevel);
	        nextLevel.clear();
	        for (Class<?> each : thisLevel) {
	            Class<?> superClass = each.getSuperclass();
	            if (superClass != null && (includeObjectClass || (!includeObjectClass && (superClass != Object.class)))) {
	                nextLevel.add(superClass);
	            }
	            for (Class<?> eachInterface : each.getInterfaces()) {
	                nextLevel.add(eachInterface);
	            }
	        }
	    } while (!nextLevel.isEmpty());
	    return classes;
	}
	
	public static List<Class<?>> getCommonAncestors(List<Class<?>> classes, boolean includeObjectClass) {
		// Start off with the set from the first hierarchy
	    Set<Class<?>> rollingIntersect = new LinkedHashSet<Class<?>>(getClassesBFS(classes.get(0), includeObjectClass));

	    // Intersect this set with the next one
	    for (int i = 1; i < classes.size(); i++) {
	        rollingIntersect.retainAll(getClassesBFS(classes.get(i), includeObjectClass));
	    }
	    return new LinkedList<Class<?>>(rollingIntersect);
	}
	

	private static Map<Class<?>, Integer> getClassesBFSMap(Class<?> clazz, boolean includeObjectClass) {
	    Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
	    Set<Class<?>> nextLevel = new LinkedHashSet<Class<?>>();
	    nextLevel.add(clazz);
	    Map<Class<?>, Integer> bfsMap = new HashMap<>();
	    int distance = 1;
	    do {
    		classes.addAll(nextLevel);
	        Set<Class<?>> thisLevel = new LinkedHashSet<Class<?>>(nextLevel);
	        nextLevel.clear();
	        for (Class<?> each : thisLevel) {
	            Class<?> superClass = each.getSuperclass();
	            if (superClass != null && (includeObjectClass || (!includeObjectClass && (superClass != Object.class)))) {
	                nextLevel.add(superClass);
	                bfsMap.put(superClass, distance);
	            }
	            for (Class<?> eachInterface : each.getInterfaces()) {
	                nextLevel.add(eachInterface);
	                bfsMap.put(eachInterface, distance);
	            }
	        }
            distance++;
	    } while (!nextLevel.isEmpty());
	    return bfsMap;
	}
	
	public static Map<Class<?>, Integer> getCommonAncestorMap(List<Class<?>> classes, boolean includeObjectClass) {
		// Start off with the set from the first hierarchy
		Map<Class<?>, Integer> bfsMap = getClassesBFSMap(classes.get(0), includeObjectClass);
		Set<Class<?>> setOfClasses = new HashSet<>();
		setOfClasses.addAll(classes);
		if(setOfClasses.size() == 1)
			bfsMap.put(new ArrayList<>(setOfClasses).get(0), 0);
			

	    // Intersect this set with the next one
	    for (int i = 1; i < classes.size(); i++) {
			Map<Class<?>, Integer> recursiveBFSMap = getClassesBFSMap(classes.get(i), includeObjectClass);
			for(Class<?> clazz : recursiveBFSMap.keySet()) {
				if(bfsMap.containsKey(clazz))
					bfsMap.put(clazz, (int) Math.max(bfsMap.get(clazz), recursiveBFSMap.get(clazz)));
				else
					bfsMap.put(clazz, recursiveBFSMap.get(clazz));
			}
	    }
	    return bfsMap;	    
	}
	
	public static Object deepCopy(Object originalObject) throws Exception {
	    ObjectOutputStream objectOutputStream = null;
	    ObjectInputStream objectInputStream = null;
	    
	    try {
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
	        objectOutputStream.writeObject(originalObject);
	        objectOutputStream.flush();
	        
	        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	        objectInputStream = new ObjectInputStream(byteArrayInputStream);

	        return objectInputStream.readObject();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    } finally {
	        objectOutputStream.close();
	        objectInputStream.close();
	    }
	}

	public void reset() {
		ioRecord1 = null ;
		ioRecord2 = null;
		ioRecord1Inputs.clear();
		ioRecord2Inputs.clear();
		generatedRecordsPair = null;
	}
}