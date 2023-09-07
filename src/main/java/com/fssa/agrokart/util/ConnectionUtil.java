package com.fssa.agrokart.util;

import java.sql.Connection;
import java.sql.DriverManager;

import com.fssa.agrokart.exception.ConnectionException;

/**
 * A utility class to manage database connections using environment-based
 * credentials. This class provides methods to obtain database connections for
 * local and cloud environments.
 *
 * @author HemanathMuralikrishnan
 */
public class ConnectionUtil {

	/**
	 * Get a database connection based on environment-specific credentials. If the
	 * environment variable "CI" is set, cloud database credentials are used.
	 * Otherwise, local database credentials are retrieved from the environment.
	 *
	 * @return A Connection object representing the database connection.
	 * @throws ConnectionException If unable to establish a database connection.
	 */
	public static Connection getConnection() throws ConnectionException {
		Connection con = null;

		String url;
		String userName;
		String passWord;

		// For database credentials
		url = System.getenv("DATABASE_HOST");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
		} catch (Exception e) {
			throw new ConnectionException(e);
		}
		return con;
	}

	/**
	 * Private constructor to prevent instantiation of the ConnectionUtil class.
	 * This class only provides static utility methods and should not be
	 * instantiated.
	 */
	private ConnectionUtil() {

	}

}
