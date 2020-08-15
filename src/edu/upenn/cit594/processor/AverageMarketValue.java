package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;

import edu.upenn.cit594.data.PopulationCenter;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;

public class AverageMarketValue implements AverageZipMetric {
	
	private List<Property> properties;
	
	public AverageMarketValue(List<Property> properties) {
		this.properties = properties;
	}
	
	public double getZipMetric(int zipCode, HashMap<Integer, Double> averageMarketValueCache) {
			
			int zipCount = 0;
			double totalMarketValue = 0;
			for(int i = 0; i < properties.size(); i++) {
				if(properties.get(i).getZipCode().contentEquals(String.valueOf(zipCode)) && properties.get(i).getMarketValue() != -1) {
					zipCount++;
					totalMarketValue += properties.get(i).getMarketValue();
				}
			}
			double averageMarketValue = totalMarketValue/zipCount;
			averageMarketValueCache.put(zipCode, averageMarketValue);
			return averageMarketValue;
	}
}
