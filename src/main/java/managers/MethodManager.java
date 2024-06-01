package managers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.MethodInfoUtils;

public class MethodManager {

	private static List<MethodInfoUtils> methodInfoUtilsList = new ArrayList<>();
	private static Map<Class<?>, Map<Method, MethodInfoUtils>> generalMIUMap = new HashMap<>();
	
	public static void addMethod(Method method, Class<?> clazz) {
		if(!generalMIUMap.containsKey(clazz)) {
			Map<Method, MethodInfoUtils> methodToMIUMap = new HashMap<>();
			MethodInfoUtils miu = new MethodInfoUtils(method, clazz);
			methodToMIUMap.put(method, miu);
			methodInfoUtilsList.add(miu);
			generalMIUMap.put(clazz, methodToMIUMap);
		}
		else {
			Map<Method, MethodInfoUtils> methodToMIUMap = generalMIUMap.get(clazz);
			if(!methodToMIUMap.containsKey(method)) {
				MethodInfoUtils miu = new MethodInfoUtils(method, clazz);
				methodToMIUMap.put(method, miu);
				methodInfoUtilsList.add(miu);
			}
			else {
				MethodInfoUtils miu = generalMIUMap.get(clazz).get(method);
				miu.setMethod(method, clazz);				
			}
		}
	}
	
	public static void addMethod(Method method,
			Class<?> clazz,
			int access,
			String name,
			String descriptor,
			String signature,
			String[] exceptions) {
		if(!generalMIUMap.containsKey(clazz)) {
			Map<Method, MethodInfoUtils> methodToMIUMap = new HashMap<>();
			MethodInfoUtils miu = new MethodInfoUtils(method, clazz, access, name, descriptor, signature, exceptions);
			methodToMIUMap.put(method, miu);
			methodInfoUtilsList.add(miu);
			generalMIUMap.put(clazz, methodToMIUMap);
		}
		else {
			Map<Method, MethodInfoUtils> methodToMIUMap = generalMIUMap.get(clazz);
			if(!methodToMIUMap.containsKey(method)) {
				MethodInfoUtils miu = new MethodInfoUtils(method, clazz, access, name, descriptor, signature, exceptions);
				methodToMIUMap.put(method, miu);
				methodInfoUtilsList.add(miu);
			}
			else {
				MethodInfoUtils miu = generalMIUMap.get(clazz).get(method);
				miu.setInfo(method, clazz, access, name, descriptor, signature, exceptions);
			}			
		}
	}
	
	public static MethodInfoUtils getMethodInfoUtils(Method method, Class clazz) {
		if(generalMIUMap.get(clazz).containsKey(method))
			return generalMIUMap.get(clazz).get(method);
		return null;
	}
		
	public static List<MethodInfoUtils> getMethodInfoUtilsList() {
		return methodInfoUtilsList;
	}
		
	public static Map<Class<?>, Map<Method, MethodInfoUtils>> getGeneralMIUMap() {
		return generalMIUMap;
	}
		
	public static void reset() {
		methodInfoUtilsList = new ArrayList<>();
		generalMIUMap = new HashMap<>();
	}
}