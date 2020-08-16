package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PropertyReader;

public class PropertyProcessor {

	private List<Property> properties;
	
	public PropertyProcessor(PropertyReader propReader) {
		properties = propReader.getAllProperties();
	}
		
	public double average(String zipCode, AverageZipMetric strategy) {
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
	
	public double averageMarketValue(String zipCode) {
		return average(zipCode, new AverageMarketValue());		
	}
	
	public double averageTotalArea(String zipCode) {
		return average(zipCode, new AverageTotalArea());
	}
	
	public double getTotalMarketValue(String zipCode) {
		
		double totalMarketValue = 0;
		for(int i = 0; i < properties.size(); i++) {
			if(properties.get(i).getZipCode().contentEquals(zipCode) && properties.get(i).getTotalLivableArea() != -1) {
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

