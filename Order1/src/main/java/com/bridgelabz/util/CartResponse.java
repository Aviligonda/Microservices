package com.bridgelabz.util;

import com.bridgelabz.dto.CartDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

	private int code;
	private String message;
	private CartDTO object;
}
