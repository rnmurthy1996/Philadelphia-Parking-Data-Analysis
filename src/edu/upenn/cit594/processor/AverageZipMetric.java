package edu.upenn.cit594.processor;

import java.util.HashMap;

public interface AverageZipMetric {
	public double getZipMetric(int zipCode, HashMap<Integer, Double> cache);
}
