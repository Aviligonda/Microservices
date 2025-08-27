package com.bridgelabz.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Purpose : CustomException are Used to Validation exception
 * @author : Aviligonda Sreenivasulu
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException {

	private int errorCode;
	private String errorMessage;
}
