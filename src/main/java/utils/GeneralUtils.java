package utils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public abstract class GeneralUtils {
	
	public abstract void printInfo();
	
	public abstract void reset();
	
	public String getAccessType(int access) {
		List<String> modifiers = new ArrayList<String>();
		
		if(Modifier.isPublic(access))
			modifiers.add("public");
		if(Modifier.isPrivate(access))
			modifiers.add("private");
		if(Modifier.isProtected(access))
			modifiers.add("protected");
		if(Modifier.isStatic(access))
			modifiers.add("static");
		if(Modifier.isAbstract(access))
			modifiers.add("abstract");
		if(Modifier.isInterface(access))
			modifiers.add("interface");
		if(Modifier.isFinal(access))
			modifiers.add("final");
		if(Modifier.isNative(access))
			modifiers.add("native");
		if(Modifier.isVolatile(access))
			modifiers.add("volatile");
		if(Modifier.isTransient(access))
			modifiers.add("transient");
		if(Modifier.isSynchronized(access))
			modifiers.add("synchronized");
		if(Modifier.isStrict(access))
			modifiers.add("strict");
		
		return String.join(":", modifiers);
	}
}