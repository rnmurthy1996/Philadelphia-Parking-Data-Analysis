package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.PopulationCenter;
import edu.upenn.cit594.datamanagement.PopulationReader;

public class PopulationProcessor {
	
	private List<PopulationCenter> populationCenters;
	
	public PopulationProcessor(PopulationReader popReader) {
		populationCenters = popReader.getAllPopulations();
	}

}
