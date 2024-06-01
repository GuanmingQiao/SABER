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

public class BooleanType extends Conversion {

	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		try {
			return (Boolean) xmlWrapper.obj;
		} catch(Exception e) {
			if(obj == null)
				return new Boolean(false);
			return new Boolean(true);
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		if(map.size() > 0)
			return new Boolean(true);
		return new Boolean(false);
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		if(collection.size() > 0)
			return new Boolean(true);
		return new Boolean(false);
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		if(list.size() > 0)
			return new Boolean(true);
		return new Boolean(false);
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		if(set.size() > 0)
			return new Boolean(true);
		return new Boolean(false);
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		if(stack.size() > 0)
			return new Boolean(true);
		return new Boolean(false);
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		if(queue.size() > 0)
			return new Boolean(true);
		return new Boolean(false);
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		if(comp == null)
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		if(enumeration == null)
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		if(iterator == null)
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		if(listIterator == null)
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertString(String str, Object obj) {
		try {
			return Boolean.parseBoolean(str);
		} catch(Exception e) {
			if(str == null || str.length() == 0)
				return new Boolean(false);
			return new Boolean(true);
		}
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		if(i == 0)
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		if(d == 0.0)
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		if(f == 0.0)
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		if(l == 0)
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		if(b == 0)
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		if(s == 0)
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		if(c == null || c == '0')
			return new Boolean(false);
		return new Boolean(true);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		return b;
	}
}