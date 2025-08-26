package com.bridgelabz.model;

import com.bridgelabz.dto.AddressDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "address")
@NoArgsConstructor
public class AddressModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private long phoneNumber;
	private Long userId;

	public AddressModel(AddressDTO addressDTO) {
		super();
		this.name = addressDTO.getName();
		this.phoneNumber = addressDTO.getPhoneNumber();
	}

}
