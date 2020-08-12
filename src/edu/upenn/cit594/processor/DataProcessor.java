package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.Map;

public class DataProcessor {

	private ParkingViolationProcessor pvProcessor;
	private PropertyProcessor propProcessor;
	private PopulationProcessor popProcessor;
	
	public DataProcessor(ParkingViolationProcessor pvProcessor, PropertyProcessor propProcessor,
			PopulationProcessor popProcessor) {
		this.pvProcessor = pvProcessor;
		this.propProcessor = propProcessor;
		this.popProcessor = popProcessor;
	}

	public int calculateTotalPopulation() {
		return -1;
	}
	
	public Map<String, Double> calculateTotalFinesPerCapita() {
		return null;
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
