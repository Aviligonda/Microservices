package com.bridgelabz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.util.ResponseClass;

@ControllerAdvice
public class BookExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseClass> handleException(BookException exception) {
		ResponseClass responseClass = new ResponseClass();
		responseClass.setCode(400);
		responseClass.setMessage(exception.getLocalizedMessage());
		return new ResponseEntity<>(responseClass, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
