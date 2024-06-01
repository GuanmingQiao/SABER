package sim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.IORecord;
import pojo.IORecordOrganizer;

public class SimilarityAnalyzer {
	
	private Map<String, IORecordOrganizer> ioRecordOrganizersMap = new HashMap<>();
	private Map<String, List<Double>> alphaIORecordSimilaritiesMap = new HashMap<>();
	private Map<String, List<Double>> betaIORecordSimilaritiesMap = new HashMap<>();
	private Map<String, Double> methodSimilarities = new HashMap<>();
	
	private double ioSim, metapropSim, extraceSim;
	
	public static final double DEFAULT_IO_SIM_THRESHOLD = 0.75;
	public static final double DEFAULT_META_PROP_SIM_THRESHOLD = 0.75;
	public static final double DEFAULT_EX_TRACE_SIM_THRESHOLD = 0.75;
	public static final double DEFAULT_ALPHA_RECORD_WEIGHT_FACTOR = 2.0;
	public static final double DEFAULT_BETA_RECORD_WEIGHT_FACTOR = 1.0;
	public static final String DELIMITER = "_COLUMBIA_SABER_";
	
	public SimilarityAnalyzer(Map<String, IORecordOrganizer> ioRecordOrganizersMap) {
		this.ioRecordOrganizersMap = ioRecordOrganizersMap;
	}
	
	public void analyze() {
		for(String subjectMethodKey : ioRecordOrganizersMap.keySet()) {
			IORecordOrganizer ioRecordOrganizer = ioRecordOrganizersMap.get(subjectMethodKey);
			
			// Populate the alpha IO record similarities map
			populateAlphaIORecordSimilaritiesMap(subjectMethodKey, ioRecordOrganizer);

			// Populate the beta IO record similarities map
			populateBetaIORecordSimilaritiesMap(subjectMethodKey, ioRecordOrganizer);
			
			// Compute the method similarities with default weight factors
			computeMethodSimilarities(DEFAULT_ALPHA_RECORD_WEIGHT_FACTOR, DEFAULT_BETA_RECORD_WEIGHT_FACTOR);
		}		
	}
	
	private void populateAlphaIORecordSimilaritiesMap(String subjectMethodKey, IORecordOrganizer ioRecordOrganizer) {
		Map<String, Map<IORecord, List<IORecord>>> alphaIORecordMap = ioRecordOrganizer.alphaIORecordMap;
		populateIORecordSimilaritiesMap(subjectMethodKey, alphaIORecordMap, alphaIORecordSimilaritiesMap);
	}
	
	private void populateBetaIORecordSimilaritiesMap(String subjectMethodKey, IORecordOrganizer ioRecordOrganizer) {
		Map<String, Map<IORecord, List<IORecord>>> betaIORecordMap = ioRecordOrganizer.betaIORecordMap;
		populateIORecordSimilaritiesMap(subjectMethodKey, betaIORecordMap, betaIORecordSimilaritiesMap);
	}
	
	private void populateIORecordSimilaritiesMap(String subjectMethodKey,
												 Map<String, Map<IORecord, List<IORecord>>> ioRecordMap,
												 Map<String, List<Double>> ioRecordSimilaritiesMap) {
		for(String otherMethodKey : ioRecordMap.keySet()) {
			Map<IORecord, List<IORecord>> nestedIORecordMap = ioRecordMap.get(otherMethodKey);
			List<Double> ioRecordSimilaritiesPerMethod = new ArrayList<>();
			for(IORecord keyIORecord : nestedIORecordMap.keySet()) {
				List<IORecord> permutedIORecords = nestedIORecordMap.get(keyIORecord);
				List<Double> permutedIORecordSimilarities = new ArrayList<>();
				for(IORecord permutedIORecord : permutedIORecords) {
					System.out.println();
					System.out.println("Key IO record method key: " + keyIORecord.methodKey);
					System.out.println("Key IO record inputs: " + keyIORecord.sortedInputs);
					System.out.println("Key IO record outputs: " + keyIORecord.sortedOutputs);
					System.out.println("Key IO record stamp: " + keyIORecord.stamp);
					System.out.println("Permuted IO record method key: " + permutedIORecord.methodKey);
					System.out.println("Permuted IO record inputs: " + permutedIORecord.sortedInputs);
					System.out.println("Permuted IO record outputs: " + permutedIORecord.sortedOutputs);
					System.out.println("Permuted IO record stamp: " + permutedIORecord.stamp);
					double similarityValue = IOSimilarity.getOutputSimilarity(keyIORecord, permutedIORecord);
					System.out.println();
					permutedIORecordSimilarities.add(similarityValue);
				}
				double maxIORecordSimilarity = 0.0;
				if(!permutedIORecordSimilarities.isEmpty())
					maxIORecordSimilarity = Collections.max(permutedIORecordSimilarities);
				ioRecordSimilaritiesPerMethod.add(maxIORecordSimilarity);
			}
			String methodSimilarityKey = subjectMethodKey + DELIMITER + otherMethodKey;
			ioRecordSimilaritiesMap.put(methodSimilarityKey, ioRecordSimilaritiesPerMethod);
		}		
	}
	
	public void computeMethodSimilarities(double alphaRecordWeightFactor, double betaRecordWeightFactor) {
		for(String methodSimilarityKey : alphaIORecordSimilaritiesMap.keySet()) {
			List<Double> alphaSimilarities = alphaIORecordSimilaritiesMap.get(methodSimilarityKey);
			double weightedSimilarity;
			if(betaIORecordSimilaritiesMap.containsKey(methodSimilarityKey)) {
				List<Double> betaSimilarities = betaIORecordSimilaritiesMap.get(methodSimilarityKey);
				weightedSimilarity = getTotalWeightedSimilarity(alphaSimilarities,
																betaSimilarities,
																alphaRecordWeightFactor,
																betaRecordWeightFactor);
			}
			else
				weightedSimilarity = getTotalWeightedSimilarity(alphaSimilarities,
																new ArrayList<Double>(),
																alphaRecordWeightFactor,
																betaRecordWeightFactor);
			methodSimilarities.put(methodSimilarityKey, weightedSimilarity);
		}
	}
	
	public static double getTotalWeightedSimilarity(List<Double> alphaSimilarities,
													List<Double> betaSimilarities,
													double alphaRecordWeightFactor,
													double betaRecordWeightFactor) {
		double totalAlphaComparisons = alphaSimilarities.size();
		double totalBetaComparisons = betaSimilarities.size();
		double totalAlphaSimilarity = 0.0, totalBetaSimilarity = 0.0;
		for(double alphaSimilarity : alphaSimilarities)
			totalAlphaSimilarity += alphaSimilarity;
		for(double betaSimilarity : betaSimilarities)
			totalBetaSimilarity += betaSimilarity;
		double numerator = alphaRecordWeightFactor * totalAlphaSimilarity + 
				betaRecordWeightFactor * totalBetaSimilarity;
		double denominator = alphaRecordWeightFactor * totalAlphaComparisons + 
				betaRecordWeightFactor * totalBetaComparisons;

		if(numerator == 0.0)
			return 0.0;
		return numerator / denominator;
	}
	
	public Map<String, List<Double>> getAlphaIORecordSimilaritiesMap() {
		return alphaIORecordSimilaritiesMap;
	}

	public Map<String, List<Double>> getBetaIORecordSimilaritiesMap() {
		return betaIORecordSimilaritiesMap;
	}

	public Map<String, Double> getMethodSimilarities() {
		return methodSimilarities;
	}
}