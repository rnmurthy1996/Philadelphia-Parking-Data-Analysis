package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.PopulationCenter;
import edu.upenn.cit594.logging.Logger;

/*
 * PopulationReader class that reads in all population centers from population.txt and adds them to a list.
 */

public class PopulationReader {
	
	protected String fileName;
	
	public PopulationReader(String fileName) {
		this.fileName = fileName;
	}
	
	public List<PopulationCenter> getAllPopulations() {
		List<PopulationCenter> populationList = new ArrayList<PopulationCenter>();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        	
        	Logger.getInstance().log(fileName);

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] tokens = line.split(" ");
                int population = Integer.valueOf(tokens[1]);
                PopulationCenter populationCenter = new PopulationCenter(tokens[0], population);
                populationList.add(populationCenter);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return populationList;
	}
}
