package utils;

import org.objectweb.asm.Label;

public class LocalVariableInfoUtils extends GeneralUtils {
	
	private String name;
    private String descriptor;
    private String signature;
    private Label start;
    private Label end;
    private int index;
	
    public LocalVariableInfoUtils(String name,
    		String descriptor,
    		String signature,
    		Label start,
    		Label end,
    		int index) {
    	setInfo(name, descriptor, signature, start, end, index);
    }
    
    public void setInfo(String name,
    		String descriptor,
    		String signature,
    		Label start,
    		Label end,
    		int index) {
    	this.name = name;
    	this.descriptor = descriptor;
    	this.signature = signature;
    	this.start = start;
    	this.end = end;
    	this.index = index;
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

	public Label getStart() {
		return start;
	}

	public void setStart(Label start) {
		this.start = start;
	}

	public Label getEnd() {
		return end;
	}

	public void setEnd(Label end) {
		this.end = end;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public void printInfo() {
		System.out.println("Local variable: " + name);
		System.out.println("(1) Descriptor:  " + descriptor);
		System.out.println("(2) Signature: " + signature);
		if(start != null)
			System.out.println("(4) Start: " + start.toString());
		if(end != null)
			System.out.println("(5) End: " + end.toString());
		System.out.println("(6) Index: " + index);
	}

	@Override
	public void reset() {
		
	}
}