package edu.upenn.cit594.ui;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.DataProcessor;

public class CommandLineUserInterface {
	
	private DataProcessor processor;
	private Scanner inputStream;
	
	
	public CommandLineUserInterface(DataProcessor processor) {
		this.processor = processor;
		inputStream = new Scanner(System.in);
	}
	
	public void run() {
		int selection = -1;
		
		while (selection != 0) {
			selection = readActionSelection();
			
			switch (selection) {
				case 1:
					executeOptionOne();
					break;
				case 2:
					executeOptionTwo();
					break;
				case 3:
					executeOptionThree();
					break;
				case 4:
					executeOptionFour();
					break;
				case 5:
					executeOptionFive();
					break;
				case 6:
					executeOptionSix();
					break;
				default:
					break;
			}
		}
		
		inputStream.close();
	}
	
	private int readActionSelection() {
		int userInput = -1;
		
		System.out.println("Please enter a selection: 0-6.");
		System.out.println("\t0: Quit");
		System.out.println("\t1: Total Population");
		System.out.println("\t2: Total Fines Per Capita by Zip Code");
		System.out.println("\t3: Average Market Value");
		System.out.println("\t4: Average Total Liveable Area");
		System.out.println("\t5: Total Residential Market Value Per Capita");
		System.out.println("\t6: TBD");
		
		try {
			userInput = inputStream.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Error: incorrect input format. Must be an integer 0-6.");
			inputStream.close();
			System.exit(0);
		} 
		
		Logger.getInstance().log(userInput);
		
		return userInput;
	}
	
	private String readZipCodeSelection() {
		System.out.println("Please enter a 5 digit zip code.");
		String zipCode = inputStream.next();
		Logger.getInstance().log(zipCode);
		return zipCode;
	}
	
	private void executeOptionOne() {
		int population = processor.calculateTotalPopulation();
		System.out.println(population);
	}
	
	private void executeOptionTwo() {
		Map<String, Double> finesPerCapitaByZip = processor.calculateTotalFinesPerCapita();
		
		DecimalFormat format = new DecimalFormat("0.0000");
		format.setRoundingMode(RoundingMode.FLOOR);
		
		for (String zip : finesPerCapitaByZip.keySet()) {
			System.out.println(zip + " " + format.format(finesPerCapitaByZip.get(zip)));
		}
	}
	
	private void executeOptionThree() {
		String zip = readZipCodeSelection();
		System.out.println(processor.calculateAverageMarketValue(zip));
	}

	private void executeOptionFour() {
		String zip = readZipCodeSelection();
		System.out.println(processor.calculateAverageTotalLiveableArea(zip));
	}

	private void executeOptionFive() {
		String zip = readZipCodeSelection();
		System.out.println(processor.calculateTotalResidentialMarketValuePerCapita(zip));
	}
	
	private void executeOptionSix() {
		String zip = readZipCodeSelection();
	}

}
