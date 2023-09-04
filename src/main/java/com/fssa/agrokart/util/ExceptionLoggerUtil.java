package com.fssa.agrokart.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for logging exceptions with customizable log levels.
 */
public class ExceptionLoggerUtil {

	private static final Logger logger = Logger.getLogger(ExceptionLoggerUtil.class.getName());

	/**
	 * Logs the given exception with the default log level (SEVERE).
	 *
	 * @param exception The exception to be logged.
	 */
	public static void logException(Throwable exception) {
		logException(Level.SEVERE, exception);
	}

	/**
	 * Logs the given exception with the specified log level.
	 *
	 * @param level     The log level to use for logging the exception.
	 * @param exception The exception to be logged.
	 */
	public static void logException(Level level, Throwable exception) {
		logger.log(level, "Exception Message: " + exception.getMessage(), exception);
	}
}
