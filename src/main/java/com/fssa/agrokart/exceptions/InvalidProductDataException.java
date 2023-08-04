/**
 * 
 */
package com.fssa.agrokart.exceptions;

/**
 * @author HemanathMuralikrishn
 * 
 *         user defined exception is created to throw the invalid data given to
 *         create the product
 *
 */
public class InvalidProductDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8105491977357554060L;

	// Calling each super constructors for each of the types
	public InvalidProductDataException(String msg) {
		super(msg);
	}

	public InvalidProductDataException(Throwable te) {
		super(te);
	}

	public InvalidProductDataException(String msg, Throwable te) {
		super(msg, te);
	}

}
