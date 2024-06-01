package p5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.Serializable;

public class DummyClass2 implements Serializable {

    private String name = "";

    private String type = "";

    private int value = 0;

    private int numOperations = 0;

    private List<String> listOfItems = new ArrayList<>();

    private List<Integer> listOfNumbers = new ArrayList<>();

    private double probability = 0.0;

    private double distanceOfItems = 0.0;

    public DummyClass2() {
    }

    public DummyClass2(String name, String type, int value, int numOperations, List<String> listOfItems, double probability) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.numOperations = numOperations;
        this.listOfItems = listOfItems;
        this.probability = probability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNumOperations() {
        return numOperations;
    }

    public void setNumOperations(int numOperations) {
        this.numOperations = numOperations;
    }

    public List<String> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(List<String> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public List<Integer> getListOfNumbers() {
        return listOfNumbers;
    }

    public void setListOfNumbers(List<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double getDistanceOfItems() {
        double distanceOfItemsSquared = 0.0;
        for (String item : listOfItems) distanceOfItemsSquared += Math.pow(item.length(), 2);
        distanceOfItems = Math.sqrt(distanceOfItemsSquared);
        return distanceOfItems;
    }

    public int addToAndReturnUpdatedValue(int a, int b, String c) {
        value += a + c.length();
        return value;
    }

    public String getInterestingItem(String x, int y) {
        String interestingItem = Integer.toString(y) + name + type + Integer.toString(value) + Integer.toString(numOperations);
        return interestingItem;
    }

    public List<Integer> insertionSort() {
        for (int i = 1; i < listOfNumbers.size(); i++) {
            int valueToSort = listOfNumbers.get(i);
            int j;
            for (j = i; j > 0 && listOfNumbers.get(j - 1) > valueToSort; j--) {
                listOfNumbers.set(j, listOfNumbers.get(j - 1));
            }
            // We will put valueToSort at that index
            listOfNumbers.set(j, valueToSort);
        }
        return listOfNumbers;
    }
}
