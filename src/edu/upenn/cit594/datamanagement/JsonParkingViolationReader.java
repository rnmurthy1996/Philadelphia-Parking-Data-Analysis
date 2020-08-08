package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.ParkingViolation;

public class JsonParkingViolationReader implements ParkingViolationReader {

	protected String fileName;
	
	public JsonParkingViolationReader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public List<ParkingViolation> getAllParkingViolations() {
		List<ParkingViolation> parkingViolationList = new ArrayList<ParkingViolation>();
		// create a parser
		JSONParser parser = new JSONParser();
		// open the file and get the array of JSON objects
		
		JSONArray tweets = null;
		try {
			tweets = (JSONArray)parser.parse(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// use an iterator to iterate over each element of the array
		Iterator iter = tweets.iterator();
		// iterate while there are more objects in array
		while (iter.hasNext()) {
			// get the next JSON object
			JSONObject pv = (JSONObject) iter.next();
			// use the "get" method to print the value associated with that key
			int ticketNumber = Integer.parseInt(String.valueOf(pv.get("ticket_number")));
			String plateID = String.valueOf(pv.get("plate_id"));
			String date = String.valueOf(pv.get("date"));
			String zipCode = String.valueOf(pv.get("zip_code"));
			String violation = String.valueOf(pv.get("violation"));
			int fine = Integer.parseInt(String.valueOf(pv.get("fine")));
			String state = String.valueOf(pv.get("state"));
			ParkingViolation parkingViolation = new ParkingViolation(date, fine, violation, plateID, state, ticketNumber, zipCode);
            parkingViolationList.add(parkingViolation);
		}
		return parkingViolationList;
	}

}
