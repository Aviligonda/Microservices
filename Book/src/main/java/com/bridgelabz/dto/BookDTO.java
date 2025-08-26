package com.bridgelabz.dto;

import lombok.Data;

@Data
public class BookDTO {

	private String bookName;
	private String bookAuthor;
	private Long bookQuantity;
	private double bookPrice;
}
