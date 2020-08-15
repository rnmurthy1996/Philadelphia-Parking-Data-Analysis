package edu.upenn.cit594.processor;

import java.util.HashMap;

/*
 * AverageZipMetric interface that defines getZipMetric method 
 * and is implemented by AverageMarketValue.java and AverageToalArea.java
 */

public interface AverageZipMetric {
	public double getZipMetric(String zipCode, HashMap<String, Double> cache);
}