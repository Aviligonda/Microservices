package com.bridgelabz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.util.ResponseClass;

@ControllerAdvice
public class AdminExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ResponseClass> handleException(AdminException exception) {
		ResponseClass responseClass = new ResponseClass();
		responseClass.setErrorCode(400);
		responseClass.setErrorMessage(exception.getMessage());
		return new ResponseEntity<>(responseClass, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
