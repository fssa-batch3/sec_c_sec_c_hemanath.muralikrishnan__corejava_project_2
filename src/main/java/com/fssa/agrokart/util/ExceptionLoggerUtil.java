package com.fssa.agrokart.util;

/**
 * Utility class for logging exceptions with customizable log levels.
 */
public class ExceptionLoggerUtil {

	public static void logException(Throwable exception) {
		// Print the exception message and stack trace to the console
		System.err.println("Exception Message: " + exception.getMessage());
		exception.printStackTrace();
	}

}
