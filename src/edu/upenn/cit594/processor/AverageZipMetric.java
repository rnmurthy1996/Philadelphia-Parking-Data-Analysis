package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

/*
 * AverageZipMetric interface that defines getZipMetric method 
 * and is implemented by AverageMarketValue.java and AverageToalArea.java
 */

public interface AverageZipMetric {
	public int getValue(Property p);
}