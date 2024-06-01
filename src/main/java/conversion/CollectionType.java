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

public class CollectionType extends Conversion {

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
	
												Collection<?> objectCastAsCollection = (Collection<?>) xmlWrapper.obj;
												return convertCollection(objectCastAsCollection, obj);
											} catch(Exception e) {}
	
											List<?> objectCastAsList = (List<?>) xmlWrapper.obj;
											return convertList(objectCastAsList, obj);
										} catch(Exception e) {}
										
										Map<?, ?> objectCastAsMap = (Map<?, ?>) xmlWrapper.obj;
										return convertMap(objectCastAsMap, obj);
									} catch(Exception e) {}
									
									Set<?> objectCastAsSet = (Set<?>) xmlWrapper.obj;
									return convertSet(objectCastAsSet, obj);
								} catch(Exception e) {}
								
								Stack<?> objectCastAsStack = (Stack<?>) xmlWrapper.obj;
								return convertStack(objectCastAsStack, obj);			
							} catch(Exception e) {}
	
							Queue<?> objectCastAsQueue = (Queue<?>) xmlWrapper.obj;
							return convertQueue(objectCastAsQueue, obj);
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

			return makeSingleElementCollectionConversion(xmlWrapper.obj, obj);			
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		try {
			Object keysInputs = null, valuesInputs = null;
			try {
				Collection<Object> collection = new ArrayList<>();
				for(Object element : map.keySet())
					collection.add(element);
				keysInputs = makeMultipleElementCollectionConversion(collection, obj);
			} catch(Exception e) {}
			try {
				Collection<Object> collection = new ArrayList<>();
				for(Object element : map.values())
					collection.add(element);
				valuesInputs = makeMultipleElementCollectionConversion(collection, obj);
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
			return makeMultipleElementCollectionConversion(collection, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		try {
			Collection<Object> collection = new ArrayList<>();
			for(Object element : list)
				collection.add(element);
			return makeMultipleElementCollectionConversion(collection, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		try {
			Collection<Object> collection = new ArrayList<>();
			for(Object element : set)
				collection.add(element);
			return makeMultipleElementCollectionConversion(collection, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		try {
			Collection<Object> collection = new ArrayList<>();
			for(Object element : stack)
				collection.add(element);
			return makeMultipleElementCollectionConversion(collection, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		try {
			Collection<Object> collection = new ArrayList<>();
			for(Object element : queue)
				collection.add(element);
			return makeMultipleElementCollectionConversion(collection, obj);
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
			Collection<Object> collection = new ArrayList<>();
			while(iterator.hasNext())
				collection.add(iterator.next());
			return makeMultipleElementCollectionConversion(collection, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		try {
			Collection<Object> collection = new ArrayList<>();
			while(listIterator.hasNext())
				collection.add(listIterator.next());
			return makeMultipleElementCollectionConversion(collection, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertString(String str, Object obj) {
		return makeSingleElementCollectionConversion(str, obj);
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return makeSingleElementCollectionConversion(i, obj);
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		return makeSingleElementCollectionConversion(d, obj);
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		return makeSingleElementCollectionConversion(f, obj);
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		return makeSingleElementCollectionConversion(l, obj);
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return makeSingleElementCollectionConversion(b, obj);
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return makeSingleElementCollectionConversion(s, obj);
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return makeSingleElementCollectionConversion(c, obj);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		return makeSingleElementCollectionConversion(b, obj);
	}
	
	private Object makeSingleElementCollectionConversion(Object obj1, Object obj2) {
		try {
			List<Class<?>> listOfClasses = new ArrayList<>();

			Collection<Object> newCollection = (Collection<Object>) TypeConversion.deepCopy(obj2);
			if(newCollection.size() == 0)
				return null;
			
			for(Object collectionObj : newCollection) {
				if(collectionObj.getClass().getCanonicalName().equals("xml.XMLParser"))
					listOfClasses.add(((XMLWrapper) (collectionObj)).obj.getClass());
				else
					listOfClasses.add(collectionObj.getClass());
			}

			List<Class<?>> commonAncestors = TypeConversion.getCommonAncestors(listOfClasses, false);
			if(commonAncestors.size() != 0) {
				if(newCollection.size() == 1) {
					Object newConversion = TypeConversion.convert(obj1, new ArrayList<>(newCollection).get(0));
					if(!newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
						Collection<Object> newerCollection = (Collection<Object>) TypeConversion.deepCopy(newCollection);
						newerCollection.clear();
						newerCollection.add(newConversion);
						return newerCollection;
					}
				}
				Group group = new Group();
				for(Object object : newCollection) {
					Object newConversion = TypeConversion.convert(obj1, object);
					if(newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
						Group nestedGroup = (Group) newConversion;
						for(Object nestedConversion : nestedGroup.getConversions()) {
							Collection<Object> newerCollection = (Collection<Object>) TypeConversion.deepCopy(newCollection);
							newerCollection.clear();
							newerCollection.add(nestedConversion);
							group.addConversion(newerCollection);
						}
					}
					else {
						Collection<Object> newerCollection = (Collection<Object>) TypeConversion.deepCopy(newCollection);
						newerCollection.clear();
						newerCollection.add(newConversion);
						group.addConversion(newerCollection);
					}
				}
				return group;
			}
			Collection<Object> newerCollection = (Collection<Object>) TypeConversion.deepCopy(newCollection);
			newerCollection.clear();
			newerCollection.add(obj1);
			return newerCollection;
		} catch(Exception e) {
			return null;
		}
	}
	
	private Object makeMultipleElementCollectionConversion(Collection<?> collection, Object obj2) {
		try {
			List<Class<?>> listOfClasses = new ArrayList<>();

			Collection<Object> newCollection = (Collection<Object>) TypeConversion.deepCopy(obj2);
			if(newCollection.size() == 0)
				return null;
			
			for(Object collectionObj : newCollection) {
				if(collectionObj.getClass().getCanonicalName().equals("xml.XMLParser"))
					listOfClasses.add(((XMLWrapper) (collectionObj)).obj.getClass());
				else
					listOfClasses.add(collectionObj.getClass());
			}

			List<Class<?>> commonAncestors = TypeConversion.getCommonAncestors(listOfClasses, false);
			if(commonAncestors.size() != 0) {
				if(newCollection.size() == 1) {
					boolean groupConversionExists = false;
					Collection<Object> newerCollection = (Collection<Object>) TypeConversion.deepCopy(newCollection);
					newerCollection.clear();
					for(Object collectionElement : collection) {
						Object newConversion = TypeConversion.convert(collectionElement, new ArrayList<>(newCollection).get(0));
						if(newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
							groupConversionExists = true;
							break;
						}
						newerCollection.add(newConversion);
					}
					
					if(!groupConversionExists)
						return newerCollection;
				}
				Group group = new Group();
				for(int i = 0; i < newCollection.size(); i++) {
					List<Object> newList = new ArrayList<>();
					for(Object collectionElement : collection) {
						Object newConversion = TypeConversion.convert(collectionElement, new ArrayList<>(newCollection).get(i));
						newList.add(newConversion);
					}

					List<List<Object>> inputCombinations = new ArrayList<>();
					TypeConversion.analyzeGroups(inputCombinations, newList, new ArrayList<>(), 0);
					for(List<Object> inputCombination : inputCombinations) {
						Collection<Object> newerCollection = (Collection<Object>) TypeConversion.deepCopy(newCollection);
						newerCollection.clear();
						newerCollection.addAll(inputCombination);
						group.addConversion(newerCollection);
					}
				}
				return group;
			}
			Collection<Object> newerCollection = (Collection<Object>) TypeConversion.deepCopy(newCollection);
			return newerCollection;
		} catch(Exception e) {
			return null;
		}
	}
}