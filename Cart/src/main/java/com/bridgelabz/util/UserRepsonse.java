package com.bridgelabz.util;

import com.bridgelabz.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRepsonse {

	private int code;
	private String message;
	private UserDTO object;
}
