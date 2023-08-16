/**
 *
 */
package com.fssa.agrokart.exception;

import java.io.Serial;

/**
 *         user defined exception is created to throw the invalid data given to
 *         create the product
 * @author HemanathMuralikrishnan
 */
public class InvalidProductDataException extends Exception {
    @Serial
    private static final long serialVersionUID = -8105491977357554060L;

    // Calling each super constructor for each of the types
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
