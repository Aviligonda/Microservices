package com.bridgelabz.model;

import java.time.LocalDateTime;

import com.bridgelabz.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "userService")
@NoArgsConstructor
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int adminEmpId;
	private String name;
	private String gender;
	private int age;
	private String email;
	private String password;
	private int OTP;
	private boolean verifyUser;
	private LocalDateTime creatdTime;
	private LocalDateTime updatedTime;

	public UserModel(UserDTO userDTO) {
		super();
		this.name = userDTO.getName();
		this.gender = userDTO.getGender();
		this.age = userDTO.getAge();
		this.email=userDTO.getEmail();
		this.password = userDTO.getPassword();
	}

	
}
