package com.bridgelabz.model;

import com.bridgelabz.dto.CartDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cart")
@NoArgsConstructor
public class CartModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Long userId;
	private Long bookId;
	private Long qunatity;
	private double totalPrice;

	public CartModel(CartDTO cartDTO) {
		this.qunatity = cartDTO.getQuantity();
	}

}
