package p5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyClass3 {

    private String firstName = "";
    private String lastName = "";
    private double decimal = 0.0;
    private List<String> strings = new ArrayList<>();
    private List<Integer> values = new ArrayList<>();

    public DummyClass3() {
    	
    }
    
    public DummyClass3(String firstName,
    		String lastName,
    		List<String> strings,
    		double decimal) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.strings = strings;
    	this.decimal = decimal;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public double getDecimal() {
		return decimal;
	}

	public void setDecimal(double decimal) {
		this.decimal = decimal;
	}
	
	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}
		
	public String returnNewString(int x, double y, String z) {
		String newString = z + firstName + lastName + Double.toString(decimal);
		strings.add(newString);
		return newString;
	}
	
	public List<Integer> selectionSort(){
		for (int i = 0; i < values.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < values.size(); j++)
				if (values.get(j) < values.get(index)) 
					index = j;
			int smallerNumber = values.get(index); 
			values.set(index, values.get(i));
			values.set(i, smallerNumber);
		}
		return values;
	}
}
