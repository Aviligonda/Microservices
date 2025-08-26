package com.bridgelabz.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookDTO {
	private Long id;
	private String bookName;
	private String bookAuthor;
	private Long bookQuantity;
	private double bookPrice;
	private LocalDateTime createdDateTime;
	private LocalDateTime updatedDateTime;
}
