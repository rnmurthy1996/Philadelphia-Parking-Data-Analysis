package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

/*
 * AverageMarketValue class that calculates the average market value for a given zip code.
 */

public class AverageMarketValue implements AverageZipMetric {
	
	public int getValue(Property p) {
		return p.getMarketValue();
	}
}
