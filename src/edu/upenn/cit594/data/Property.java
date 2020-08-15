package edu.upenn.cit594.data;

/*
 * Property class that defines the variables for a property.
 */

public class Property {
	private final int totalLivableArea;
	private final int marketValue;
	private final String buildingCode;
	private final String zipCode;
	
	public Property(int totalLivableArea, int marketValue, String buildingCode, String zipCode) {
		this.totalLivableArea = totalLivableArea;
		this.marketValue = marketValue;
		this.buildingCode = buildingCode;
		this.zipCode = zipCode;
	}

	public int getTotalLivableArea() {
		return totalLivableArea;
	}

	public int getMarketValue() {
		return marketValue;
	}

	public String getBuildingCode() {
		return buildingCode;
	}

	public String getZipCode() {
		return zipCode;
	}
	
}
