package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

public class ExecuteZipMetric {
	
	public double executeStrategy(String zipCode, List<Property> properties, AverageZipMetric strategy){
		
		int zipCount = 0;
		double total = 0;
		
		for (Property property : properties) {
			if(property.getZipCode().equals(zipCode) && strategy.getValue(property) != -1) {
				zipCount++;
				total += strategy.getValue(property);
			}
		}
		
		return zipCount == 0 ? 0 : total / zipCount;
	}
}