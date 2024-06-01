package conversion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import xml.XMLWrapper;

public class MapType extends Conversion {

	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		try {
			try {
				try {
					try {
						try {
							try {
								try {
									try {
										try {

											Map<?, ?> objectCastAsMap = (Map<?, ?>) xmlWrapper.obj;
											return convertMap(objectCastAsMap, obj);
										} catch(Exception e) {}

										List<?> objectCastAsList = (List<?>) xmlWrapper.obj;
										return convertList(objectCastAsList, obj);
									} catch(Exception e) {}
									
									Stack<?> objectCastAsStack = (Stack<?>) xmlWrapper.obj;
									return convertStack(objectCastAsStack, obj);			
								} catch(Exception e) {}
								
								Set<?> objectCastAsSet = (Set<?>) xmlWrapper.obj;
								return convertSet(objectCastAsSet, obj);
							} catch(Exception e) {}
							
							Queue<?> objectCastAsQueue = (Queue<?>) xmlWrapper.obj;
							return convertQueue(objectCastAsQueue, obj);
						} catch(Exception e) {}

						Collection<?> objectCastAsCollection = (Collection<?>) xmlWrapper.obj;
						return convertCollection(objectCastAsCollection, obj);
					} catch(Exception e) {}

					Iterator<?> objectCastAsIterator = (Iterator<?>) xmlWrapper.obj;
					return convertIterator(objectCastAsIterator, obj);					
				} catch(Exception e) {}

				ListIterator<?> objectCastAsListIterator = (ListIterator<?>) xmlWrapper.obj;
				return convertListIterator(objectCastAsListIterator, obj);			
			} catch(Exception e) {}
			
			Enumeration<?> objectCastAsEnumeration = (Enumeration<?>) xmlWrapper.obj;
			return convertEnumeration(objectCastAsEnumeration, obj);			
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {		
		try {
			Object keySetConversionObj = null, valueCollectionConversionObj = null;
			Map<Object, Object> map2 = (Map<Object, Object>) TypeConversion.deepCopy(obj);			
			try {
				keySetConversionObj = TypeConversion.convert(map.keySet(), map2.keySet());
			} catch(Exception e) {}
			try {
				valueCollectionConversionObj = TypeConversion.convert(map.values(), map2.values());
			} catch(Exception e) {}
			
			if(keySetConversionObj == null || valueCollectionConversionObj == null)
				return null;
			if(keySetConversionObj.getClass().getCanonicalName().equals("conversion.Group") ||
					valueCollectionConversionObj.getClass().getCanonicalName().equals("conversion.Group")) {
				Group group = new Group();
				if(keySetConversionObj.getClass().getCanonicalName().equals("conversion.Group") &&
						valueCollectionConversionObj.getClass().getCanonicalName().equals("conversion.Group")) {
					Group keysGroup = (Group) keySetConversionObj;
					Group valuesGroup = (Group) valueCollectionConversionObj;
					for(Object keyConversionObj : keysGroup.getConversions()) {
						for(Object valueConversionObj : valuesGroup.getConversions()) {
							List<Object> convertedKeyList = new ArrayList<>((Set<Object>) TypeConversion.deepCopy(keyConversionObj));
							List<Object> convertedValueList = new ArrayList<>((Collection<Object>) TypeConversion.deepCopy(valueConversionObj));
							Map<Object, Object> map3 = (Map<Object, Object>) TypeConversion.deepCopy(map2);
							map3.clear();

							for(int i = 0; i < convertedKeyList.size(); i++)
								map3.put(convertedKeyList.get(i), convertedValueList.get(i));
							
							group.addConversion(map3);
						}					
					}
					return group;
				}
				if(keySetConversionObj.getClass().getCanonicalName().equals("conversion.Group")) {
					Group keysGroup = (Group) keySetConversionObj;
					for(Object keyConversionObj : keysGroup.getConversions()) {
						List<Object> convertedKeyList = new ArrayList<>((Set<Object>) TypeConversion.deepCopy(keyConversionObj));
						List<Object> convertedValueList = new ArrayList<>((Collection<Object>) TypeConversion.deepCopy(valueCollectionConversionObj));
						Map<Object, Object> map3 = (Map<Object, Object>) TypeConversion.deepCopy(map2);
						map3.clear();

						for(int i = 0; i < convertedKeyList.size(); i++)
							map3.put(convertedKeyList.get(i), convertedValueList.get(i));
						
						group.addConversion(map3);
					}					
					return group;					
				}
				if(valueCollectionConversionObj.getClass().getCanonicalName().equals("conversion.Group")) {
					Group valuesGroup = (Group) valueCollectionConversionObj;
					for(Object valueConversionObj : valuesGroup.getConversions()) {
						List<Object> convertedKeyList = new ArrayList<>((Set<Object>) TypeConversion.deepCopy(keySetConversionObj));
						List<Object> convertedValueList = new ArrayList<>((Collection<Object>) TypeConversion.deepCopy(valueConversionObj));
						Map<Object, Object> map3 = (Map<Object, Object>) TypeConversion.deepCopy(map2);
						map3.clear();

						for(int i = 0; i < convertedKeyList.size(); i++)
							map3.put(convertedKeyList.get(i), convertedValueList.get(i));
						
						group.addConversion(map3);
					}					
					return group;					
				}
			}

			List<Object> convertedKeyList = new ArrayList<>((Set<Object>) TypeConversion.deepCopy(keySetConversionObj));
			List<Object> convertedValueList = new ArrayList<>((Collection<Object>) TypeConversion.deepCopy(valueCollectionConversionObj));
			Map<Object, Object> map3 = (Map<Object, Object>) TypeConversion.deepCopy(map2);
			map3.clear();

			for(int i = 0; i < convertedKeyList.size(); i++)
				map3.put(convertedKeyList.get(i), convertedValueList.get(i));

			return map3;
		} catch(Exception e) {
			return null;
		}
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