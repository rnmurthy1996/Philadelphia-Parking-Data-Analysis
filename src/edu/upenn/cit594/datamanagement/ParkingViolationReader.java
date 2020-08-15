package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;

/*
 * ParkingViolationReader interface that defines getAllParkingViolations method 
 * and is implemented by CsvParkingViolationReader.java and JsonParkingViolationReader.java
 */

public interface ParkingViolationReader {
	List<ParkingViolation> getAllParkingViolations();
}
