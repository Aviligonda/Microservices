package com.bridgelabz.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
@ResponseStatus
public class BookException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int errorCode;
	String message;
	public BookException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.message = message;
	}
	
	
}
