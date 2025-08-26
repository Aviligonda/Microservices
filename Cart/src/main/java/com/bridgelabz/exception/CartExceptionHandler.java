package com.bridgelabz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.util.Responseclass;
@ControllerAdvice
public class CartExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<Responseclass> handleException(CartException exception){
		
		Responseclass responseclass = new Responseclass();
		responseclass.setErrorCode(400);
		responseclass.setErrorMessage(exception.getLocalizedMessage());
		return new ResponseEntity<>(responseclass,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
