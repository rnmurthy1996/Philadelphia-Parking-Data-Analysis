package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

/*
 * AverageMarketValue class that calculates the average total livable area for a given zip code.
 */

public class AverageTotalArea implements AverageZipMetric {

	public int getValue(Property p) {
		return p.getTotalLivableArea();
	}
	
}
