/**
 * Custom exception class for handling Data Access Object (DAO) related errors.
 * This exception is used to encapsulate and propagate exceptions that occur during database operations.
 * It provides specific details about the DAO-related error.
 *
 * @author HemanathMuralikrishnan
 * @see com.fssa.agrokart.dao.DataAccessObject for database operations.
 */
package com.fssa.agrokart.exception;

/**
 * Custom exception class to handle Data Access Object (DAO) related errors.
 * This exception is used to encapsulate and propagate exceptions that occur during database operations.
 *
 * @see com.fssa.agrokart.dao for database operations.
 */
public class DAOException extends Exception {

    /**
     * Constructs a new DAOException with the specified error message.
     *
     * @param msg The error message describing the DAO-related issue.
     */
    public DAOException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new DAOException with the specified cause.
     *
     * @param te The underlying cause of the DAO-related issue.
     */
    public DAOException(Throwable te) {
        super(te);
    }

    /**
     * Constructs a new DAOException with the specified error message and cause.
     *
     * @param msg The error message describing the DAO-related issue.
     * @param te  The underlying cause of the DAO-related issue.
     */
    public DAOException(String msg, Throwable te) {
        super(msg, te);
    }

}
