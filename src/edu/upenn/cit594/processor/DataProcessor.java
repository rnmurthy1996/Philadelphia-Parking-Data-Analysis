package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.data.PopulationCenter;

/*
 *  Aggregates and operates on data from all 3 sources: parking violations, properties, and population.
 *  Also caches prior calculations to avoid recomputing.
 */

public class DataProcessor {

	private ParkingViolationProcessor pvProcessor;
	private PropertyProcessor propProcessor;
	private PopulationProcessor popProcessor;
	
	// caches
	private int totalPopulation = -1;
	private Map<String, Double> finesPerCapitaCache;
	private Map<String, Double> averageMarketValueCache = new HashMap<String, Double>();
	private Map<String, Double> averageTotalAreaCache = new HashMap<String, Double>();
	private Map<String, Double> mvPerCapitaCache = new HashMap<String, Double>();
	private Map<String, Double> violationsPerCapitaCache = new HashMap<String, Double>();
	private Map<String, Double> commercialPercentageCache = new HashMap<String, Double>();
	
	
	public DataProcessor(ParkingViolationProcessor pvProcessor, PropertyProcessor propProcessor,
			PopulationProcessor popProcessor) {
		this.pvProcessor = pvProcessor;
		this.propProcessor = propProcessor;
		this.popProcessor = popProcessor;
	}

	public int calculateTotalPopulation() {
		if (totalPopulation < 0) {
			totalPopulation = popProcessor.sumTotalPopulation();
		}
		return totalPopulation;
	}
	
	public Map<String, Double> calculateTotalFinesPerCapita() {
		if (finesPerCapitaCache != null) {
			return finesPerCapitaCache;
		}
		
		Map<String, Integer> totalFinesByZipCode = pvProcessor.getTotalFinesByZipCode();
		List<PopulationCenter> populationCenters = popProcessor.getPopulationCenters();
		
		Map<String, Double> totalFinesPerCapita = new TreeMap<>();
		
		for (PopulationCenter pc : populationCenters) {
			String zipCode = pc.getZipCode();
			if (totalFinesByZipCode.containsKey(zipCode)) {
				double finesPerCapita = (double)totalFinesByZipCode.get(zipCode) / pc.getPopulation();
				totalFinesPerCapita.put(zipCode, finesPerCapita);
			}
		}
		
		finesPerCapitaCache = totalFinesPerCapita;
		
		return totalFinesPerCapita;
	}
	
	public double calculateAverageMarketValue(String zipCode) {
		if(averageMarketValueCache.containsKey(zipCode)) {
			return averageMarketValueCache.get(zipCode);
		}
		
		double amv = propProcessor.averageMarketValue(zipCode);
		averageMarketValueCache.put(zipCode, amv);
		return amv;
	}
	
	public double calculateAverageTotalLiveableArea(String zipCode) {
		if(averageTotalAreaCache.containsKey(zipCode)) {
			return averageTotalAreaCache.get(zipCode);
		}
		
		double ata = propProcessor.averageTotalArea(zipCode); 
		averageTotalAreaCache.put(zipCode, ata);
		return ata;
	}
	
	public double calculateTotalResidentialMarketValuePerCapita(String zipCode) {
		
		if(mvPerCapitaCache.containsKey(zipCode)) {
			return mvPerCapitaCache.get(zipCode);
		}
		
		double totalMarketValue = propProcessor.getTotalMarketValue(zipCode);
		int totalPopulation = popProcessor.getPopulation(zipCode);
		
		double totalMarketValuePerCapita = totalMarketValue /totalPopulation;
		mvPerCapitaCache.put(zipCode, totalMarketValuePerCapita);
		
		return totalMarketValuePerCapita;
	}
	
	public String calculateViolationsPerCapitaAndCommercialPercentage(String zipCode) {
		
		double violationsPerCapita = 0;
		double commercialPercentage = 0;
		
		if (violationsPerCapitaCache.containsKey(zipCode)) {
			violationsPerCapita = violationsPerCapitaCache.get(zipCode);
		} else {
			int violations = pvProcessor.getTotalViolations(zipCode);
			int population = popProcessor.getPopulation(zipCode);
			if (population == 0) {  // prevent div by 0 errors
				violationsPerCapita = 0;
			} else {
				violationsPerCapita = (double)violations / population;
			}
			violationsPerCapitaCache.put(zipCode, violationsPerCapita);
		}
		
		if (commercialPercentageCache.containsKey(zipCode)) {
			commercialPercentage = commercialPercentageCache.get(zipCode);
		} else {
			commercialPercentage = propProcessor.getCommercialPercentage(zipCode);
			commercialPercentageCache.put(zipCode, commercialPercentage);
		}
		
		return String.format("The violations per capita is %.04f and commercial percentage is %.02f%%", violationsPerCapita, commercialPercentage * 100);
	}
}
