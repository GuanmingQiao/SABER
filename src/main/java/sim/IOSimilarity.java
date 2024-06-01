package sim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import conversion.Group;
import conversion.TypeConversion;
import pojo.IORecord;
import xml.DeepHash;
import xml.XMLWrapper;

public class IOSimilarity {

	private static final double expModelBeta = 3.0;
		
	public static double getSimilarity(IORecord ioRecord1, IORecord ioRecord2) {
		double inputSimilarity = getInputSimilarity(ioRecord1, ioRecord2);
		double outputSimilarity = getOutputSimilarity(ioRecord1, ioRecord2);
		return computeExpModelSimilarity(inputSimilarity, outputSimilarity);
	}
	
	public static double getInputSimilarity(IORecord ioRecord1, IORecord ioRecord2) {
		return getSimilarity(ioRecord1.sortedInputs, ioRecord2.sortedInputs);
	}

	public static double getOutputSimilarity(IORecord ioRecord1, IORecord ioRecord2) {
		return getSimilarity(ioRecord1.sortedOutputs, ioRecord2.sortedOutputs);
	}
	
	public static double getSimilarity(List<Object> list1, List<Object> list2) {
        if(list1.size() == 0 && list2.size() == 0)
        	return 0;

        List<Integer> deepHashList1 = generateDeepHash(list1);
        List<Integer> deepHashList2 = generateDeepHash(list2);
        List<Object> list1WithoutXMLWrappers = newListWithExtractedObjects(list1);
        List<Object> list2WithoutXMLWrappers = newListWithExtractedObjects(list2);
        
        System.out.println("Deep hash list 1: " + deepHashList1);
        System.out.println("Deep hash list 2: " + deepHashList2);
        System.out.println("List 1 without XML wrappers: " + list1WithoutXMLWrappers);
        System.out.println("List 2 without XML wrappers: " + list2WithoutXMLWrappers);

        double intersection = 0;
        for(int i = 0; i < deepHashList1.size(); i++) {
    		Object obj1 = list1.get(i);
        	int hash = deepHashList1.get(i);
        	if(deepHashList2.contains(hash) || list2.contains(obj1))
        		intersection++;
        	else {
        		double maxAncestralWalkFactor = 0.0;
        		boolean ancestralWalkFactorNecessary = true;
        		for(int j = 0; j < list2.size(); j++) {
        			Object obj2 = list2.get(j);
        			
        			// Type match
        			if(obj1.getClass().getName().equals(obj2.getClass().getName()) && !(obj1 instanceof XMLWrapper)) {
        				intersection += 0.5;
                		ancestralWalkFactorNecessary = false;
        				break;
        			}    				

    				// Value match
        			Object newObj1 = TypeConversion.convert(obj1, obj2);
        			boolean groupConversionMatchFound = false;
        			if(newObj1 != null) {
	        			if(newObj1.getClass().getCanonicalName().equals("conversion.Group")) {
	        				Group group = (Group) newObj1;
	        				for(int k = 0; k < group.size(); k++) {
	        					Object newerObj1 = new ArrayList<>(group.getConversions()).get(k);
	                			int newHash = generateDeepHash(newerObj1);
	                			if(deepHashList2.contains(newHash) || list2.contains(newerObj1)) {
	                				intersection += 0.5;
	                				groupConversionMatchFound = true;
	                        		ancestralWalkFactorNecessary = false;
	                				break;
	                			}
	        				}
	        			}
	        			else {
	            			int newHash = generateDeepHash(newObj1);
	            			if(deepHashList2.contains(newHash) || list2.contains(newObj1)) {
	            				intersection += 0.5;
	                    		ancestralWalkFactorNecessary = false;
	            				break;
	            			}
	        			}
        			}
        			
        			// Ancestral walk
        			Object rawObj1, rawObj2;
    				if(obj1 instanceof XMLWrapper)
        				rawObj1 = list1WithoutXMLWrappers.get(i);
       				else
       					rawObj1 = obj1;
       				if(obj2 instanceof XMLWrapper)
           				rawObj2 = list2WithoutXMLWrappers.get(i);
       				else
       					rawObj2 = obj2;
    				double ancestralWalkFactor = ancestralWalk(rawObj1, rawObj2);
    				if(ancestralWalkFactor > maxAncestralWalkFactor)
    					maxAncestralWalkFactor = ancestralWalkFactor;

        			if(groupConversionMatchFound)
        				break;
        		}
        		if(ancestralWalkFactorNecessary) {
        			System.out.println("Max ancestral walk factor: " + maxAncestralWalkFactor);
        			intersection += 0.5 * maxAncestralWalkFactor;
        		}
        	}
        }
        double union = list1.size() + list2.size() - intersection;
        
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        System.out.println("Intersection: " + intersection);
        System.out.println("Union: " + union);
        System.out.println("Similarity: " + intersection / union);
        return intersection / union;
	}
	
	public static double computeExpModelSimilarity(double inputSimilarity, double outputSimilarity) {
		double numerator = (1 - expModelBeta * Math.pow(Math.E, inputSimilarity)) *
				(1 - expModelBeta * Math.pow(Math.E, outputSimilarity));
		double denominator = Math.pow(1 - expModelBeta * Math.E, 2);
		
		return numerator / denominator;
	}
	
	public static Integer generateDeepHash(Object obj) {
    	return (obj instanceof XMLWrapper) ? ((XMLWrapper) obj).deepHash : DeepHash.deepHash(obj);
	}
	
	public static List<Integer> generateDeepHash(List<Object> originalList) {
		List<Integer> convertedList = new ArrayList<>();
        for (Object obj : originalList) {
        	int deepHash = (obj instanceof XMLWrapper) ? ((XMLWrapper) obj).deepHash : DeepHash.deepHash(obj);
        	convertedList.add(deepHash);
        }

        return convertedList;
	}
	
	public static List<Object> newListWithExtractedObjects(List<Object> list) {
		List<Object> convertedList = new ArrayList<>();
		for(Object obj : list) {
			if(!(obj instanceof XMLWrapper))
				convertedList.add(obj);
			else
				convertedList.add(((XMLWrapper) obj).obj);
		}
		return convertedList;
	}
	
	public static double ancestralWalk(Object obj1, Object obj2) {
		List<Class<?>> classes = new ArrayList<>();
		classes.add(obj1.getClass());
		classes.add(obj2.getClass());
		Map<Class<?>, Integer> commonAncestorMap = TypeConversion.getCommonAncestorMap(classes, true);
		int distanceToObjectClass = 0;
		int smallestAncestralDistance = Integer.MAX_VALUE;
		for(Class<?> clazz : commonAncestorMap.keySet()) {
			if(clazz.isInterface())
				continue;
			if(clazz.getName().equals("java.lang.Object"))
				distanceToObjectClass = commonAncestorMap.get(clazz);
			else if(commonAncestorMap.get(clazz) < smallestAncestralDistance) {
				smallestAncestralDistance = commonAncestorMap.get(clazz);
			}
		}
		if(distanceToObjectClass == 0 || smallestAncestralDistance == 0)
			return 1.0;
		if(smallestAncestralDistance == Integer.MAX_VALUE)
			return 0.0;
		return (distanceToObjectClass - smallestAncestralDistance) * 1.0 / distanceToObjectClass;
	}
}