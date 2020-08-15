package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;

import edu.upenn.cit594.data.PopulationCenter;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;

/*
 * AverageMarketValue class that calculates the average total livable area for a given zip code.
 */

public class AverageTotalArea implements AverageZipMetric {
	
	private List<Property> properties;
	
	public AverageTotalArea(List<Property> properties) {
		this.properties = properties;
	}
	
	public double getZipMetric(int zipCode, HashMap<Integer, Double> averageTotalAreaCache) {
			
			int zipCount = 0;
			double totalArea = 0;
			for(int i = 0; i < properties.size(); i++) {
				if(properties.get(i).getZipCode().contentEquals(String.valueOf(zipCode)) && properties.get(i).getTotalLivableArea() != -1) {
					zipCount++;
					totalArea += properties.get(i).getTotalLivableArea();
				}
			}
			double averageTotalArea = totalArea/zipCount;
			averageTotalAreaCache.put(zipCode, averageTotalArea);
			return averageTotalArea;
	}
}
