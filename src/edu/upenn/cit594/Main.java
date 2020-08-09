package edu.upenn.cit594;

import java.io.File;

import edu.upenn.cit594.datamanagement.CsvParkingViolationReader;
import edu.upenn.cit594.datamanagement.JsonParkingViolationReader;
import edu.upenn.cit594.datamanagement.ParkingViolationReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ParkingViolationProcessor;
import edu.upenn.cit594.ui.CommandLineUserInterface;

public class Main {
	
	public static void main(String[] args) {
		
		// Argument Validation
		if (args.length < 5) {
			System.out.println("Incorrect number of arguments.");
			System.exit(0);
		}
		
		String format = args[0].toLowerCase();
		if (!format.equals("csv") && !format.equals("json")) {
			System.out.println("Incorrect format type specified: must be either \"text\" or \"json\"");
			System.exit(0);
		}
		
		File parkingFile = new File(args[1]);
		File propertyFile = new File(args[2]);
		File populationFile = new File(args[3]);
		if (!parkingFile.canRead() || !propertyFile.canRead() || !populationFile.canRead()) {
			System.out.println("Required input file is missing or unreadable.");
			System.exit(0);
		}
		
		Logger.logName = args[4];
		String runtimeArgs = String.format("%s %s %s %s %s", args[0], args[1], args[2], args[3], args[4]); 
		Logger.getInstance().log(runtimeArgs);
		
		
		// Program Setup		
		ParkingViolationReader pvReader;
		if (format.equals("csv")) {
			pvReader = new CsvParkingViolationReader(args[1]);
		} else {
			pvReader = new JsonParkingViolationReader(args[1]);
		}
		
		PropertyReader propReader = new PropertyReader(args[2]);
		PopulationReader popReader = new PopulationReader(args[3]);
		ParkingViolationProcessor processor = new ParkingViolationProcessor(pvReader, propReader, popReader);
		CommandLineUserInterface cli = new CommandLineUserInterface(processor);
		
		
		// Run Program
		cli.run();
	}
	
}
