package utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.objectweb.asm.Label;

import managers.MethodManager;

public class MethodInfoUtils extends GeneralUtils {
	
	private Method method;
	private Class clazz;
	private int access;
	private String accessType;
	private String name;
	private String descriptor;
	private String signature;
	private String[] exceptions;
	private List<Parameter> parameters;
	private List<LocalVariableInfoUtils> localVariables;
//	private List<IONode> inputSources;
//	private List<IONode> outputSinks;

	public MethodInfoUtils(Method method, Class clazz) {
//		this.inputSources = new ArrayList<>();
//		this.outputSinks = new ArrayList<>();
		this.parameters = new ArrayList<Parameter>();
		this.localVariables = new ArrayList<LocalVariableInfoUtils>();
		setMethod(method, clazz);
	}
	
	public MethodInfoUtils(Method method,
			Class clazz,
			int access,
			String name,
			String descriptor,
			String signature,
			String[] exceptions) {
//		this.inputSources = new ArrayList<>();
//		this.outputSinks = new ArrayList<>();
		this.parameters = new ArrayList<Parameter>();
		this.localVariables = new ArrayList<LocalVariableInfoUtils>();
		setInfo(method, clazz, access, name, descriptor, signature, exceptions);
	}
	
	public Method getMethod() {
		return method;
	}
	
	public void setMethod(Method method, Class clazz) {
		this.method = method;
		this.clazz = clazz;
		this.parameters = new ArrayList<>(Arrays.asList(method.getParameters()));
	}
	
	public void setInfo(Method method,
			Class clazz,
			int access,
			String name,
			String descriptor,
			String signature,
			String[] exceptions) {
		this.method = method;
		this.clazz = clazz;
		this.parameters = new ArrayList<>(Arrays.asList(method.getParameters()));
		this.access = access;
		this.accessType = getAccessType(access);
		this.name = name;
		this.descriptor = descriptor;
		this.signature = signature;
		this.exceptions = exceptions;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String[] getExceptions() {
		return exceptions;
	}

	public void setExceptions(String[] exceptions) {
		this.exceptions = exceptions;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public List<LocalVariableInfoUtils> getLocalVariables() {
		return localVariables;
	}

	public void addLocalVariable(String name,
			String descriptor,
			String signature,
			Label start,
			Label end,
			int index) {
		LocalVariableInfoUtils lviu = new LocalVariableInfoUtils(name, descriptor, signature, start, end, index);
		if(!localVariables.contains(lviu))
			localVariables.add(lviu);
	}
	
	@Override
	public void printInfo() {
		System.out.println("Method: " + name + " (Class: " + clazz.getName() + ")");
		System.out.println("(1) Access: " + accessType);
		System.out.println("(2) Descriptor:  " + descriptor);
		System.out.println("(3) Signature: " + signature);
		if(exceptions != null)
			System.out.println("(4) Number of exceptions: " + exceptions.length);
		if(parameters != null)
			System.out.println("(5) Number of parameters: " + parameters.size());
		if(localVariables != null) {
			System.out.println("(6) Number of local variables: " + localVariables.size());
			for(LocalVariableInfoUtils lviu : localVariables)
				lviu.printInfo();
		}
	}

	@Override
	public void reset() {
		
	}
}