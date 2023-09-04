/**
 * Custom exception class for handling service-related errors.
 * This exception is used to indicate errors that occur in the service layer of the application.
 * It encapsulates and propagates errors related to service operations.
 *
 * @see com.fssa.agrokart.service.ServiceLayer for application services.
 * @see com.fssa.agrokart.exception.DAOException for lower-level data access errors.
 * @see com.fssa.agrokart.exception.InvalidInputException for invalid input data errors.
 * @see com.fssa.agrokart.exception.ConnectionException for database connection errors.
 * @see com.fssa.agrokart.model.Product for product-related services.
 * @see com.fssa.agrokart.validator.ProductValidator for product data validation.
 * @see com.fssa.agrokart.exception.ServiceException for service-related errors.
 */
package com.fssa.agrokart.exception;

/**
 * Custom exception class to handle errors that occur in the service layer of the application.
 * This exception is used to indicate errors related to service operations.
 *
 * @see com.fssa.agrokart.service for application services.
 * @see com.fssa.agrokart.exception.DAOException for lower-level data access errors.
 * @see com.fssa.agrokart.exception.InvalidInputException for invalid input data errors.
 * @see com.fssa.agrokart.exception.ConnectionException for database connection errors.
 * @see com.fssa.agrokart.model.Product for product-related services.
 * @see com.fssa.agrokart.validator.ProductValidator for product data validation.
 */
public class ServiceException extends Exception {

    /**
     * Constructs a new ServiceException with the specified error message.
     *
     * @param msg The error message describing the service-related issue.
     */
    public ServiceException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new ServiceException with the specified cause.
     *
     * @param te The underlying cause of the service-related issue.
     */
    public ServiceException(Throwable te) {
        super(te);
    }

    /**
     * Constructs a new ServiceException with the specified error message and cause.
     *
     * @param msg The error message describing the service-related issue.
     * @param te  The underlying cause of the service-related issue.
     */
    public ServiceException(String msg, Throwable te) {
        super(msg, te);
    }
}
