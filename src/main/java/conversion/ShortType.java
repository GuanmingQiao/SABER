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

public class ShortType extends Conversion {

	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		try {
			return (Short) xmlWrapper.obj;
		} catch(Exception e) {
			return (Short) ((short) xmlWrapper.deepHash);
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		return (Short) ((short) map.size());
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		return (Short) ((short) collection.size());
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		return (Short) ((short) list.size());
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		return (Short) ((short) set.size());
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		return (Short) ((short) stack.size());
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		return (Short) ((short) queue.size());
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		return (Short) ((short) comp.hashCode());
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		return (Short) ((short) enumeration.hashCode());
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		return (Short) ((short) iterator.hashCode());
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		return (Short) ((short) listIterator.hashCode());
	}

	@Override
	public Object convertString(String str, Object obj) {
		try {
			return Short.parseShort(str);
		} catch(Exception e) {
			return new Short((short) 0);
		}
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		if(i < Short.MIN_VALUE)
			return (Short) Short.MIN_VALUE;
		if(i > Short.MAX_VALUE)
			return (Short) Short.MAX_VALUE;
		return (Short) i.shortValue();
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		if(d < Short.MIN_VALUE)
			return (Short) Short.MIN_VALUE;
		if(d > Short.MAX_VALUE)
			return (Short) Short.MAX_VALUE;
		return (Short) d.shortValue();
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		if(f < Short.MIN_VALUE)
			return (Short) Short.MIN_VALUE;
		if(f > Short.MAX_VALUE)
			return (Short) Short.MAX_VALUE;
		return (Short) f.shortValue();
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		if(l < Short.MIN_VALUE)
			return (Short) Short.MIN_VALUE;
		if(l > Short.MAX_VALUE)
			return (Short) Short.MAX_VALUE;
		return (Short) l.shortValue();
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return (Short) b.shortValue();
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return s;
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		if(c > Short.MAX_VALUE)
			return (Short) Short.MAX_VALUE;
		return (Short) ((short) ((int) c));
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		if(b == true)
			return new Short((short) 1);
		return new Short((short) 0);
		
	}
}