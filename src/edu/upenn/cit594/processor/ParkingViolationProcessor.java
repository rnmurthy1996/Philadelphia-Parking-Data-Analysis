package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.datamanagement.ParkingViolationReader;

/*
 * Aggregates and summarizes parking violations 
 */

public class ParkingViolationProcessor {
	
	private List<ParkingViolation> violations;
	
	public ParkingViolationProcessor(ParkingViolationReader pvReader) {
		violations = pvReader.getAllParkingViolations();
	}
	
	public Map<String, Integer> getTotalFinesByZipCode() {
		Map<String, Integer> totalFinesByZipCode = new TreeMap<>();
		
		for (ParkingViolation pv : violations) {
			String zipCode = pv.getZipCode();
			
			if (zipCode == null || !pv.getState().equals("PA")) {
				continue;
			}
			
			if (totalFinesByZipCode.containsKey(zipCode)) {
				totalFinesByZipCode.merge(zipCode, pv.getFine(), Integer::sum);
			} else {
				totalFinesByZipCode.put(zipCode, pv.getFine());
			}
		}
		
		return totalFinesByZipCode;
	}
	
	public int getTotalViolations(String zipCode) {
		int violationCount = 0;
		
		for (ParkingViolation pv : violations) {
			if (pv.getZipCode().equals(zipCode)) {
				violationCount++;
			}
		}
		
		return violationCount;
	}
}

