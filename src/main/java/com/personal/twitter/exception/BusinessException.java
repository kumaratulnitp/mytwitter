package com.personal.twitter.exception;

public class BusinessException extends Exception {

	public BusinessException(String message) {
		this.message = message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	@Override
	public String getMessage() {
		return message;
	}
}
