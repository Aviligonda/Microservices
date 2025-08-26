package com.bridgelabz.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class UserDTO {
	private Long id;
	private int adminEmpId;
	private String name;
	private String gender;
	private int age;
	private String email;
	private String password;
	private LocalDateTime creatdTime;
	private LocalDateTime updatedTime;

}
