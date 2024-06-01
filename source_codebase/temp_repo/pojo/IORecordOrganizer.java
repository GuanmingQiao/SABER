package pojo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class IORecordOrganizer implements Serializable {
	
	public String methodKey;
	public List<IORecord> subjectIORecords = new ArrayList<>();
	
	// Alpha IO records: important IO records
	public Map<String, Map<IORecord, List<IORecord>>> alphaIORecordMap = new HashMap<>();

	// Beta IO records: less important IO records
	public Map<String, Map<IORecord, List<IORecord>>> betaIORecordMap = new HashMap<>();
	
	public void addSubjectIORecord(IORecord subjectIORecord) {
		subjectIORecords.add(subjectIORecord);
	}
	
	public void addAlphaIORecords(IORecord subjectIORecord,
								  List<IORecord> alphaIORecords,
								  String otherMethodKey) {
		if(!alphaIORecordMap.containsKey(otherMethodKey))
			alphaIORecordMap.put(otherMethodKey, new HashMap<>());
		if(!alphaIORecordMap.get(otherMethodKey).containsKey(subjectIORecord))
			alphaIORecordMap.get(otherMethodKey).put(subjectIORecord, new ArrayList<>());			
		alphaIORecordMap.get(otherMethodKey).get(subjectIORecord).addAll(alphaIORecords);
	}

	public void addBetaIORecords(IORecord otherIORecord,
			  List<IORecord> betaIORecords,
			  String otherMethodKey) {
		if(!betaIORecordMap.containsKey(otherMethodKey))
			betaIORecordMap.put(otherMethodKey, new HashMap<>());
		if(!betaIORecordMap.get(otherMethodKey).containsKey(otherIORecord))
			betaIORecordMap.get(otherMethodKey).put(otherIORecord, new ArrayList<>());			
		betaIORecordMap.get(otherMethodKey).get(otherIORecord).addAll(betaIORecords);
	}
	
	public String printOrganizer() {
		String organizerString = "\t\tSubject records:\n";
		try {
			for(IORecord ioRecord : subjectIORecords) {
				organizerString += "\t\t\tIO record:\n";
				organizerString += "\t\t\t\tMethod key:" + ioRecord.methodKey + "\n";
				organizerString += "\t\t\t\tStamp:" + ioRecord.stamp + "\n";
			}
		} catch(Exception e) {}
		try {
			organizerString += "\t\tAlpha records:\n";
			for(String alphaRecordKey : alphaIORecordMap.keySet()) {
				organizerString += "\tAlpha record method key: " + alphaRecordKey + "\n";
				Map<IORecord, List<IORecord>> layer2AIORMap = alphaIORecordMap.get(alphaRecordKey);
				organizerString += "\t\t\t\tNumber of alphas: " + layer2AIORMap.size() + "\n";
				organizerString += "\t\t\t\tAlpha type: " + new ArrayList<>(layer2AIORMap.keySet()).get(0).methodKey + "\n";
				organizerString += "\t\t\t\tAlpha stamp: " + new ArrayList<>(layer2AIORMap.keySet()).get(0).stamp + "\n";			
				organizerString += "\t\t\t\tMapped alpha type: " + layer2AIORMap.get(new ArrayList<>(layer2AIORMap.keySet()).get(0)).get(0).methodKey + "\n";			
				organizerString += "\t\t\t\tMapped alpha stamp: " + layer2AIORMap.get(new ArrayList<>(layer2AIORMap.keySet()).get(0)).get(0).stamp + "\n";			
		}
		} catch(Exception e) {}
		try {
			organizerString += "\t\tBeta records:\n";
			for(String betaRecordKey : betaIORecordMap.keySet()) {
				organizerString += "\tBeta record method key: " + betaRecordKey + "\n";
				Map<IORecord, List<IORecord>> layer2BIORMap = betaIORecordMap.get(betaRecordKey);
				organizerString += "\t\t\t\tNumber of betas: " + layer2BIORMap.size() + "\n";
				organizerString += "\t\t\t\tBeta type: " + new ArrayList<>(layer2BIORMap.keySet()).get(0).methodKey + "\n";
				organizerString += "\t\t\t\tBeta stamp: " + new ArrayList<>(layer2BIORMap.keySet()).get(0).stamp + "\n";
				organizerString += "\t\t\t\tMapped beta type: " + layer2BIORMap.get(new ArrayList<>(layer2BIORMap.keySet()).get(0)).get(0).methodKey + "\n";
				organizerString += "\t\t\t\tMapped beta stamp: " + layer2BIORMap.get(new ArrayList<>(layer2BIORMap.keySet()).get(0)).get(0).stamp + "\n";			
			}
		} catch(Exception e) {}
		return organizerString;
	}
	
	public static void main(String[] args) {
		Method[] methods = IORecordOrganizer.class.getDeclaredMethods();
		for(Method m : methods) {
			Field gSig;
			String sig;
			try {
				gSig = Method.class.getDeclaredField("signature");
				gSig.setAccessible(true);
				sig = (String) gSig.get(m);
				System.out.println("Method: " + m.getName() + ", signature: " + sig);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}