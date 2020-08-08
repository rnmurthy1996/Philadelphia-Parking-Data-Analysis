package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;

public class CsvParkingViolationReader implements ParkingViolationReader {
	
	protected String fileName;
	
	public CsvParkingViolationReader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public List<ParkingViolation> getAllParkingViolations() {
		List<ParkingViolation> parkingViolationList = new ArrayList<ParkingViolation>();
		
		String csvFile = fileName;
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] pv = line.split(",");
                int ticketNumber = Integer.valueOf(pv[5]);
                String plateID = pv[3];
                String date = pv[0];
                String zipCode = "null";
                if(pv.length == 7) {
                	zipCode = pv[6];
                }
                String violation = pv[2];
                int fine = Integer.valueOf(pv[1]);
                String state = pv[4];
                ParkingViolation parkingViolation = new ParkingViolation(date, fine, violation, plateID, state, ticketNumber, zipCode);
                parkingViolationList.add(parkingViolation);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return parkingViolationList;
	}

}
