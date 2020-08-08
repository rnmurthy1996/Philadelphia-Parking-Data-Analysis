package edu.upenn.cit594.data;

public class PopulationCenter {
	private final int zipCode;
	private final int population;
	
	public PopulationCenter(int zipCode, int population) {
		this.zipCode = zipCode;
		this.population = population;
	}

	public int getZipCode() {
		return zipCode;
	}

	public int getPopulation() {
		return population;
	}
	
}
