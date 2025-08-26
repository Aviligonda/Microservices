package com.bridgelabz.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CartException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code;
	String message;

	public CartException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

}
