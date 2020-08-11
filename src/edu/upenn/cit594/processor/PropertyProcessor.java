package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PropertyReader;

public class PropertyProcessor {

	private List<Property> properties;
	
	public PropertyProcessor(PropertyReader propReader) {
		properties = propReader.getAllProperties();
	}

}
