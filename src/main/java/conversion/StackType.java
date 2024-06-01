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

public class StackType extends Conversion {

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
	
												Stack<?> objectCastAsStack = (Stack<?>) xmlWrapper.obj;
												return convertStack(objectCastAsStack, obj);			
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

			return makeSingleElementStackConversion(xmlWrapper.obj, obj);			
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		try {
			Object keysInputs = null, valuesInputs = null;
			try {
				Stack<Object> stack = new Stack<>();
				for(Object element : map.keySet())
					stack.add(element);
				keysInputs = makeMultipleElementStackConversion(stack, obj);
			} catch(Exception e) {}
			try {
				Stack<Object> stack = new Stack<>();
				for(Object element : map.values())
					stack.add(element);
				valuesInputs = makeMultipleElementStackConversion(stack, obj);
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
			Stack<Object> stack = new Stack<>();
			for(Object element : collection)
				stack.add(element);
			return makeMultipleElementStackConversion(stack, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		try {
			Stack<Object> stack = new Stack<>();
			for(Object element : list)
				stack.add(element);
			return makeMultipleElementStackConversion(stack, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		try {
			Stack<Object> stack = new Stack<>();
			for(Object element : set)
				stack.add(element);
			return makeMultipleElementStackConversion(stack, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		try {
			return makeMultipleElementStackConversion(stack, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		try {
			Stack<Object> stack = new Stack<>();
			for(Object element : queue)
				stack.add(element);
			return makeMultipleElementStackConversion(stack, obj);
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
			Stack<Object> stack = new Stack<>();
			while(iterator.hasNext())
				stack.add(iterator.next());
			return makeMultipleElementStackConversion(stack, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		try {
			Stack<Object> stack = new Stack<>();
			while(listIterator.hasNext())
				stack.add(listIterator.next());
			return makeMultipleElementStackConversion(stack, obj);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertString(String str, Object obj) {
		return makeSingleElementStackConversion(str, obj);
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return makeSingleElementStackConversion(i, obj);
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		return makeSingleElementStackConversion(d, obj);
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		return makeSingleElementStackConversion(f, obj);
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		return makeSingleElementStackConversion(l, obj);
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return makeSingleElementStackConversion(b, obj);
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return makeSingleElementStackConversion(s, obj);
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return makeSingleElementStackConversion(c, obj);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		return makeSingleElementStackConversion(b, obj);
	}
	
	private Object makeSingleElementStackConversion(Object obj1, Object obj2) {
		try {
			List<Class<?>> listOfClasses = new ArrayList<>();

			Stack<Object> newStack = (Stack<Object>) TypeConversion.deepCopy(obj2);
			if(newStack.size() == 0)
				return null;
			
			for(Object stackObj : newStack) {
				if(stackObj.getClass().getCanonicalName().equals("xml.XMLParser"))
					listOfClasses.add(((XMLWrapper) (stackObj)).obj.getClass());
				else
					listOfClasses.add(stackObj.getClass());
			}

			List<Class<?>> commonAncestors = TypeConversion.getCommonAncestors(listOfClasses, false);
			if(commonAncestors.size() != 0) {
				if(newStack.size() == 1) {
					Object newConversion = TypeConversion.convert(obj1, new ArrayList<>(newStack).get(0));
					if(!newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
						Stack<Object> newerStack = (Stack<Object>) TypeConversion.deepCopy(newStack);
						newerStack.clear();
						newerStack.add(newConversion);
						return newerStack;
					}
				}
				Group group = new Group();
				for(Object object : newStack) {
					Object newConversion = TypeConversion.convert(obj1, object);
					if(newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
						Group nestedGroup = (Group) newConversion;
						for(Object nestedConversion : nestedGroup.getConversions()) {
							Stack<Object> newerStack = (Stack<Object>) TypeConversion.deepCopy(newStack);
							newerStack.clear();
							newerStack.add(nestedConversion);
							group.addConversion(newerStack);
						}
					}
					else {
						Stack<Object> newerStack = (Stack<Object>) TypeConversion.deepCopy(newStack);
						newerStack.clear();
						newerStack.add(newConversion);
						group.addConversion(newerStack);
					}
				}
				return group;
			}
			Stack<Object> newerStack = (Stack<Object>) TypeConversion.deepCopy(newStack);
			newerStack.clear();
			newerStack.add(obj1);
			return newerStack;
		} catch(Exception e) {
			return null;
		}
	}
	
	private Object makeMultipleElementStackConversion(Stack<?> stack, Object obj2) {
		try {
			List<Class<?>> listOfClasses = new ArrayList<>();

			Stack<Object> newStack = (Stack<Object>) TypeConversion.deepCopy(obj2);
			if(newStack.size() == 0)
				return null;
			
			for(Object stackObj : newStack) {
				if(stackObj.getClass().getCanonicalName().equals("xml.XMLParser"))
					listOfClasses.add(((XMLWrapper) (stackObj)).obj.getClass());
				else
					listOfClasses.add(stackObj.getClass());
			}

			List<Class<?>> commonAncestors = TypeConversion.getCommonAncestors(listOfClasses, false);
			if(commonAncestors.size() != 0) {
				if(newStack.size() == 1) {
					boolean groupConversionExists = false;
					Stack<Object> newerStack = (Stack<Object>) TypeConversion.deepCopy(newStack);
					newerStack.clear();
					for(Object stackElement : stack) {
						Object newConversion = TypeConversion.convert(stackElement, new ArrayList<>(newStack).get(0));
						if(newConversion.getClass().getCanonicalName().equals("conversion.Group")) {
							groupConversionExists = true;
							break;
						}
						newerStack.add(newConversion);
					}
					
					if(!groupConversionExists)
						return newerStack;
				}
				Group group = new Group();
				for(int i = 0; i < newStack.size(); i++) {
					List<Object> newList = new ArrayList<>();
					for(Object stackElement : stack) {
						Object newConversion = TypeConversion.convert(stackElement, new ArrayList<>(newStack).get(i));
						newList.add(newConversion);
					}

					List<List<Object>> inputCombinations = new ArrayList<>();
					TypeConversion.analyzeGroups(inputCombinations, newList, new ArrayList<>(), 0);
					for(List<Object> inputCombination : inputCombinations) {
						Stack<Object> newerStack = (Stack<Object>) TypeConversion.deepCopy(newStack);
						newerStack.clear();
						newerStack.addAll(inputCombination);
						group.addConversion(newerStack);
					}
				}
				return group;
			}
			Stack<Object> newerStack = (Stack<Object>) TypeConversion.deepCopy(newStack);
			return newerStack;
		} catch(Exception e) {
			return null;
		}
	}
}