package com.bridgelabz.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Purpose : ResponseClass Used to Handle Exception
 * @author : Aviligonda Sreenivasulu
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClass {

	int errorCode;
	String errorMessage;
}
