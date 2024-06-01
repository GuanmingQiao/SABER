package conversion;

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

public abstract class Conversion {
	
	public Object getObject(Object obj1, Object obj2) {
		String typeWithoutArraySuffix = obj1.getClass().getCanonicalName().split("\\[\\]")[0];

		switch(typeWithoutArraySuffix) {
		case "java.lang.Boolean":
		case "boolean":
			return convertBoolean((Boolean) obj1, obj2);
		case "java.lang.Byte":
		case "byte":
			return convertByte((Byte) obj1, obj2);
		case "java.lang.Character":
		case "char":
			return convertCharacter((Character) obj1, obj2);
		case "java.lang.Double":
		case "double":
			return convertDouble((Double) obj1, obj2);
		case "java.lang.Float":
		case "float":
			return convertFloat((Float) obj1, obj2);
		case "java.lang.Integer":
		case "int":
			return convertInteger((Integer) obj1, obj2);
		case "java.lang.Long":
		case "long":
			return convertLong((Long) obj1, obj2);
		case "java.lang.Short":
		case "short":
			return convertShort((Short) obj1, obj2);
		case "java.lang.String":
			return convertString((String) obj1, obj2);
		case "java.util.LinkedList":
		case "java.util.ArrayList":
		case "java.util.Vector":
		case "java.util.AbstractList":
		case "java.util.List":
			return convertList((List<?>) obj1, obj2);
		case "java.util.HashSet":
		case "java.util.TreeSet":
		case "java.util.LinkedHashSet":
		case "java.util.AbstractSet":
		case "java.util.Set":
			return convertSet((Set<?>) obj1, obj2);
		case "java.util.HashMap":
		case "java.util.TreeMap":
		case "java.util.IdentityHashMap":
		case "java.util.LinkedHashMap":
		case "java.util.WeakHashMap":
		case "java.util.Hashtable":
		case "java.util.AbstractMap":
		case "java.util.Map":
			return convertMap((Map<?, ?>) obj1, obj2);
		case "java.util.PriorityQueue":
		case "java.util.AbstractQueue":
		case "java.util.Queue":
			return convertQueue((Queue<?>) obj1, obj2);
		case "java.util.Stack":
			return convertStack((Stack<?>) obj1, obj2);
		case "java.util.Collection":
			return convertCollection((Collection<?>) obj1, obj2);
		case "java.util.Comparator":
			return convertComparator((Comparator<?>) obj1, obj2);
		case "java.util.Enumeration":
			return convertEnumeration((Enumeration<?>) obj1, obj2);
		case "java.util.Iterator":
			return convertIterator((Iterator<?>) obj1, obj2);
		case "java.util.ListIterator":
			return convertListIterator((ListIterator<?>) obj1, obj2);
		case "xml.XMLWrapper":
			return convertObject((XMLWrapper) obj1, obj2);
		default:
			return null;
		}
	}
	public abstract Object convertObject(XMLWrapper xmlWrapper, Object obj);
	public abstract Object convertMap(Map<?, ?> map, Object obj);
	public abstract Object convertCollection(Collection<?> collection, Object obj);
	public abstract Object convertList(List<?> list, Object obj);
	public abstract Object convertSet(Set<?> set, Object obj);
	public abstract Object convertStack(Stack<?> stack, Object obj);
	public abstract Object convertQueue(Queue<?> queue, Object obj);
	public abstract Object convertComparator(Comparator<?> comp, Object obj);
	public abstract Object convertEnumeration(Enumeration<?> enumeration, Object obj);
	public abstract Object convertIterator(Iterator<?> iterator, Object obj);
	public abstract Object convertListIterator(ListIterator<?> listIterator, Object obj);
	public abstract Object convertString(String str, Object obj);
	public abstract Object convertInteger(Integer i, Object obj);
	public abstract Object convertDouble(Double d, Object obj);
	public abstract Object convertFloat(Float f, Object obj);
	public abstract Object convertLong(Long l, Object obj);
	public abstract Object convertByte(Byte b, Object obj);
	public abstract Object convertShort(Short s, Object obj);
	public abstract Object convertCharacter(Character c, Object obj);
	public abstract Object convertBoolean(Boolean b, Object obj);	
}