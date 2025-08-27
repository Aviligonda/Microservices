package com.bridgelabz.dto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {
	@NotNull(message = "Name not should be null")
	private String name;
	@NotNull(message = "Gender not should be null")
	private String gender;
	@NotNull(message = "Age not should be null")
	private int age;
	@NotNull(message = "email not should be null")
	private String email;
	@NotNull(message = "password not should be null")
	private String password;

}
