package com.bridgelabz.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class AdminDTO {
	private Long id;
	private int empId;
	private String name;
	private int age;
	private String email;
	private String password;
	private LocalDateTime createDateTime;
	private LocalDateTime updatedDateTime;


}
