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

public class LongType extends Conversion {

	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		try {
			return (Long) xmlWrapper.obj;
		} catch(Exception e) {
			return (Long) ((long) xmlWrapper.deepHash);
		}
	}
	
	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		return (Long) ((long) map.size());
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		return (Long) ((long) collection.size());
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		return (Long) ((long) list.size());
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		return (Long) ((long) set.size());
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		return (Long) ((long) stack.size());
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		return (Long) ((long) queue.size());
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		return (Long) ((long) comp.hashCode());
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		return (Long) ((long) enumeration.hashCode());
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		return (Long) ((long) iterator.hashCode());
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		return (Long) ((long) listIterator.hashCode());
	}

	@Override
	public Object convertString(String str, Object obj) {
		try {
			return Long.parseLong(str);
		} catch(Exception e) {
			return new Long(0);
		}
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return (Long) i.longValue();
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		if(d < Long.MIN_VALUE)
			return (Long) Long.MIN_VALUE;
		if(d > Long.MAX_VALUE)
			return (Long) Long.MAX_VALUE;
		return (Long) d.longValue();
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		if(f < Long.MIN_VALUE)
			return (Long) Long.MIN_VALUE;
		if(f > Long.MAX_VALUE)
			return (Long) Long.MAX_VALUE;
		return (Long) f.longValue();
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		return l;
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return (Long) b.longValue();
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return (Long) s.longValue();
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return (Long) ((long) c);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		if(b == true)
			return new Long(1);
		return new Long(0);
	}
}