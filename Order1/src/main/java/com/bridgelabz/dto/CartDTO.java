package com.bridgelabz.dto;

import lombok.Data;

@Data
public class CartDTO {
	private Long id;
	private Long userId;
	private Long bookId;
	private Long qunatity;
	private double totalPrice;
}
