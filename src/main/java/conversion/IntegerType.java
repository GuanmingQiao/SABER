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

public class IntegerType extends Conversion {

	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		try {
			return (Integer) xmlWrapper.obj;
		} catch(Exception e) {
			return (Integer) xmlWrapper.deepHash;
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		return (Integer) map.size();
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		return (Integer) collection.size();
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		return (Integer) list.size();
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		return (Integer) set.size();
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		return (Integer) stack.size();
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		return (Integer) queue.size();
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		return (Integer) comp.hashCode();
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		return (Integer) enumeration.hashCode();
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		return (Integer) iterator.hashCode();
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		return (Integer) listIterator.hashCode();
	}

	@Override
	public Object convertString(String str, Object obj) {
		try {
			return Integer.parseInt(str);
		} catch(Exception e) {
			return new Integer(0);
		}
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return i;
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		if(d < Integer.MIN_VALUE)
			return (Integer) Integer.MIN_VALUE;
		if(d > Integer.MAX_VALUE)
			return (Integer) Integer.MAX_VALUE;
		return (Integer) d.intValue();
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		if(f < Integer.MIN_VALUE)
			return (Integer) Integer.MIN_VALUE;
		if(f > Integer.MAX_VALUE)
			return (Integer) Integer.MAX_VALUE;
		return (Integer) f.intValue();
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		if(l < Integer.MIN_VALUE)
			return (Integer) Integer.MIN_VALUE;
		if(l > Integer.MAX_VALUE)
			return (Integer) Integer.MAX_VALUE;
		return (Integer) l.intValue();
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return (Integer) b.intValue();
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return (Integer) s.intValue();
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return (Integer) ((int) c);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		if(b == true)
			return new Integer(1);
		return new Integer(0);
	}
}