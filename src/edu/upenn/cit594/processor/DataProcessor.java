package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.data.PopulationCenter;

public class DataProcessor {

	private ParkingViolationProcessor pvProcessor;
	private PropertyProcessor propProcessor;
	private PopulationProcessor popProcessor;
	
	// caches
	private int totalPopulation = -1;
	private Map<String, Double> finesPerCapitaCache;
	
	
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
			String zipCode = String.valueOf(pc.getZipCode());  // shouldn't zip codes be strings?
			if (totalFinesByZipCode.containsKey(zipCode)) {
				double finesPerCapita = (double)totalFinesByZipCode.get(zipCode) / pc.getPopulation();
				totalFinesPerCapita.put(zipCode, finesPerCapita);
			}
		}
		
		finesPerCapitaCache = totalFinesPerCapita;
		
		return totalFinesPerCapita;
	}
	
	public double calculateAverageMarketValue(String zipCode) {
		return propProcessor.averageMarketValue(Integer.parseInt(zipCode));
	}
	
	public double calculateAverageTotalLiveableArea(String zipCode) {
		return propProcessor.averageTotalArea(Integer.parseInt(zipCode));
	}
	
	public int calculateTotalResidentialMarketValuePerCapita() {
		return -1;
	}
}
