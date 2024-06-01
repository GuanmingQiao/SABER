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

public class CharacterType extends Conversion {

	@Override
	public Object convertObject(XMLWrapper xmlWrapper, Object obj) {
		try {
			return (Character) xmlWrapper.obj;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Object convertMap(Map<?, ?> map, Object obj) {
		return null;
	}

	@Override
	public Object convertCollection(Collection<?> collection, Object obj) {
		return null;
	}

	@Override
	public Object convertList(List<?> list, Object obj) {
		return null;
	}

	@Override
	public Object convertSet(Set<?> set, Object obj) {
		return null;
	}

	@Override
	public Object convertStack(Stack<?> stack, Object obj) {
		return null;
	}

	@Override
	public Object convertQueue(Queue<?> queue, Object obj) {
		return null;
	}

	@Override
	public Object convertComparator(Comparator<?> comp, Object obj) {
		return null;
	}

	@Override
	public Object convertEnumeration(Enumeration<?> enumeration, Object obj) {
		return null;
	}

	@Override
	public Object convertIterator(Iterator<?> iterator, Object obj) {
		return null;
	}

	@Override
	public Object convertListIterator(ListIterator<?> listIterator, Object obj) {
		return null;
	}

	@Override
	public Object convertString(String str, Object obj) {
		if(str == null || str.length() == 0)
			return null;
		return str.charAt(0);
	}

	@Override
	public Object convertInteger(Integer i, Object obj) {
		if(i < Character.MIN_VALUE)
			return (Character) Character.MIN_VALUE;
		if(i > Character.MAX_VALUE)
			return (Character) Character.MAX_VALUE;
		return (Character) ((char) ((int) i));
	}

	@Override
	public Object convertDouble(Double d, Object obj) {
		if(d < Character.MIN_VALUE)
			return (Character) Character.MIN_VALUE;
		if(d > Character.MAX_VALUE)
			return (Character) Character.MAX_VALUE;
		return (Character) ((char) ((double) d));
	}

	@Override
	public Object convertFloat(Float f, Object obj) {
		if(f < Character.MIN_VALUE)
			return (Character) Character.MIN_VALUE;
		if(f > Character.MAX_VALUE)
			return (Character) Character.MAX_VALUE;
		return (Character) ((char) ((float) f));
	}

	@Override
	public Object convertLong(Long l, Object obj) {
		if(l < Character.MIN_VALUE)
			return (Character) Character.MIN_VALUE;
		if(l > Character.MAX_VALUE)
			return (Character) Character.MAX_VALUE;
		return (Character) ((char) ((long) l));
	}

	@Override
	public Object convertByte(Byte b, Object obj) {
		if(b < Character.MIN_VALUE)
			return (Character) Character.MIN_VALUE;
		return (Character) ((char) ((byte) b));
	}

	@Override
	public Object convertShort(Short s, Object obj) {
		if(s < Character.MIN_VALUE)
			return (Character) Character.MIN_VALUE;
		return (Character) ((char) ((short) s));
	}

	@Override
	public Object convertCharacter(Character c, Object obj) {
		return c;
	}

	@Override
	public Object convertBoolean(Boolean b, Object obj) {
		return null;
	}
}