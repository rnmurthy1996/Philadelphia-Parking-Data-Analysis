package edu.upenn.cit594.data;

/*
 * PopulationCenter class that defines the variables for a population center.
 */

public class PopulationCenter {
	private final String zipCode;
	private final int population;
	
	public PopulationCenter(String zipCode, int population) {
		this.zipCode = zipCode;
		this.population = population;
	}

	public String getZipCode() {
		return zipCode;
	}

	public int getPopulation() {
		return population;
	}
	
}
