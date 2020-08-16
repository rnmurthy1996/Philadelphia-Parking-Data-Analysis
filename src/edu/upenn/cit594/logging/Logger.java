package edu.upenn.cit594.logging;

import java.io.FileWriter;
import java.io.PrintWriter;

/*
 *  Leverages singleton pattern to create a single instance for application-wide logging
 */

public class Logger {
	public static String logName;
	private static Logger instance;
	private PrintWriter out;

	private Logger() {
		try { out = new PrintWriter(new FileWriter(logName, true)); }
		catch (Exception e) { }
	}
	
	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	public void log(Object data) {
		String output = String.format("%d %s\n", System.currentTimeMillis(), data.toString());
		out.write(output);
		out.flush();
	}
}
