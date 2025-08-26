package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.dto.BookDTO;
import com.bridgelabz.service.IBookService;
import com.bridgelabz.util.Response;

@RestController
@RequestMapping("book")
public class BooksController {

	@Autowired
	IBookService bookService;

	@PostMapping("/add")
	public ResponseEntity<Response> addBook(@RequestBody BookDTO bookDTO, @RequestHeader String token) {
		Response response = bookService.addBook(bookDTO, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getBook(@PathVariable Long id) {
		Response response = bookService.getBook(id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Response> getAll() {
		Response response = bookService.getAll();
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Response> updaeteBook(@PathVariable Long id, @RequestBody BookDTO bookDTO,
			@RequestHeader String token) {
		Response response = bookService.updateBook(id, bookDTO, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteBook(@PathVariable Long id, @RequestHeader String token) {
		Response response = bookService.deleteBook(id, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("verify/{id}")
	public ResponseEntity<Response> verifyBook(@PathVariable Long id) {
		Response response = bookService.verifyBook(id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("decresingBookQuantity/{bookId}/{quantity}")
	public ResponseEntity<Response> decresingBookQuantity(@PathVariable Long bookId, @PathVariable Long quantity) {
		Response response = bookService.decresingBookQuantity(bookId, quantity);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("incresingBookQuantity/{bookId}/{quantity}")
	public ResponseEntity<Response> incresingBookQuantity(@PathVariable Long bookId, @PathVariable Long quantity) {
		Response response = bookService.incresingBookQuantity(bookId, quantity);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
