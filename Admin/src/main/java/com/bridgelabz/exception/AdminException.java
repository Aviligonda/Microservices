package com.bridgelabz.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class AdminException extends RuntimeException{

	private int code;
	private String status;
	public AdminException(int code, String status) {
		super(status);
		this.code = code;
		this.status = status;
	}
	
}
