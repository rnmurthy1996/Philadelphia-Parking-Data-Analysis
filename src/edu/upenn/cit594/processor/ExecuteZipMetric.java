package edu.upenn.cit594.processor;

import java.util.HashMap;

public class ExecuteZipMetric {
	
	private AverageZipMetric azm;
	
	public ExecuteZipMetric(AverageZipMetric azm){
	this.azm = azm;
	}
	
	public double executeStrategy(int zipCode, HashMap<Integer, Double> averageCache){
		return azm.getZipMetric(zipCode, averageCache);
	}
}