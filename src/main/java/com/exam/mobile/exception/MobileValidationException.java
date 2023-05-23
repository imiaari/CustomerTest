package com.exam.mobile.exception;

public class MobileValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MobileValidationException() {
		super();
	}

	public MobileValidationException(String message) {
		super(message);
	}

	public MobileValidationException(Throwable cause) {
		super(cause);
	}

	public MobileValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}
