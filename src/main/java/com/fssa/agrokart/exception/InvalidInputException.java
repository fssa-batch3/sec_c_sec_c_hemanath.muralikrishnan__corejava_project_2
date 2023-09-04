/**
 * Custom exception class for handling invalid input data when creating a product.
 * This exception is used to indicate that the provided input data for creating a product is invalid.
 * It encapsulates and propagates errors related to invalid input data.
 *
 * @author HemanathMuralikrishnan
 * @see com.fssa.agrokart.model.Product for product creation.
 */
package com.fssa.agrokart.exception;

/**
 * Custom exception class to handle invalid input data when creating a product.
 * This exception is used to indicate that the provided input data for creating a product is invalid.
 *
 * @see com.fssa.agrokart.model.Product for product creation.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs a new InvalidInputException with the specified error message.
     *
     * @param msg The error message describing the invalid input issue.
     */
    public InvalidInputException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new InvalidInputException with the specified cause.
     *
     * @param te The underlying cause of the invalid input issue.
     */
    public InvalidInputException(Throwable te) {
        super(te);
    }

    /**
     * Constructs a new InvalidInputException with the specified error message and cause.
     *
     * @param msg The error message describing the invalid input issue.
     * @param te  The underlying cause of the invalid input issue.
     */
    public InvalidInputException(String msg, Throwable te) {
        super(msg, te);
    }

}
