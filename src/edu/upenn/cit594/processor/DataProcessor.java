package edu.upenn.cit594.processor;

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
	
	public int calculateAverageMarketValue() {
		return -1;
	}
	
	public int calculateAverageTotalLiveableArea() {
		return -1;
	}
	
	public int calculateTotalResidentialMarketValuePerCapita() {
		return -1;
	}
}
