package com.bridgelabz.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Purpose :Return Status
 * @author : Aviligonda Sreenivasulu
 *
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	int code;
	String message;
	Object object;
}
