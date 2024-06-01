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

public class ByteType extends Conversion {

	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		try {
			return (Byte) xmlWrapper.obj;
		} catch(Exception e) {
			return (Byte) ((byte) xmlWrapper.deepHash);
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		return (Byte) ((byte) map.size());
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		return (Byte) ((byte) collection.size());
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		return (Byte) ((byte) list.size());
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		return (Byte) ((byte) set.size());
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		return (Byte) ((byte) stack.size());
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		return (Byte) ((byte) queue.size());
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		return (Byte) ((byte) comp.hashCode());
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		return (Byte) ((byte) enumeration.hashCode());
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		return (Byte) ((byte) iterator.hashCode());
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		return (Byte) ((byte) listIterator.hashCode());
	}

	@Override
	public Object convertString(String str, Object obj) {
		try {
			return Byte.parseByte(str);
		} catch(Exception e) {
			return new Byte((byte) 0);
		}
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		if(i < Byte.MIN_VALUE)
			return (Byte) Byte.MIN_VALUE;
		if(i > Byte.MAX_VALUE)
			return (Byte) Byte.MAX_VALUE;
		return (Byte) i.byteValue();
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		if(d < Byte.MIN_VALUE)
			return (Byte) Byte.MIN_VALUE;
		if(d > Byte.MAX_VALUE)
			return (Byte) Byte.MAX_VALUE;
		return (Byte) d.byteValue();
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		if(f < Byte.MIN_VALUE)
			return (Byte) Byte.MIN_VALUE;
		if(f > Byte.MAX_VALUE)
			return (Byte) Byte.MAX_VALUE;
		return (Byte) f.byteValue();
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		if(l < Byte.MIN_VALUE)
			return (Byte) Byte.MIN_VALUE;
		if(l > Byte.MAX_VALUE)
			return (Byte) Byte.MAX_VALUE;
		return (Byte) l.byteValue();
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return b;
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		if(s < Byte.MIN_VALUE)
			return (Byte) Byte.MIN_VALUE;
		if(s > Byte.MAX_VALUE)
			return (Byte) Byte.MAX_VALUE;
		return (Byte) s.byteValue();
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		if(c > Byte.MAX_VALUE)
			return (Byte) Byte.MAX_VALUE;
		return (Byte) ((byte) ((int) c));
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		if(b == true)
			return new Byte((byte) 1);
		return new Byte((byte) 0);
	}
}