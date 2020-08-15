package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.PopulationCenter;
import edu.upenn.cit594.datamanagement.PopulationReader;

public class PopulationProcessor {
	
	private List<PopulationCenter> populationCenters;
	
	public PopulationProcessor(PopulationReader popReader) {
		populationCenters = popReader.getAllPopulations();
	}
	
	public int sumTotalPopulation() {
		int totalPopulation = 0;
		for (PopulationCenter pc : populationCenters) {
			totalPopulation += pc.getPopulation();
		}
		return totalPopulation;
	}

	public List<PopulationCenter> getPopulationCenters() {
		return populationCenters;
	}
	
	public int getPopulation(String zipCode) {
		for (PopulationCenter p : populationCenters) {
			if (String.valueOf(p.getZipCode()).equals(zipCode)) {
				return p.getPopulation();
			}
		}
		return 0;
	}
}
