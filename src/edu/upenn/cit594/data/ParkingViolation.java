package edu.upenn.cit594.data;

/*
 * ParkingViolation class that defines the variables for a parking violation.
 */

public class ParkingViolation {
	private final String timestamp;
	private final int fine;
	private final String violation;
	private final String vehicleIdentifier;
	private final String state;
	private final int violationIdentifier;
	private final String zipCode;
	
	public ParkingViolation(String timestamp, int fine, String violation, 
			String vehicleIdentifier, String state, int violationIdentifier, String zipCode) {
		this.timestamp = timestamp;
		this.fine = fine;
		this.violation = violation;
		this.vehicleIdentifier = vehicleIdentifier;
		this.state = state;
		this.violationIdentifier = violationIdentifier;
		this.zipCode = zipCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public int getFine() {
		return fine;
	}

	public String getViolation() {
		return violation;
	}

	public String getVehicleIdentifier() {
		return vehicleIdentifier;
	}

	public String getState() {
		return state;
	}

	public int getViolationIdentifier() {
		return violationIdentifier;
	}

	public String getZipCode() {
		return zipCode;
	}
	
	
}
