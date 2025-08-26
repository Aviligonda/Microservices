package com.bridgelabz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.util.ResponseClass;

@ControllerAdvice
public class OrderExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseClass> handleException(OrderException exception) {
		ResponseClass responseClass = new ResponseClass();
		responseClass.setCode(400);
		responseClass.setMessage(exception.getLocalizedMessage());
		return new ResponseEntity<ResponseClass>(responseClass, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
