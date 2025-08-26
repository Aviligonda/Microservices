package com.bridgelabz.model;

import java.time.LocalDateTime;

import com.bridgelabz.dto.BookDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
public class BookModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String bookName;
	private String bookAuthor;
	private Long bookQuantity;
	private double bookPrice;
	private LocalDateTime createdDateTime;
	private LocalDateTime updatedDateTime;

	public BookModel(BookDTO bookDTO) {
		super();
		this.bookName = bookDTO.getBookName();
		this.bookAuthor = bookDTO.getBookAuthor();
		this.bookQuantity = bookDTO.getBookQuantity();
		this.bookPrice = bookDTO.getBookPrice();
	}

}
