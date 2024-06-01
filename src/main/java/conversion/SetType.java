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

public class SetType extends Conversion {

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
	
												Set<?> objectCastAsSet = (Set<?>) xmlWrapper.obj;
												return convertSet(objectCastAsSet, obj);
											} catch(Exception e) {}
	
											Stack<?> objectCastAsStack = (Stack<?>) xmlWrapper.obj;
											return convertStack(objectCastAsStack, obj);			
										} catch(Exception e) {}
										
										Map<?, ?> objectCastAsMap = (Map<?, ?>) xmlWrapper.obj;
										return convertMap(objectCastAsMap, obj);
									} catch(Exception e) {}
									
									List<?> objectCastAsList = (List<?>) xmlWrapper.obj;
									return convertList(objectCastAsList, obj);
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

			return makeSingleElementSetConversion(xmlWrapper.obj, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		try {
			Object keysInputs = null, valuesInputs = null;
			try {
				keysInputs = makeMultipleElementSetConversion(map.keySet(), obj);
			} catch(Exception e) {}
			try {
				Set<Object> set = new HashSet<>();
				for(Object element : map.values())
					set.add(element);
				valuesInputs = makeMultipleElementSetConversion(set, obj);
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
			Set<Object> set = new HashSet<>();
			for(Object element : collection)
				set.add(element);
			return makeMultipleElementSetConversion(set, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		try {
			Set<Object> set = new HashSet<>();
			for(Object element : list)
				set.add(element);
			return makeMultipleElementSetConversion(set, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		try {
			return makeMultipleElementSetConversion(set, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		try {
			Set<Object> set = new HashSet<>();
			for(Object element : stack)
				set.add(element);
			return makeMultipleElementSetConversion(set, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		try {
			Set<Object> set = new HashSet<>();
			for(Object element : queue)
				set.add(element);
			return makeMultipleElementSetConversion(set, obj);
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
			Set<Object> set = new HashSet<>();
			while(iterator.hasNext())
				set.add(iterator.next());
			return makeMultipleElementSetConversion(set, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		try {
			Set<Object> set = new HashSet<>();
			while(listIterator.hasNext())
				set.add(listIterator.next());
			return makeMultipleElementSetConversion(set, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertString(String str, Object obj) {
		return makeSingleElementSetConversion(str, obj);
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return makeSingleElementSetConversion(i, obj);
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		return makeSingleElementSetConversion(d, obj);
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		return makeSingleElementSetConversion(f, obj);
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		return makeSingleElementSetConversion(l, obj);
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return makeSingleElementSetConversion(b, obj);
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return makeSingleElementSetConversion(s, obj);
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return makeSingleElementSetConversion(c, obj);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		return makeSingleElementSetConversion(b, obj);
	}
	
	private Object makeSingleElementSetConversion(Object obj1, Object obj2) {
		try {
			List<Class<?>> listOfClasses = new ArrayList<>();

			Set<Object> newSet = (Set<Object>) TypeConversion.deepCopy(obj2);
			if(newSet.size() == 0)
				return null;
			
			for(Object setObj : newSet) {
				if(setObj.getClass().getCanonicalName().equals("xml.XMLParser"))
					listOfClasses.add(((XMLWrapper) (setObj)).obj.getClass());
				else
					listOfClasses.add(setObj.getClass());
			}

			List<Class<?>> commonAncestors = TypeConversion.getCommonAncestors(listOfClasses, false);
			if(commonAncestors.size() != 0) {
				if(newSet.size() == 1) {
					Object newConversion = TypeConversion.convert(obj1, new ArrayList<>(newSet).get(0));
					if(!newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
						Set<Object> newerSet = (Set<Object>) TypeConversion.deepCopy(newSet);
						newerSet.clear();
						newerSet.add(newConversion);
						return newerSet;
					}					
				}
				Group group = new Group();
				for(Object object : newSet) {
					Object newConversion = TypeConversion.convert(obj1, object);
					if(newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
						Group nestedGroup = (Group) newConversion;
						for(Object nestedConversion : nestedGroup.getConversions()) {
							Set<Object> newerSet = (Set<Object>) TypeConversion.deepCopy(newSet);
							newerSet.clear();
							newerSet.add(nestedConversion);
							group.addConversion(newerSet);
						}
					}
					else {
						Set<Object> newerSet = (Set<Object>) TypeConversion.deepCopy(newSet);
						newerSet.clear();
						newerSet.add(newConversion);
						group.addConversion(newerSet);
					}
				}
				return group;
			}
			Set<Object> newerSet = (Set<Object>) TypeConversion.deepCopy(newSet);
			newerSet.clear();
			newerSet.add(obj1);
			return newerSet;
		} catch(Exception e) {
			return null;
		}
	}
	
	private Object makeMultipleElementSetConversion(Set<?> set, Object obj2) {
		try {
			List<Class<?>> listOfClasses = new ArrayList<>();

			Set<Object> newSet = (Set<Object>) TypeConversion.deepCopy(obj2);
			if(newSet.size() == 0)
				return null;
			
			for(Object setObj : newSet) {
				if(setObj.getClass().getCanonicalName().equals("xml.XMLParser"))
					listOfClasses.add(((XMLWrapper) (setObj)).obj.getClass());
				else
					listOfClasses.add(setObj.getClass());
			}

			List<Class<?>> commonAncestors = TypeConversion.getCommonAncestors(listOfClasses, false);
			if(commonAncestors.size() != 0) {
				if(newSet.size() == 1) {
					boolean groupConversionExists = false;
					Set<Object> newerSet = (Set<Object>) TypeConversion.deepCopy(newSet);
					newerSet.clear();
					for(Object setElement : set) {
						Object newConversion = TypeConversion.convert(setElement, new ArrayList<>(newSet).get(0));
						if(newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
							groupConversionExists = true;
							break;
						}
						newerSet.add(newConversion);
					}
					
					if(!groupConversionExists)
						return newerSet;
				}
				Group group = new Group();
				for(int i = 0; i < newSet.size(); i++) {
					List<Object> newList = new ArrayList<>();
					for(Object setElement : set) {
						Object newConversion = TypeConversion.convert(setElement, new ArrayList<>(newSet).get(i));
						newList.add(newConversion);
					}

					List<List<Object>> inputCombinations = new ArrayList<>();
					TypeConversion.analyzeGroups(inputCombinations, newList, new ArrayList<>(), 0);
					for(List<Object> inputCombination : inputCombinations) {
						Set<Object> newerSet = (Set<Object>) TypeConversion.deepCopy(newSet);
						newerSet.clear();
						newerSet.addAll(inputCombination);
						group.addConversion(newerSet);
					}
				}
				return group;
			}
			Set<Object> newerSet = (Set<Object>) TypeConversion.deepCopy(newSet);
			return newerSet;
		} catch(Exception e) {
			return null;
		}
	}
}