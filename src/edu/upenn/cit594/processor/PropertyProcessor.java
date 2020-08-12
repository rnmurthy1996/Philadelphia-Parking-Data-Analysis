package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PropertyReader;

public class PropertyProcessor {

	private List<Property> properties;
	
	protected HashMap<Integer, Double> averageMarketValueCache = new HashMap<Integer, Double>();
	protected HashMap<Integer, Double> averageTotalAreaCache = new HashMap<Integer, Double>();
	
	public PropertyProcessor(PropertyReader propReader) {
		properties = propReader.getAllProperties();
	}
	
	public double averageMarketValue(int zipCode) {
		if(averageMarketValueCache.containsKey(zipCode)) {
			return averageMarketValueCache.get(zipCode);
		}
		int zipCount = 0;
		double totalMarketValue = 0;
		for(int i = 0; i < properties.size(); i++) {
			System.out.println(properties.get(i).getZipCode());
			System.out.println(properties.get(i).getMarketValue());
			if(properties.get(i).getZipCode() == zipCode && properties.get(i).getMarketValue() != -1) {
				zipCount++;
				totalMarketValue += properties.get(i).getMarketValue();
			}
		}
		double averageMarketValue = totalMarketValue/zipCount;
		averageMarketValueCache.put(zipCode, averageMarketValue);
		return averageMarketValue;
	}
	
	public double averageTotalArea(int zipCode) {
		if(averageTotalAreaCache.containsKey(zipCode)) {
			return averageTotalAreaCache.get(zipCode);
		}
		int zipCount = 0;
		double totalArea = 0;
		for(int i = 0; i < properties.size(); i++) {
			if(properties.get(i).getZipCode() == zipCode && properties.get(i).getTotalLivableArea() != -1) {
				zipCount++;
				totalArea += properties.get(i).getTotalLivableArea();
			}
		}
		double averageTotalArea = totalArea/zipCount;
		averageTotalAreaCache.put(zipCode, averageTotalArea);
		return averageTotalArea;
	}

}

