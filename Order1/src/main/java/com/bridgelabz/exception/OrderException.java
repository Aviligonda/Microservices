package com.bridgelabz.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class OrderException extends RuntimeException{

	private int errorCode;
	private String errorMessage;
	public OrderException(int errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
}
