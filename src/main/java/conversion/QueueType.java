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
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import xml.XMLWrapper;

public class QueueType extends Conversion {

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
	
												Queue<?> objectCastAsQueue = (Queue<?>) xmlWrapper.obj;
												return convertQueue(objectCastAsQueue, obj);
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

			return makeSingleElementQueueConversion(xmlWrapper.obj, obj);			
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		try {
			Object keysInputs = null, valuesInputs = null;
			try {
				Queue<Object> queue = new PriorityQueue<>();
				for(Object element : map.keySet())
					queue.add(element);
				keysInputs = makeMultipleElementQueueConversion(queue, obj);
			} catch(Exception e) {}
			try {
				Queue<Object> queue = new PriorityQueue<>();
				for(Object element : map.values())
					queue.add(element);
				valuesInputs = makeMultipleElementQueueConversion(queue, obj);
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
			Queue<Object> queue = new PriorityQueue<>();
			for(Object element : collection)
				queue.add(element);
			return makeMultipleElementQueueConversion(queue, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		try {
			Queue<Object> queue = new PriorityQueue<>();
			for(Object element : list)
				queue.add(element);
			return makeMultipleElementQueueConversion(queue, obj);
		} catch(Exception e) {
			return null;
		}

	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		try {
			Queue<Object> queue = new PriorityQueue<>();
			for(Object element : set)
				queue.add(element);
			return makeMultipleElementQueueConversion(queue, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		try {
			Queue<Object> queue = new PriorityQueue<>();
			for(Object element : stack)
				queue.add(element);
			return makeMultipleElementQueueConversion(queue, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		try {
			return makeMultipleElementQueueConversion(queue, obj);
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
			Queue<Object> queue = new PriorityQueue<>();
			while(iterator.hasNext())
				queue.add(iterator.next());
			return makeMultipleElementQueueConversion(queue, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		try {
			Queue<Object> queue = new PriorityQueue<>();
			while(listIterator.hasNext())
				queue.add(listIterator.next());
			return makeMultipleElementQueueConversion(queue, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertString(String str, Object obj) {
		return makeSingleElementQueueConversion(str, obj);
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return makeSingleElementQueueConversion(i, obj);
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		return makeSingleElementQueueConversion(d, obj);
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		return makeSingleElementQueueConversion(f, obj);
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		return makeSingleElementQueueConversion(l, obj);
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return makeSingleElementQueueConversion(b, obj);
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return makeSingleElementQueueConversion(s, obj);
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return makeSingleElementQueueConversion(c, obj);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		return makeSingleElementQueueConversion(b, obj);
	}
	
	private Object makeSingleElementQueueConversion(Object obj1, Object obj2) {
		try {
			List<Class<?>> listOfClasses = new ArrayList<>();

			Queue<Object> newQueue = (Queue<Object>) TypeConversion.deepCopy(obj2);
			if(newQueue.size() == 0)
				return null;
			
			for(Object queueObj : newQueue) {
				if(queueObj.getClass().getCanonicalName().equals("xml.XMLParser"))
					listOfClasses.add(((XMLWrapper) (queueObj)).obj.getClass());
				else
					listOfClasses.add(queueObj.getClass());
			}

			List<Class<?>> commonAncestors = TypeConversion.getCommonAncestors(listOfClasses, false);
			if(commonAncestors.size() != 0) {
				if(newQueue.size() == 1) {
					Object newConversion = TypeConversion.convert(obj1, new ArrayList<>(newQueue).get(0));
					if(!newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
						Queue<Object> newerQueue = (Queue<Object>) TypeConversion.deepCopy(newQueue);
						newerQueue.clear();
						newerQueue.add(newConversion);
						return newerQueue;
					}
				}
				Group group = new Group();
				for(Object object : newQueue) {
					Object newConversion = TypeConversion.convert(obj1, object);
					if(newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
						Group nestedGroup = (Group) newConversion;
						for(Object nestedConversion : nestedGroup.getConversions()) {
							Queue<Object> newerQueue = (Queue<Object>) TypeConversion.deepCopy(newQueue);
							newerQueue.clear();
							newerQueue.add(nestedConversion);
							group.addConversion(newerQueue);
						}
					}
					else {
						Queue<Object> newerQueue = (Queue<Object>) TypeConversion.deepCopy(newQueue);
						newerQueue.clear();
						newerQueue.add(newConversion);
						group.addConversion(newerQueue);
					}
				}
				return group;
			}
			Queue<Object> newerQueue = (Queue<Object>) TypeConversion.deepCopy(newQueue);
			newerQueue.clear();
			newerQueue.add(obj1);
			return newerQueue;
		} catch(Exception e) {
			return null;
		}
	}
	
	private Object makeMultipleElementQueueConversion(Queue<?> queue, Object obj2) {
		try {
			List<Class<?>> listOfClasses = new ArrayList<>();

			Queue<Object> newQueue = (Queue<Object>) TypeConversion.deepCopy(obj2);
			if(newQueue.size() == 0)
				return null;
			
			for(Object queueObj : newQueue) {
				if(queueObj.getClass().getCanonicalName().equals("xml.XMLParser"))
					listOfClasses.add(((XMLWrapper) (queueObj)).obj.getClass());
				else
					listOfClasses.add(queueObj.getClass());
			}

			List<Class<?>> commonAncestors = TypeConversion.getCommonAncestors(listOfClasses, false);
			if(commonAncestors.size() != 0) {
				if(newQueue.size() == 1) {
					boolean groupConversionExists = false;
					Queue<Object> newerQueue = (Queue<Object>) TypeConversion.deepCopy(newQueue);
					newerQueue.clear();
					for(Object queueElement : queue) {
						Object newConversion = TypeConversion.convert(queueElement, new ArrayList<>(newQueue).get(0));
						if(newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
							groupConversionExists = true;
							break;
						}
						newerQueue.add(newConversion);
					}
					
					if(!groupConversionExists)
						return newerQueue;
				}
				Group group = new Group();
				for(int i = 0; i < newQueue.size(); i++) {
					List<Object> newList = new ArrayList<>();
					for(Object queueElement : queue) {
						Object newConversion = TypeConversion.convert(queueElement, new ArrayList<>(newQueue).get(i));
						newList.add(newConversion);
					}

					List<List<Object>> inputCombinations = new ArrayList<>();
					TypeConversion.analyzeGroups(inputCombinations, newList, new ArrayList<>(), 0);
					for(List<Object> inputCombination : inputCombinations) {
						Queue<Object> newerQueue = (Queue<Object>) TypeConversion.deepCopy(newQueue);
						newerQueue.clear();
						newerQueue.addAll(inputCombination);
						group.addConversion(newerQueue);
					}
				}
				return group;
			}
			Queue<Object> newerQueue = (Queue<Object>) TypeConversion.deepCopy(newQueue);
			return newerQueue;
		} catch(Exception e) {
			return null;
		}
	}
}