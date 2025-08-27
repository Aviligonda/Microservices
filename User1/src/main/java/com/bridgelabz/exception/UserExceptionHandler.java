package com.bridgelabz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.util.ResponseClass;

@ControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ResponseClass> handleHiringException(UserException exception) {
		ResponseClass response = new ResponseClass();
		response.setCode(400);
		response.setMessageS(exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomException> validationHandleException(MethodArgumentNotValidException exception) {
		CustomException response = new CustomException();
		response.setErrorCode(400);
		response.setErrorMessage(exception.getFieldError().getDefaultMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
