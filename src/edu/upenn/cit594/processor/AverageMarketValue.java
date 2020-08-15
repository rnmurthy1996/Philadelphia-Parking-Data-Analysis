package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;

import edu.upenn.cit594.data.PopulationCenter;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;

/*
 * AverageMarketValue class that calculates the average market value for a given zip code.
 */

public class AverageMarketValue implements AverageZipMetric {
	
	private List<Property> properties;
	
	public AverageMarketValue(List<Property> properties) {
		this.properties = properties;
	}
	
	public double getZipMetric(String zipCode, HashMap<String, Double> averageMarketValueCache) {
			
			int zipCount = 0;
			double totalMarketValue = 0;
			for(int i = 0; i < properties.size(); i++) {
				if(properties.get(i).getZipCode().contentEquals(zipCode) && properties.get(i).getMarketValue() != -1) {
					zipCount++;
					totalMarketValue += properties.get(i).getMarketValue();
				}
			}
			double averageMarketValue = totalMarketValue/zipCount;
			averageMarketValueCache.put(zipCode, averageMarketValue);
			return averageMarketValue;
	}
}
