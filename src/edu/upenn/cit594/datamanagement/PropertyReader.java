package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.PopulationCenter;
import edu.upenn.cit594.data.Property;

/*
 * PropertyReader class that reads in all properties from properties.csv and adds them to a list.
 */

public class PropertyReader {

	protected String fileName;
	
	public PropertyReader(String fileName) {
		this.fileName = fileName;
	}

	public List<Property> getAllProperties() {
		List<Property> propertyList = new ArrayList<Property>();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

        	int tlaIndex = -1;
        	int mvIndex = -1;
        	int ccIndex = -1;
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
            	if(headerVals[i].contentEquals("category_code")) {
            		ccIndex = i;
            	}
            	if(headerVals[i].contentEquals("zip_code")) {
            		zcIndex = i;
            	}
            }
            
        	while((line = br.readLine()) != null) {
        		List<String> rowVals = Arrays.asList(line.split(","));
        		
        		if (rowVals.size() != 77) { // easy method broke down so
        			rowVals = parser(line); // parse by individual char
        		}
                
                String tla = rowVals.get(tlaIndex);
                int totalLivableArea = strRead(tla);
                
                String mv = rowVals.get(mvIndex);
                int marketValue = strRead(mv);
                
                String categoryCodeStr = rowVals.get(ccIndex);
                int categoryCode = categoryCodeStr.length() == 0 ? -1 : Integer.parseInt(categoryCodeStr);
                
                String zc = rowVals.get(zcIndex);
                String zipCode = zipRead(zc);
                
                Property p = new Property(totalLivableArea, marketValue, categoryCode, zipCode);
                propertyList.add(p);
        	}
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return propertyList;
	}
	

	public ArrayList<String> parser(String line) {
		ArrayList<String> tokens = new ArrayList<>();
        String token = "";
        boolean insideQuotes = false;

        for (int i = 0; i < line.length() - 1; i++) {
            char currentChar = line.charAt(i);
            char nextChar = line.charAt(i + 1);

            if (currentChar == '"' && insideQuotes && nextChar == ',') {
                token += currentChar;
                tokens.add(token);
                i++; // skip next comma so we don't add another "" token
                insideQuotes = false;
                token = "";
            } else if (currentChar == '"') {
            	if (insideQuotes) {
            		insideQuotes = false;
            	} else {
            		insideQuotes = true;
            	}
                token += currentChar;
            } else if (insideQuotes && currentChar == ',') { // treat like any other char instead of splitting tokens
                token += currentChar;
            } else if (currentChar == ',') {
                tokens.add(token);
                token = "";
            } else {
                token += currentChar;
            }
        }

        token += line.charAt(line.length() - 1); // consume last char in input string
        tokens.add(token);
             
        return tokens;
	}
	
	//Method used to read in market value or total livable area from csv file and convert it to format we want.
	public int strRead(String s) {
        boolean num = true;
        for(int i = 0; i < s.length(); i++) {
        	if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != '.') {
        		num = false;
        	}
        }
        int val;
        if(num && s.length() > 0) {
        	val = (int)Double.parseDouble(s);
        }
        else {
        	val = -1;
        }
        return val;
	}
	
	//Method used to read in zip code from csv file and convert it to format we want.
	public String zipRead(String zc) {
		boolean num = true;
		String zipCode;
		if(zc.length() < 5) {
			zipCode = "-1";
		}
		else {
			for(int i = 0; i < 5; i++) {
	        	if(!Character.isDigit(zc.charAt(i))) {
	        		num = false;
	        	}
	        }
			if(num)
				zipCode = zc.substring(0,5);
			else
				zipCode = "-1";
		}
      
        return zipCode;
	}
}
