package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PropertyReader;

public class PropertyProcessor {

	private List<Property> properties;
	
	public PropertyProcessor(PropertyReader propReader) {
		properties = propReader.getAllProperties();
	}
	
	public double averageMarketValue(int zipCode, HashMap<Integer, Double> averageMarketValueCache) {
		
		ExecuteZipMetric execute = new ExecuteZipMetric(new AverageMarketValue(properties));
		return execute.executeStrategy(zipCode, averageMarketValueCache);
		
	}
	
	public double averageTotalArea(int zipCode, HashMap<Integer, Double> averageTotalAreaCache) {
		
		ExecuteZipMetric execute = new ExecuteZipMetric(new AverageTotalArea(properties));
		return execute.executeStrategy(zipCode, averageTotalAreaCache);
	}
	
	public double getTotalMarketValue(int zipCode) {
		
		double totalMarketValue = 0;
		for(int i = 0; i < properties.size(); i++) {
			if(properties.get(i).getZipCode() == zipCode && properties.get(i).getTotalLivableArea() != -1) {
				totalMarketValue += properties.get(i).getTotalLivableArea();
			}
		}
		return totalMarketValue;
	}

}

