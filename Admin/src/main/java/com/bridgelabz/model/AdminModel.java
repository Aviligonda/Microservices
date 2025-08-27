package com.bridgelabz.model;

import java.time.LocalDateTime;

import com.bridgelabz.dto.AdminDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Purpose : AdminModel Are Used Create A table and connection to Database
 *
 * @author : Aviligonda Sreenivasulu
 **/
@Data
@Entity
@Table(name = "Admin")
@NoArgsConstructor
public class AdminModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private int empId;
	private String name;
	private int age;
	private String email;
	private String password;
	private LocalDateTime createDateTime;
	private LocalDateTime updatedDateTime;

	public AdminModel(AdminDTO adminDTO) {
		super();
		this.name = adminDTO.getName();
		this.age = adminDTO.getAge();
		this.email = adminDTO.getEmail();
		this.password = adminDTO.getPassword();
	}

}
