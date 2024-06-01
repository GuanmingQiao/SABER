package conversion;

import java.util.HashSet;
import java.util.Set;

public class Group {
	private Set<Object> inputConversions = new HashSet<>();
	
	public Group() {}
	
	public Group(Set<Object> inputConversions) {
		setConversions(inputConversions);
	}
	
	public Set<Object> getConversions() {
		return inputConversions;
	}
	
	public void setConversions(Set<Object> inputConversions) {
		this.inputConversions = inputConversions;
	}
	
	public void addConversion(Object inputConversion) {
		this.inputConversions.add(inputConversion);
	}
	
	public void removeConversion(Object inputConversion) {
		this.inputConversions.remove(inputConversion);
	}
	
	public int size() {
		return inputConversions.size();
	}
}