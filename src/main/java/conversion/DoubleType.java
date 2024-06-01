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

public class DoubleType extends Conversion {

	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		try {
			return (Double) xmlWrapper.obj;
		} catch(Exception e) {
			return (Double) (xmlWrapper.deepHash + 0.0);
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		return (Double) (map.size() + 0.0);
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		return (Double) (collection.size() + 0.0);
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		return (Double) (list.size() + 0.0);
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		return (Double) (set.size() + 0.0);
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		return (Double) (stack.size() + 0.0);
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		return (Double) (queue.size() + 0.0);
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		return (Double) (comp.hashCode() + 0.0);
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		return (Double) (enumeration.hashCode() + 0.0);
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		return (Double) (iterator.hashCode() + 0.0);
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		return (Double) (listIterator.hashCode() + 0.0);
	}

	@Override
	public Object convertString(String str, Object obj) {
		try {
			return Double.parseDouble(str);
		} catch(Exception e) {
			return new Double(0.0);
		}
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		return new Double(i + 0.0);
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		return d;
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		return (Double) f.doubleValue();
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		return (Double) l.doubleValue();
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		return (Double) b.doubleValue();
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		return (Double) s.doubleValue();
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return (Double) ((double) c);
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		if(b == true)
			return new Double(1.0);
		return new Double(0.0);
	}
}