package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.PopulationCenter;
import edu.upenn.cit594.data.Property;

public class PropertyReader {

	protected String fileName;
	
	public PropertyReader(String fileName) {
		this.fileName = fileName;
	}

	public List<Property> getAllProperties() {
		List<Property> propertyList = new ArrayList<Property>();
		
		String csvFile = fileName;
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

        	int tlaIndex = -1;
        	int mvIndex = -1;
        	int bcIndex = -1;
        	int zcIndex = -1;
        	
            String headerLine = br.readLine();
            String[] headerVals = headerLine.split(",");
            for(int i = 0; i < headerVals.length; i++) {
            	if(headerVals[i].contentEquals("total_livable_area")) {
            		tlaIndex = i;
            	}
            	if(headerVals[i].contentEquals("market_value")) {
            		mvIndex = i;
            	}
            	if(headerVals[i].contentEquals("building_code")) {
            		bcIndex = i;
            	}
            	if(headerVals[i].contentEquals("zip_code")) {
            		zcIndex = i;
            	}
            }
            
        	while((line = br.readLine()) != null) {

                // use comma as separator
                String[] property = line.split(",");
                ArrayList<String> rowVals = parser(property);
                
                String tla = rowVals.get(tlaIndex);
                int totalLivableArea = strRead(tla);
                
                String mv = rowVals.get(mvIndex);
                int marketValue = strRead(mv);
                
                String buildingCode = rowVals.get(bcIndex);
                
                String zc = rowVals.get(zcIndex);
                int zipCode = zipRead(zc);
                
                Property p = new Property(totalLivableArea, marketValue, buildingCode, zipCode);
                propertyList.add(p);
        	}
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return propertyList;
	}
	
	public ArrayList<String> parser(String[] strArr) {
		ArrayList<String> rowVals = new ArrayList<String>();
        for(int i = 0; i < strArr.length; i++) {
        	rowVals.add(strArr[i]);
        }
        
        ArrayList<String> rowValsFixed = new ArrayList<String>();
        boolean openString = false;
        for(int i = 0; i < rowVals.size(); i++) {
        	String token = "";
        	if(rowVals.get(i).length() > 0 && rowVals.get(i).charAt(0) == '"' 
        			&& rowVals.get(i).charAt(rowVals.get(i).length() - 1) != '"') {
        		openString = true;
        		token += rowVals.get(i);
        		while(openString && i < rowVals.size()) {
        			i++;
        			token += rowVals.get(i);
        			if(token.charAt(token.length() - 1) == '"') {
        				openString = false;
        			}
        		}
        		rowValsFixed.add(token);
        	}
        	else {
        		rowValsFixed.add(rowVals.get(i));
        	}
        }
        
        return rowValsFixed;
        
	}
	
	public int strRead(String s) {
        boolean num = true;
        for(int i = 0; i < s.length(); i++) {
        	if(!Character.isDigit(s.charAt(i))) {
        		num = false;
        	}
        }
        int val;
        if(num && s.length() > 0) {
        	val = Integer.parseInt(s);
        }
        else {
        	val = -1;
        }
        return val;
	}
	
	public int zipRead(String zc) {
		boolean num = true;
        for(int i = 0; i < zc.length(); i++) {
        	if(!Character.isDigit(zc.charAt(i))) {
        		num = false;
        	}
        }
        int zipCode;
        if(zc.length() == 0 || !num) {
        	zipCode = -1;
        }
        else if(zc.length() < 5) {
        	int l = zc.length();
        	zipCode = Integer.parseInt(zc.substring(0,l));
        }
        else {
        	zipCode = Integer.parseInt(zc.substring(0,5));
        } 
        return zipCode;
	}
}
