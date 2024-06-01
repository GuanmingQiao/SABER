package managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ClassInfoUtils;

public class ClassManager {
	
	private static List<Class<?>> classesList = new ArrayList<>();
	private static List<ClassInfoUtils> classInfoUtilsList = new ArrayList<>();
	private static Map<Class<?>, ClassInfoUtils> classToCIUMap = new HashMap<>();
	
	public static void addClass(Class<?> clazz) {
		addClassToList(clazz);
		if(!classToCIUMap.containsKey(clazz)) {
			ClassInfoUtils ciu = new ClassInfoUtils(clazz);
			classInfoUtilsList.add(ciu);
			classToCIUMap.put(clazz, ciu);
		}
		else {
			ClassInfoUtils ciu = classToCIUMap.get(clazz);
			ciu.setClass(clazz);
		}
	}
	
	public static void addClass(Class<?> clazz,
			int version, 
			int access, 
			String name, 
			String signature,
			String superclassName,
			String[] interfaces) {
		addClassToList(clazz);
		if(!classToCIUMap.containsKey(clazz)) {
			ClassInfoUtils ciu = new ClassInfoUtils(clazz, version, access, name, signature, superclassName, interfaces);
			classInfoUtilsList.add(ciu);
			classToCIUMap.put(clazz, ciu);
		}
		else {
			ClassInfoUtils ciu = classToCIUMap.get(clazz);
			ciu.setInfo(clazz, version, access, name, signature, superclassName, interfaces);
		}
	}
	
	public static void addClass(int version,
			int access,
			String name, 
			String signature,
			String superclassName,
			String[] interfaces) {
		boolean ciuFound = false;
		for(ClassInfoUtils ciu : classInfoUtilsList) {
			if(ciu.getName().equals(name)) {
				Class<?> clazz = ciu.getClass();
				ciu.setInfo(clazz, version, access, name, signature, superclassName, interfaces);
				ciuFound = true;
			}
		}
		if(!ciuFound) {
			ClassInfoUtils ciu = new ClassInfoUtils(null, version, access, name, signature, superclassName, interfaces);
			classInfoUtilsList.add(ciu);
		}
	}
	
	private static void addClassToList(Class<?> clazz) {
		if(!classesList.contains(clazz))
			classesList.add(clazz);
	}
	
	public static List<Class<?>> getClassesList() {
		return classesList;
	}
	
	public static boolean hasClass(Class<?> clazz) {
		return classesList.contains(clazz);
	}
	
	public static ClassInfoUtils getClassInfoUtils(Class<?> clazz) {
		if(classToCIUMap.containsKey(clazz))
			return classToCIUMap.get(clazz);
		return null;
	}
	
	public static Map<Class<?>, ClassInfoUtils> getClassToCIUMap() {
		return classToCIUMap;
	}
	
	public static List<ClassInfoUtils> getClassInfoUtilsList() {
		return classInfoUtilsList;
	}
		
	public static void reset() {
		classesList = new ArrayList<>();
		classInfoUtilsList = new ArrayList<>();
		classToCIUMap = new HashMap<>();
	}
}