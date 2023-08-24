package com.fssa.agrokart.exception;

/**
 * user defined exception is created to throw the SQLEXCEPTION as DAO exception
 *
 * @author HemanathMuralikrishnan
 */

public class DAOException extends Exception {

    // Calling each super constructor for each of the types

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(Throwable te) {
        super(te);
    }

    public DAOException(String msg, Throwable te) {
        super(msg, te);
    }
}
