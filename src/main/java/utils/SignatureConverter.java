package utils;

import java.util.*;

public class SignatureConverter {
	private static Map<String, String> conversionMap;

	static {
		Map<String, String> tempConversionMap = new HashMap<>();
	    tempConversionMap.put("Z", "boolean");
	    tempConversionMap.put("B", "byte");
	    tempConversionMap.put("C", "char");
	    tempConversionMap.put("S", "short");
	    tempConversionMap.put("I", "int");
	    tempConversionMap.put("J", "long");
	    tempConversionMap.put("F", "float");
	    tempConversionMap.put("D", "double");
	    tempConversionMap.put("V", "");
	    conversionMap = Collections.unmodifiableMap(tempConversionMap);
	}
	
	public static String convertSignatureToJSSFormat(String hitoshiIOSignature) {
	    String newSignature = hitoshiIOSignature.substring(0, hitoshiIOSignature.indexOf("(") + 1).replace("-", ".");
	    List<String> argsList = new ArrayList<>(), returnValueList = new ArrayList<>();
	    String descriptor = hitoshiIOSignature.substring(hitoshiIOSignature.indexOf("(") + 1);
	    if(descriptor.charAt(0) == ')')
	        newSignature += ")";
	    else {
	        int numArgsArrayDimensions = 0;
	        while(descriptor.charAt(0) != ')') {
	            char elementType = descriptor.charAt(0);
	            if(conversionMap.containsKey(elementType + "")) {
	                argsList.add(conversionMap.get(elementType + ""));
	                descriptor = descriptor.substring(1);
	            }
	            else if(elementType == '[') {
	                numArgsArrayDimensions++;
	                descriptor = descriptor.substring(1);
	                continue;
	            }
	            else if(elementType == 'L') {
	                argsList.add(descriptor.substring(descriptor.indexOf(elementType) + 1, descriptor.indexOf(";")).replace("/", "."));
	                descriptor = descriptor.substring(descriptor.indexOf(";") + 1);
	            }
	            else {
	                descriptor = descriptor.substring(1);
	            }
	            if(numArgsArrayDimensions > 0) {
	                String arrayBracesAttachment = "";
	                for(int i = 0; i < numArgsArrayDimensions; i++)
	                    arrayBracesAttachment += "[]";
	                String lastArgsElement = argsList.get(argsList.size() - 1);
	                argsList.set(argsList.size() - 1, lastArgsElement + arrayBracesAttachment);
	                numArgsArrayDimensions = 0;
	            }
	        }
	        newSignature += String.join(", ", argsList) + ")";
	    }
//	    descriptor = descriptor.substring(1);
//	    int numRVArrayDimensions = 0;
//	    while(descriptor.length() > 0) {
//	        char elementType = descriptor.charAt(0);
//	        if(conversionMap.containsKey(elementType + "")) {
//	            returnValueList.add(conversionMap.get(elementType + ""));
//	            descriptor = descriptor.substring(1);
//	        }
//	        else if(elementType == '[') {
//	            numRVArrayDimensions++;
//	            descriptor = descriptor.substring(1);
//	            continue;
//	        }
//	        else if(elementType == 'L') {
//	            returnValueList.add(descriptor.substring(descriptor.indexOf(elementType) + 1, descriptor.indexOf(";")).replace("/", "."));
//	            descriptor = descriptor.substring(descriptor.indexOf(";") + 1);
//	        }
//	        else {
//	            descriptor = descriptor.substring(1);
//	        }
//	        if(numRVArrayDimensions > 0) {
//	            String arrayBracesAttachment = "";
//	            for(int i = 0; i < numRVArrayDimensions; i++)
//	                arrayBracesAttachment += "[]";
//	            String lastRVElement = returnValueList.get(returnValueList.size() - 1);
//	            returnValueList.set(returnValueList.size() - 1, lastRVElement + arrayBracesAttachment);
//	            numRVArrayDimensions = 0;
//	        }
//	    }
//	    newSignature += String.join(", ", returnValueList);
	    return newSignature;
	}
}