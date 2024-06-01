package conversion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.evosuite.shaded.org.hsqldb.types.Type;

import com.github.javaparser.utils.Pair;
import com.thoughtworks.xstream.XStream;

import pojo.IORecord;
import xml.XMLTools;
import xml.XMLWrapper;

public class ObjectType extends Conversion {

	private static final String COLUMBIA_TAG = "__COLUMBIA";
	
	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		try {
			Object obj1 = TypeConversion.deepCopy(xmlWrapper.obj);
			Object oldObj2;
			if(obj.getClass().getCanonicalName().contains("xml.XMLWrapper"))
				oldObj2 = ((XMLWrapper) obj).obj;
			else
				oldObj2 = obj;
			Object obj2 = TypeConversion.deepCopy(oldObj2);
			
			try {
				XStream xstream = XMLTools.getXStream();

		        File file1 = new File("/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/src/ioRepoFiles/originalIORecords/object" + xmlWrapper.deepHash);
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1, false));
				bufferedWriter.write(xstream.toXML(obj1));
				bufferedWriter.close();
				
				List<String> fieldsAsInputsForObj1 = new ArrayList<>(), fieldsAsInputsForObj2 = new ArrayList<>();
				String currLine = "", newFieldString = COLUMBIA_TAG, startingObject1Tag = COLUMBIA_TAG, endingObject1Tag = COLUMBIA_TAG;
				Field[] fieldObjectsForObj1 = obj1.getClass().getDeclaredFields();
				int fieldObjectIndex = 0;
				
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
				while((currLine = bufferedReader.readLine()) != null) {
					int indent = TypeConversion.getIndent(currLine);
					if(indent == 0) {
						if(startingObject1Tag.equals(COLUMBIA_TAG))
							startingObject1Tag = currLine;
						else
							endingObject1Tag = currLine;
						
						if(!newFieldString.equals(COLUMBIA_TAG)) {
							fieldsAsInputsForObj1.add(newFieldString);
							newFieldString = COLUMBIA_TAG;
						}
					}
					else if(indent == 2) {
						String contentFromStartingTag = extractContentFromStartingTag(currLine);
						String currLineWithClassAttribute = currLine;
						String currLineWithoutIndent = currLine.substring(indent);

						if(newFieldString.equals(COLUMBIA_TAG) && !currLineWithoutIndent.startsWith("</")) {
							if(!contentFromStartingTag.contains("class=")) {
								String type = fieldObjectsForObj1[fieldObjectIndex].getType().getCanonicalName();
								String typeWithoutArraySuffix = type.split("\\[\\]")[0];
								int arrayDimension = TypeConversion.countOccurrences(type, "[]");
								if(arrayDimension == 0)
									type = fieldObjectsForObj1[fieldObjectIndex].getType().getName();
								else {
									type = typeWithoutArraySuffix;
									for(int i = 0; i < arrayDimension; i++)
										type += "-array";
								}
								currLineWithClassAttribute = currLineWithClassAttribute.replaceFirst(Pattern.quote(contentFromStartingTag), Matcher.quoteReplacement(contentFromStartingTag + " class=\"" + type + "\""));
							}
							newFieldString = currLineWithClassAttribute;
							fieldObjectIndex++;
						}
						else {
							if(currLineWithoutIndent.startsWith("</")) {
								newFieldString += "\n" + currLine;
								fieldsAsInputsForObj1.add(newFieldString);
								newFieldString = COLUMBIA_TAG;
							}
							else {
								fieldsAsInputsForObj1.add(newFieldString);
								if(!contentFromStartingTag.contains("class=")) {
									String type = fieldObjectsForObj1[fieldObjectIndex].getType().getCanonicalName();
									String typeWithoutArraySuffix = type.split("\\[\\]")[0];
									int arrayDimension = TypeConversion.countOccurrences(type, "[]");
									if(arrayDimension == 0)
										type = fieldObjectsForObj1[fieldObjectIndex].getType().getName();
									else {
										type = typeWithoutArraySuffix;
										for(int i = 0; i < arrayDimension; i++)
											type += "-array";
									}
									currLineWithClassAttribute = currLineWithClassAttribute.replaceFirst(Pattern.quote(contentFromStartingTag), Matcher.quoteReplacement(contentFromStartingTag + " class=\"" + type + "\""));
								}
								newFieldString = currLineWithClassAttribute;
  								fieldObjectIndex++;
							}
						}
					}
					else
						newFieldString += "\n" + currLine;
				}
    			Runtime.getRuntime().exec("rm /Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/src/ioRepoFiles/originalIORecords/object" + xmlWrapper.deepHash);

    			IORecord ioRecordObj1 = new IORecord();
				ioRecordObj1.methodKey = "";
				for(String fieldAsInput : fieldsAsInputsForObj1)
					ioRecordObj1.sortedInputs.add(xstream.fromXML(fieldAsInput));
				ioRecordObj1.id = new Random().nextInt(Integer.MAX_VALUE);
				
		        File file2 = new File("/Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/src/ioRepoFiles/originalIORecords/object" + ((XMLWrapper) obj).deepHash);
				BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2, false));
				bufferedWriter2.write(xstream.toXML(obj2));
				bufferedWriter2.close();
				
				currLine = "";
				newFieldString = COLUMBIA_TAG;
				String startingObject2Tag = COLUMBIA_TAG, endingObject2Tag = COLUMBIA_TAG;
				Field[] fieldObjectsForObj2 = obj2.getClass().getDeclaredFields();
				fieldObjectIndex = 0;
				
				BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2));
				while((currLine = bufferedReader2.readLine()) != null) {
					int indent = TypeConversion.getIndent(currLine);
					if(indent == 0) {
						if(startingObject2Tag.equals(COLUMBIA_TAG))
							startingObject2Tag = currLine;
						else
							endingObject2Tag = currLine;
						
						if(!newFieldString.equals(COLUMBIA_TAG)) {
							fieldsAsInputsForObj2.add(newFieldString);
							newFieldString = COLUMBIA_TAG;
						}
					}
					else if(indent == 2) {
						String contentFromStartingTag = extractContentFromStartingTag(currLine);
						String currLineWithClassAttribute = currLine;
						String currLineWithoutIndent = currLine.substring(indent);
						
						if(newFieldString.equals(COLUMBIA_TAG) && !currLineWithoutIndent.startsWith("</")) {
							if(!contentFromStartingTag.contains("class=")) {
								String type = fieldObjectsForObj2[fieldObjectIndex].getType().getCanonicalName();
								String typeWithoutArraySuffix = type.split("\\[\\]")[0];
								int arrayDimension = TypeConversion.countOccurrences(type, "[]");
								if(arrayDimension == 0)
									type = fieldObjectsForObj2[fieldObjectIndex].getType().getName();
								else {
									type = typeWithoutArraySuffix;
									for(int i = 0; i < arrayDimension; i++)
										type += "-array";
								}
								currLineWithClassAttribute = currLineWithClassAttribute.replaceFirst(Pattern.quote(contentFromStartingTag), Matcher.quoteReplacement(contentFromStartingTag + " class=\"" + type + "\""));
							}
							newFieldString = currLineWithClassAttribute;
							fieldObjectIndex++;
						}
						else {
							if(currLineWithoutIndent.startsWith("</")) {
								newFieldString += "\n" + currLine;
								fieldsAsInputsForObj2.add(newFieldString);
								newFieldString = COLUMBIA_TAG;
							}
							else {
								fieldsAsInputsForObj2.add(newFieldString);
								if(!contentFromStartingTag.contains("class=")) {
									String type = fieldObjectsForObj2[fieldObjectIndex].getType().getCanonicalName();
									String typeWithoutArraySuffix = type.split("\\[\\]")[0];
									int arrayDimension = TypeConversion.countOccurrences(type, "[]");
									if(arrayDimension == 0)
										type = fieldObjectsForObj2[fieldObjectIndex].getType().getName();
									else {
										type = typeWithoutArraySuffix;
										for(int i = 0; i < arrayDimension; i++)
											type += "-array";
									}
									currLineWithClassAttribute = currLineWithClassAttribute.replaceFirst(Pattern.quote(contentFromStartingTag), Matcher.quoteReplacement(contentFromStartingTag + " class=\"" + type + "\""));
								}
								newFieldString = currLineWithClassAttribute;
								fieldObjectIndex++;
							}
						}
					}
					else
						newFieldString += "\n" + currLine;
				}
    			Runtime.getRuntime().exec("rm /Users/adityasridhar/Documents/COLUMBIA/JavaParserPractice/src/ioRepoFiles/originalIORecords/object" + ((XMLWrapper) obj).deepHash);

				IORecord ioRecordObj2 = new IORecord();
				ioRecordObj2.methodKey = "";
				for(String fieldAsInput : fieldsAsInputsForObj2)
					ioRecordObj2.sortedInputs.add(xstream.fromXML(fieldAsInput));
				ioRecordObj2.id = new Random().nextInt(Integer.MAX_VALUE);

				TypeConversion typeConversionObj = new TypeConversion(ioRecordObj1, ioRecordObj2);
				Pair<IORecord, List<IORecord>> generatedRecordsPair = typeConversionObj.getGeneratedRecordsPair();
				List<IORecord> generatedRecordsForObj1 = generatedRecordsPair.b;
				
				Group group = new Group();
				for(IORecord generatedRecord : generatedRecordsForObj1) {
					String objectString = "";
					if(!startingObject1Tag.equals(COLUMBIA_TAG))
						objectString = startingObject1Tag;
					int inputIndex = 0;
					String typeString = "";
					for(Object input : generatedRecord.sortedInputs) {
						String xmlInput = xstream.toXML(input);
						String contentFromStartingTag = extractContentFromStartingTag(xmlInput);
						if(!contentFromStartingTag.contains("class=")) {
							String fieldName = fieldObjectsForObj1[inputIndex].getName();
							xmlInput = xmlInput.replaceFirst(Pattern.quote(contentFromStartingTag), Matcher.quoteReplacement(fieldName));
							xmlInput = xmlInput.replaceAll(Pattern.quote("</" + contentFromStartingTag + ">"), Matcher.quoteReplacement("</" + fieldName + ">"));
						}
						objectString += "\n  " + xmlInput;
						inputIndex++;
					}
					if(!endingObject1Tag.equals(COLUMBIA_TAG))
						objectString += "\n" + endingObject1Tag;
					group.addConversion(new XMLWrapper(xstream.fromXML(objectString)));
				}
				return group;			
			} catch (IOException e) {
				return null;
			}
		} catch(Exception e) {
			return null;
		}
	}
	
	private String extractContentFromStartingTag(String xmlFieldString) {
		return xmlFieldString.substring(xmlFieldString.indexOf("<") + 1, xmlFieldString.indexOf(">"));
	}

	private String extractContentFromEndingTag(String xmlFieldString) {
		return xmlFieldString.substring(xmlFieldString.lastIndexOf("</") + 1, xmlFieldString.lastIndexOf(">"));
	}
	
	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		return null;
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		return null;
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		return null;
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		return null;
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		return null;
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		return null;
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		return null;
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		return null;
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		return null;
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		return null;
	}

	@Override
	public Object convertString(String str, Object obj) {
		return null;
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return null;
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		return null;
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		return null;
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		return null;
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return null;
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return null;
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return null;
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		return null;
	}
}