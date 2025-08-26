package com.bridgelabz.util;

import org.antlr.v4.runtime.misc.TestRig;

import com.bridgelabz.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private int code;
	private String message;
	private UserDTO object;
}
