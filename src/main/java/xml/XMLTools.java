package xml;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class XMLTools {

    private static Set<String> blackFields = new HashSet<String>();
    private static Object bfLock = new Object();
	
	public static XStream getXStream() {
        XStream xstream = new XStream() {
        	@Override
        	protected MapperWrapper wrapMapper(MapperWrapper next) {
        		return new MapperWrapper(next) {
        			@Override
        			public boolean shouldSerializeMember(Class definedClass, String fieldName) {
                        if (definedClass == Object.class) {
                            return false;
                        }

                        String fieldKey = definedClass.getName() + "-" + fieldName;
                        synchronized(bfLock) {
                            if (blackFields.contains(fieldKey)) {
                                return false;
                            }
                        }

                        Field f = null;
                        LinkedList<Class> queue = new LinkedList<Class>();
                        queue.add(definedClass);
                        while (queue.size() > 0) {
                            Class clazz = queue.removeFirst();
                            try {
                                f = clazz.getDeclaredField(fieldName);
                            } catch (NoSuchFieldException ex) {									
                                if (clazz.getSuperclass() != null) {
                                    queue.add(clazz.getSuperclass());
                                }
                            }
                        }

                        if (f == null) {
                            // Not in super classes
                            System.out.println(fieldName + " in interfaces");
                            for (Class inter: definedClass.getInterfaces()) {
                                queue.add(inter);
                            }
                        }

                        while (queue.size() > 0) {
                            Class curInter = queue.removeFirst();
                            try {
                                f = curInter.getDeclaredField(fieldName);
                            } catch (NoSuchFieldException ex) {
                                for (Class superInter: curInter.getInterfaces()) {
                                    queue.add(superInter);
                                }
                            }
                        }

                        if (f == null) {
                            System.err.println("Cannot find field: " + fieldKey);
                            return super.shouldSerializeMember(definedClass, fieldName);
                        } else {
                            if (f.isSynthetic()) {
                                System.out.println("Synthetic field: " + fieldKey);
                                synchronized(bfLock) {
                                    blackFields.add(fieldKey);
                                }
                                return false;
                            } else if (BConverter.shouldBlock(f.getType())) {
                                // Don't serialize reader, writer, scanner, stringtokenizer
                                return false;
                            } else {
                                return super.shouldSerializeMember(definedClass, fieldName);
                            }
                        }
        			}
        		};
        	}
        };
//        XStream.setupDefaultSecurity(xstream);
        xstream.ignoreUnknownElements();
        xstream.registerConverter(new BConverter(), XStream.PRIORITY_VERY_HIGH);
        xstream.registerConverter(new EMConverter(), XStream.PRIORITY_VERY_HIGH);
        
        return xstream;
	}
	
	
}