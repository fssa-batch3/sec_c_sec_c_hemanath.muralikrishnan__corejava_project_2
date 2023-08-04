package com.fssa.agrokart.exceptions;

public class ProductDAOException extends Exception {

	// Calling each super constructors for each of the types
	
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
