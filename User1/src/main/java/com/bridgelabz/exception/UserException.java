package com.bridgelabz.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@SuppressWarnings("unused")
public class UserException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String status;
	public UserException(int code, String status) {
		super(status);
		this.code = code;
		this.status = status;
	
	}
	

}
