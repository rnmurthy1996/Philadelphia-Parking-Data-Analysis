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
			if(properties.get(i).getZipCode().contentEquals(String.valueOf(zipCode)) && properties.get(i).getTotalLivableArea() != -1) {
				totalMarketValue += properties.get(i).getTotalLivableArea();
			}
		}
		return totalMarketValue;
	}

	public double getCommercialPercentage(String zipCode) {
		int commercialCount = 0;
		int totalCount = 0;
		
		for (Property p : properties) {
			if (!p.getZipCode().equals(zipCode)) {
				continue;
			}
			if (p.getCategoryCode() != -1) {
				totalCount++;
			}
			if (p.getCategoryCode() == 4) {
				commercialCount++;
			}
		}
		
		if (totalCount == 0) { // prevent divide by 0 issues
			return 0;
		}
		
		return (double)commercialCount / totalCount;
	}
}

