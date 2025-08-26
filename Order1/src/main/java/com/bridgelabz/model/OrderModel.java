package com.bridgelabz.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "orderService")
@NoArgsConstructor
public class OrderModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Long userId;
	private Long bookId;
	@OneToOne
	private AddressModel addressModel;
	private double price;
	private boolean cancel;
	private long quantity;
	private LocalDateTime orderDate;
}
