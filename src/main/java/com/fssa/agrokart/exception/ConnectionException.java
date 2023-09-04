/**
 * A custom exception class for handling connection-related errors.
 * This exception is thrown when there is an issue with connecting to a database,
 * and it provides specific details about the connection error.
 *
 * @author HemanathMuralikrishnan
 * @see com.fssa.agrokart.dao.DatabaseManager for database connection management.
 */
package com.fssa.agrokart.exception;

/**
 * Custom exception class to handle connection-related errors.
 *
 * @see com.fssa.agrokart.dao for database connection management.
 */
public class ConnectionException extends Exception {

    /**
     * Constructs a new ConnectionException with the specified error message.
     *
     * @param msg The error message describing the connection issue.
     */
    public ConnectionException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new ConnectionException with the specified cause.
     *
     * @param te The underlying cause of the connection issue.
     */
    public ConnectionException(Throwable te) {
        super(te);
    }

    /**
     * Constructs a new ConnectionException with the specified error message and cause.
     *
     * @param msg The error message describing the connection issue.
     * @param te  The underlying cause of the connection issue.
     */
    public ConnectionException(String msg, Throwable te) {
        super(msg, te);
    }

}
