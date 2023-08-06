package com.fssa.agrokart.exceptions;

/**
 * user defined exception is created to throw the SQLEXCEPTION as DAO exception
 *
 * @author HemanathMuralikrishnan
 */

public class ProductDAOException extends Exception {

    // Calling each super constructor for each of the types

    public ProductDAOException(String msg) {
        super(msg);
    }

    public ProductDAOException(Throwable te) {
        super(te);
    }

    public ProductDAOException(String msg, Throwable te) {
        super(msg, te);
    }
}
