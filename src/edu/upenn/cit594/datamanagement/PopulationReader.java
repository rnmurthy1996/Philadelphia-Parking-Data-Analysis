package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.ParkingViolation;
import edu.upenn.cit594.data.PopulationCenter;

public class PopulationReader {
	
	public List<PopulationCenter> getAllPopulations(String fileName) {
		List<PopulationCenter> populationList = new ArrayList<PopulationCenter>();
		
		String txtFile = fileName;
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] zip = line.split(" ");
                int zipCode = Integer.valueOf(zip[0]);
                int population = Integer.valueOf(zip[1]);
                PopulationCenter populationCenter = new PopulationCenter(zipCode, population);
                populationList.add(populationCenter);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return populationList;
	}
}
