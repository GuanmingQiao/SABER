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

public class FloatType extends Conversion {

	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		try {
			return (Float) xmlWrapper.obj;
		} catch(Exception e) {
			return (Float) ((float) (xmlWrapper.deepHash));
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		return (Float) ((float) map.size());
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		return (Float) ((float) collection.size());
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		return (Float) ((float) list.size());
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		return (Float) ((float) set.size());
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		return (Float) ((float) stack.size());
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		return (Float) ((float) queue.size());
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		return (Float) ((float) comp.hashCode());
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		return (Float) ((float) enumeration.hashCode());
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		return (Float) ((float) iterator.hashCode());
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		return (Float) ((float) listIterator.hashCode());
	}

	@Override
	public Object convertString(String str, Object obj) {
		try {
			return Float.parseFloat(str);
		} catch(Exception e) {
			return new Float(0);
		}
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return (Float) i.floatValue();
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		if(d < Float.MIN_VALUE)
			return (Float) Float.MIN_VALUE;
		if(d > Float.MAX_VALUE)
			return (Float) Float.MAX_VALUE;
		return (Float) d.floatValue();
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		return f;
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		return (Float) l.floatValue();
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return (Float) b.floatValue();
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return (Float) s.floatValue();
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return (Float) ((float) c);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		if(b == true)
			return new Float(1.0);
		return new Float(0.0);
	}
}