package com.fssa.agrokart.exceptions;

import java.io.Serial;

/**
 *
 * A class for connection exception while connectin to the database there may be
 * any error we can't throw generic exception so I created user defined exception for connection
 * @author HemanathMuralikrishnan
 */

public class ConnectionException extends Exception{
    @Serial
    private static final long serialVersionUID = -8105491977357554060L;

    // Calling each super constructor for each of the types
    public ConnectionException(String msg) {
        super(msg);
    }

    public ConnectionException(Throwable te) {
        super(te);
    }

    public ConnectionException(String msg, Throwable te) {
        super(msg, te);
    }

}
