package utils;

public class GlobalVariableInfoUtils extends GeneralUtils {
	
	private int access;
	private String accessType;
	private String name;
    private String descriptor;
    private String signature;
    private Object value;
	
    public GlobalVariableInfoUtils(int access,
			String name,
			String descriptor,
			String signature,
			Object value) {
    	setInfo(access, name, descriptor, signature, value);
    }
    
    public void setInfo(int access,
    		String name,
    		String descriptor,
    		String signature,
    		Object value) {
    	this.access = access;
    	this.accessType = getAccessType(access);
    	this.name = name;
    	this.descriptor = descriptor;
    	this.signature = signature;
    	this.value = value;
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
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public void printInfo() {
		System.out.println("Global variable: " + name);
		System.out.println("(1) Access: " + accessType);
		System.out.println("(2) Descriptor:  " + descriptor);
		System.out.println("(3) Signature: " + signature);
		if(value != null)
			System.out.println("(4) Value: " + value.toString());
	}

	@Override
	public void reset() {
		
	}
}