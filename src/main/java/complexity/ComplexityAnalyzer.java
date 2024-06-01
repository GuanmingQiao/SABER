package complexity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Main;

public class ComplexityAnalyzer {

	private List<String> methodKeys;
	private Map<String, Integer> methodKeyToComplexityMap = new HashMap<>();
	
	public ComplexityAnalyzer(List<String> methodKeys) {
		this.methodKeys = methodKeys;
	}
	
	public void analyzeMethods() {
		for(String methodKey : methodKeys) {
			System.out.println("Source path: " + Main.projectSourcePath);
			System.out.println("Class: " + methodKey.split("-")[0]);
			System.out.println("Method signature: " + methodKey.split("-")[1]);
			MethodComplexity mc = new MethodComplexity(
					Main.projectSourcePath,
					methodKey.split("-")[0],
					methodKey.split("-")[1]);
			int complexity = mc.getComplexity();
			methodKeyToComplexityMap.put(methodKey, complexity);
			System.out.println("New complexity entity");
			System.out.println("\tMethod key: " + methodKey);
			System.out.println("\tComplexity: " + complexity);
		}
	}
	
	public Map<String, Integer> getMethodKeyToComplexityMap() {
		return methodKeyToComplexityMap;
	}
}