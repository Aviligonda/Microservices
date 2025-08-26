package com.bridgelabz.service;

import com.bridgelabz.dto.BookDTO;
import com.bridgelabz.util.Response;

public interface IBookService {

	Response addBook(BookDTO bookDTO,String token);

	Response getAll();

	Response getBook(Long id);

	Response updateBook(Long id, BookDTO bookDTO,String token);

	Response deleteBook(Long id,String token);

	Response verifyBook(Long id);

	Response decresingBookQuantity(Long bookId, Long qunatity);

	Response incresingBookQuantity(Long bookId, Long qunatity);

}
