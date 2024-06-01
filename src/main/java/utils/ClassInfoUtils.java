package utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassInfoUtils extends GeneralUtils {
	
	private Class<?> clazz;
	private String name;
	private int version;
	private int access;
	private String accessType;
	private String signature;
	private String superclassName;
	private String[] interfaces;
	private List<Method> methods;
	private List<GlobalVariableInfoUtils> globalVariables;
		
	public ClassInfoUtils(Class<?> clazz) {
		setClass(clazz);
	}
	
	public ClassInfoUtils(Class<?> clazz,
			int version, 
			int access, 
			String name, 
			String signature,
			String superclassName,
			String[] interfaces) {
		setInfo(clazz, version, access, name, signature, superclassName, interfaces);
	}
	
	public void setInfo(Class<?> clazz,
			int version, 
			int access, 
			String name, 
			String signature,
			String superclassName,
			String[] interfaces) {
		
		if(clazz != null) {
			this.clazz = clazz;
			this.methods = new ArrayList<>(Arrays.asList(clazz.getMethods()));
		}
		else
			this.methods = new ArrayList<>();
		this.version = version;
		this.access = access;
		this.accessType = getAccessType(access);
		this.name = name;
		this.signature = signature;
		this.superclassName = superclassName;
		this.interfaces = interfaces;
		this.globalVariables = new ArrayList<>();
	}
	
	public Class<?> getFullClass() {
		return clazz;
	}
	
	public void setClass(Class<?> clazz) {
		this.clazz = clazz;
		this.methods = new ArrayList<>(Arrays.asList(clazz.getMethods()));
		this.globalVariables = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getAccess() {
		return access;
	}
	
	public String getAccessType() {
		return accessType;
	}

	public void setAccess(int access) {
		this.access = access;
		this.accessType = getAccessType(access);
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSuperclassName() {
		return superclassName;
	}

	public void setSuperclassName(String superclassName) {
		this.superclassName = superclassName;
	}

	public String[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(String[] interfaces) {
		this.interfaces = interfaces;
	}
	
	public List<Method> getAllMethods() {
		return methods;
	}
	
	public int getNumMethods() {
		return methods.size();
	}

	public List<GlobalVariableInfoUtils> getGlobalVariables() {
		return globalVariables;
	}

	public void addGlobalVariable(int access,
			String name,
			String descriptor,
			String signature,
			Object value) {
		GlobalVariableInfoUtils gviu = new GlobalVariableInfoUtils(access, name, descriptor, signature, value);
		if(!globalVariables.contains(gviu))
			globalVariables.add(gviu);
	}

	@Override
	public void printInfo() {
		System.out.println("Class: " + name);
		System.out.println("(1) Version: " + version);
		System.out.println("(2) Access: " + accessType);
		System.out.println("(3) Signature: " + signature);
		System.out.println("(4) Superclass name: " + superclassName);
		if(interfaces != null)
			System.out.println("(5) Number of interfaces: " + interfaces.length);
		if(methods != null)
			System.out.println("(6) Number of methods: " + methods.size());
		if(globalVariables != null) {
			System.out.println("(7) Number of global variables: " + globalVariables.size());
			for(GlobalVariableInfoUtils gviu : globalVariables)
				gviu.printInfo();
		}
	}

	@Override
	public void reset() {
		
	}
}