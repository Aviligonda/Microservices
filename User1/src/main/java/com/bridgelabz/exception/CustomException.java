/**
 * 
 */
package com.bridgelabz.exception;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

/**
 * @author Honey
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException {

	private int errorCode;
	private String errorMessage;

}
