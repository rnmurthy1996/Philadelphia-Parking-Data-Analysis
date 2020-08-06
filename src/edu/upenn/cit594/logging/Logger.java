package edu.upenn.cit594.logging;

import java.io.FileWriter;
import java.io.PrintWriter;

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
	
	public void log(String text) {
		String output = String.format("%d %s\n", System.currentTimeMillis(), text);
		out.write(output);
		out.flush();
	}
}
