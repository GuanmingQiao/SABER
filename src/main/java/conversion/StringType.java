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

public class StringType extends Conversion {

	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		return xmlWrapper.obj.toString();
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		return map.toString();
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		return collection.toString();
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		return list.toString();
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		return set.toString();
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		return stack.toString();
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		return queue.toString();
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		return comp.toString();
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		return enumeration.toString();
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		return iterator.toString();
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		return listIterator.toString();
	}

	@Override
	public Object convertString(String str, Object obj) {
		return str;
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return Integer.toString(i);
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		return Double.toString(d);
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		return Float.toString(f);
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		return Long.toString(l);
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return Byte.toString(b);
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return Short.toString(s);
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return Character.toString(c);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		return Boolean.toString(b);
	}
}