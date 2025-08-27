package com.bridgelabz.service;

import com.bridgelabz.dto.BookDTO;
import com.bridgelabz.util.Response;

public interface IBookService {

	Response addBook(BookDTO bookDTO,String adminToken);

	Response getAll();

	Response getBook(Long id);

	Response updateBook(Long id, BookDTO bookDTO,String adminToken);

	Response deleteBook(Long id,String adminToken);

	Response verifyBook(Long id);

	Response decresingBookQuantity(Long bookId, Long qunatity);

	Response incresingBookQuantity(Long bookId, Long qunatity);

	Response search(String bookName);

	Response searchByAuthorName(String authorName);

}
