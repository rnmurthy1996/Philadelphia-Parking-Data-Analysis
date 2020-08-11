package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.datamanagement.ParkingViolationReader;

public class ParkingViolationProcessor {
	
	private List<ParkingViolation> violations;
	
	public ParkingViolationProcessor(ParkingViolationReader pvReader) {
		violations = pvReader.getAllParkingViolations();
	}
	
}
