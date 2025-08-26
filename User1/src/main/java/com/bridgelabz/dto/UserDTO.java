package com.bridgelabz.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class UserDTO {
	@Pattern(regexp = "[A-Z][a-z]{2,}")
	private String name;
//	@Pattern( message = "Not null", regexp = "")
	private String gender;
//	@Pattern(message = "Not null", regexp = "\\d{2}")
	private int age;
	private String email;
	private String password;

}
