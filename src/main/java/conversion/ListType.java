package conversion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import xml.XMLWrapper;

public class ListType extends Conversion {

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
											try {
	
												List<?> objectCastAsList = (List<?>) xmlWrapper.obj;
												return convertList(objectCastAsList, obj);
											} catch(Exception e) {}
	
											Stack<?> objectCastAsStack = (Stack<?>) xmlWrapper.obj;
											return convertStack(objectCastAsStack, obj);			
										} catch(Exception e) {}
										
										Map<?, ?> objectCastAsMap = (Map<?, ?>) xmlWrapper.obj;
										return convertMap(objectCastAsMap, obj);
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
			} catch(Exception e) {}

			return makeSingleElementListConversion(xmlWrapper.obj, obj);			
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		try {
			Object keysInputs = null, valuesInputs = null;
			try {
				List<Object> list = new ArrayList<>();
				for(Object element : map.keySet())
					list.add(element);
				keysInputs = makeMultipleElementListConversion(list, obj);
			} catch(Exception e) {}
			try {
				List<Object> list = new ArrayList<>();
				for(Object element : map.values())
					list.add(element);
				valuesInputs = makeMultipleElementListConversion(list, obj);
			} catch(Exception e) {}
			
			if(keysInputs == null && valuesInputs == null)
				return null;
			if((keysInputs != null && keysInputs.getClass().getCanonicalName().equals("conversion.Group")) ||
					(valuesInputs != null && valuesInputs.getClass().getCanonicalName().equals("conversion.Group")) ||
					(keysInputs != null && valuesInputs != null)) {
				Group group = new Group();
				if(keysInputs != null) {
					if(keysInputs.getClass().getCanonicalName().equals("conversion.Group")) {
						Group keysGroup = (Group) keysInputs;
						for(Object conversion : keysGroup.getConversions())
							group.addConversion(conversion);
					}
					else
						group.addConversion(keysInputs);
				}
				if(valuesInputs != null) {
					if(valuesInputs.getClass().getCanonicalName().equals("conversion.Group")) {
						Group valuesGroup = (Group) valuesInputs;
						for(Object conversion : valuesGroup.getConversions())
							group.addConversion(conversion);
					}
					else
						group.addConversion(valuesInputs);
				}
				return group;
			}
			if(keysInputs != null)
				return keysInputs;
			if(valuesInputs != null)
				return valuesInputs;
			return null;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		try {
			List<Object> list = new ArrayList<>();
			for(Object element : collection)
				list.add(element);
			return makeMultipleElementListConversion(list, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		try {
			return makeMultipleElementListConversion(list, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		try {
			List<Object> list = new ArrayList<>();
			for(Object element : set)
				list.add(element);
			return makeMultipleElementListConversion(list, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		try {
			List<Object> list = new ArrayList<>();
			for(Object element : stack)
				list.add(element);
			return makeMultipleElementListConversion(list, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		try {
			List<Object> list = new ArrayList<>();
			for(Object element : queue)
				list.add(element);
			return makeMultipleElementListConversion(list, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		return null;
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		try {
			List<Object> list = (List<Object>) TypeConversion.deepCopy(Collections.list(enumeration));
			return convertList(list, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		try {
			List<Object> list = new ArrayList<>();
			while(iterator.hasNext())
				list.add(iterator.next());
			return makeMultipleElementListConversion(list, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		try {
			List<Object> list = new ArrayList<>();
			while(listIterator.hasNext())
				list.add(listIterator.next());
			return makeMultipleElementListConversion(list, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertString(String str, Object obj) {
		return makeSingleElementListConversion(str, obj);
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return makeSingleElementListConversion(i, obj);
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		return makeSingleElementListConversion(d, obj);
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		return makeSingleElementListConversion(f, obj);
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		return makeSingleElementListConversion(l, obj);
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return makeSingleElementListConversion(b, obj);
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return makeSingleElementListConversion(s, obj);
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return makeSingleElementListConversion(c, obj);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		return makeSingleElementListConversion(b, obj);
	}
	
	private Object makeSingleElementListConversion(Object obj1, Object obj2) {
		try {
			List<Class<?>> listOfClasses = new ArrayList<>();

			List<Object> newList = (List<Object>) TypeConversion.deepCopy(obj2);
			if(newList.size() == 0)
				return null;
			
			for(Object listObj : newList) {
				if(listObj.getClass().getCanonicalName().equals("xml.XMLParser"))
					listOfClasses.add(((XMLWrapper) (listObj)).obj.getClass());
				else
					listOfClasses.add(listObj.getClass());
			}

			List<Class<?>> commonAncestors = TypeConversion.getCommonAncestors(listOfClasses, false);
			if(commonAncestors.size() != 0) {
				if(newList.size() == 1) {
					Object newConversion = TypeConversion.convert(obj1, newList.get(0));
					if(!newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
						List<Object> newerList = (List<Object>) TypeConversion.deepCopy(newList);
						newerList.clear();
						newerList.add(newConversion);
						return newerList;
					}					
				}
				Group group = new Group();
				for(Object object : newList) {
					Object newConversion = TypeConversion.convert(obj1, object);
					if(newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
						Group nestedGroup = (Group) newConversion;
						for(Object nestedConversion : nestedGroup.getConversions()) {
							List<Object> newerList = (List<Object>) TypeConversion.deepCopy(newList);
							newerList.clear();
							newerList.add(nestedConversion);
							group.addConversion(newerList);
						}
					}
					else {
						List<Object> newerList = (List<Object>) TypeConversion.deepCopy(newList);
						newerList.clear();
						newerList.add(newConversion);
						group.addConversion(newerList);
					}
				}
				return group;
			}
			List<Object> newerList = (List<Object>) TypeConversion.deepCopy(newList);
			newerList.clear();
			newerList.add(obj1);
			return newerList;
		} catch(Exception e) {
			return null;
		}
	}
	
	private Object makeMultipleElementListConversion(List<?> list, Object obj2) {
		try {
			List<Class<?>> listOfClasses = new ArrayList<>();
			List<Object> newList = (List<Object>) TypeConversion.deepCopy(obj2);
			if(newList.size() == 0)
				return null;
			
			for(Object listObj : newList) {
				if(listObj.getClass().getCanonicalName().equals("xml.XMLParser"))
					listOfClasses.add(((XMLWrapper) (listObj)).obj.getClass());
				else
					listOfClasses.add(listObj.getClass());
			}

			List<Class<?>> commonAncestors = TypeConversion.getCommonAncestors(listOfClasses, false);
			if(commonAncestors.size() != 0) {
				if(newList.size() == 1) {
					boolean groupConversionExists = false;
					List<Object> newerList = (List<Object>) TypeConversion.deepCopy(newList);
					newerList.clear();
					for(Object listElement : list) {
						Object newConversion = TypeConversion.convert(listElement, newList.get(0));
						if(newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
							groupConversionExists = true;
							break;
						}
						newerList.add(newConversion);
					}
					
					if(!groupConversionExists)
						return newerList;
				}
				Group group = new Group();
				for(int i = 0; i < newList.size(); i++) {
					List<Object> newerList = (List<Object>) TypeConversion.deepCopy(newList);
					newerList.clear();
					for(Object listElement : list) {
						Object newConversion = TypeConversion.convert(listElement, newList.get(i));
						newerList.add(newConversion);
					}

					List<List<Object>> inputCombinations = new ArrayList<>();
					TypeConversion.analyzeGroups(inputCombinations, newerList, new ArrayList<>(), 0);
					for(List<Object> inputCombination : inputCombinations)
						group.addConversion(inputCombination);
				}
				return group;
			}
			List<Object> newerList = (List<Object>) TypeConversion.deepCopy(newList);
			return newerList;
		} catch(Exception e) {
			return null;
		}
	}
}