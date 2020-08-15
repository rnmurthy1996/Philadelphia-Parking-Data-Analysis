package edu.upenn.cit594.data;

/*
 * Property class that defines the variables for a property.
 */

public class Property {
	private final int totalLivableArea;
	private final int marketValue;
	private final int categoryCode;
	private final String zipCode;
	
	public Property(int totalLivableArea, int marketValue, int categoryCode, String zipCode) {
		this.totalLivableArea = totalLivableArea;
		this.marketValue = marketValue;
		this.categoryCode = categoryCode;
		this.zipCode = zipCode;
	}

	public int getTotalLivableArea() {
		return totalLivableArea;
	}

	public int getMarketValue() {
		return marketValue;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public String getZipCode() {
		return zipCode;
	}
	
}
