package xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import xml.BConverter;
import xml.EMConverter;
import pojo.IORecord;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

public class XMLParser {

    private IORecord oldInput;
    private IORecord newInput;

    private IORecord getIORecord(File xmlFile) {
        Set<String> blackFields = new HashSet<>();
        Object bfLock = new Object();
        try {
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
                            synchronized (bfLock) {
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
                                for (Class inter : definedClass.getInterfaces()) {
                                    queue.add(inter);
                                }
                            }
                            while (queue.size() > 0) {
                                Class curInter = queue.removeFirst();
                                try {
                                    f = curInter.getDeclaredField(fieldName);
                                } catch (NoSuchFieldException ex) {
                                    for (Class superInter : curInter.getInterfaces()) {
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
                                    synchronized (bfLock) {
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
            xstream.ignoreUnknownElements();
            xstream.registerConverter(new BConverter(), XStream.PRIORITY_VERY_HIGH);
            xstream.registerConverter(new EMConverter(), XStream.PRIORITY_VERY_HIGH);
            return (IORecord) xstream.fromXML(xmlFile);
        } catch (Exception e) {
            throw e;
        }
    }

    public IORecord getOldInput() {
        return this.oldInput;
    }

    public IORecord getNewInput() {
        return this.newInput;
    }

    public Object getNewInputByIndex(int index) {
        if (index > this.newInput.sortedInputs.size()) {
            return null;
        }
        Object input = this.newInput.sortedInputs.get(index);
        if (input.getClass().equals(xml.XMLWrapper.class)) {
            xml.XMLWrapper wrapper = (xml.XMLWrapper) input;
            return wrapper.obj;
        } else {
            return input;
        }
    }

    public Object getOldInputByIndex(int index) {
        if (index > this.oldInput.sortedInputs.size()) {
            return null;
        }
        Object input = this.oldInput.sortedInputs.get(index);
        if (input.getClass().equals(xml.XMLWrapper.class)) {
            xml.XMLWrapper wrapper = (xml.XMLWrapper) input;
            return wrapper.obj;
        } else {
            return input;
        }
    }

    public XMLParser(IORecord originalIORecord, IORecord newIORecord) {
        this.oldInput = originalIORecord;
        this.newInput = newIORecord;
    }
    
    public XMLParser(String filePath){
        this.newInput = getIORecord(new File(filePath));
    }
}
