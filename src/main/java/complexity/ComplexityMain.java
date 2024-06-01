package complexity;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

public class ComplexityMain {
	
	private static String classFile = 
			"/Users/adityasridhar/Documents/COLUMBIA/SABER/target/classes/complexity/DummyClass2.class";
	
	public static void main(String[] args) {
		try {
			new ClassComplexity(classFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}