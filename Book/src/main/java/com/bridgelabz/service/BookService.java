package com.bridgelabz.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.dto.BookDTO;
import com.bridgelabz.exception.BookException;
import com.bridgelabz.model.BookModel;
import com.bridgelabz.repository.BookRepository;
import com.bridgelabz.util.AdminResponse;
import com.bridgelabz.util.Response;

@Service
public class BookService implements IBookService {
	@Autowired
	BookRepository repository;
	@Autowired
	RestTemplate restTemplate;

	@Override
	public Response addBook(BookDTO bookDTO, String adminToken) {
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + adminToken,
				AdminResponse.class);
		if (isAdmin.getCode() == 200) {
			BookModel bookModel = new BookModel(bookDTO);
			Optional<BookModel> isBook = repository.findByBookName(bookModel.getBookName());
			if (isBook.isEmpty()) {
				bookModel.setCreatedDateTime(LocalDateTime.now());
				repository.save(bookModel);
				return new Response(200, "Success", bookModel);
			}
			throw new BookException(500, "Book name is Alredy Registred" + isBook.get());
		}
		throw new BookException(400, "Admin is not found");
	}

	@Override
	public Response getAll() {
		// TODO Auto-generated method stub
		List<BookModel> isBooks = repository.findAll();
		if (isBooks.size() > 0) {
			return new Response(200, "Success", isBooks);
		}
		throw new BookException(500, "No Books found");
	}

	@Override
	public Response getBook(Long id) {
		// TODO Auto-generated method stub
		Optional<BookModel> isBook = repository.findById(id);
		if (isBook.isPresent()) {
			return new Response(200, "Success", isBook.get());
		}
		throw new BookException(500, "No Book found with this id");
	}

	@Override
	public Response updateBook(Long id, BookDTO bookDTO, String adminToken) {
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + adminToken,
				AdminResponse.class);
		if (isAdmin.getCode() == 200) {
			// TODO Auto-generated method stub
			Optional<BookModel> isBook = repository.findById(id);
			if (isBook.isPresent()) {
				isBook.get().setBookName(bookDTO.getBookName());
				isBook.get().setBookAuthor(bookDTO.getBookAuthor());
				isBook.get().setBookPrice(bookDTO.getBookPrice());
				isBook.get().setBookQuantity(bookDTO.getBookQuantity());
				isBook.get().setUpdatedDateTime(LocalDateTime.now());
				repository.save(isBook.get());
				return new Response(200, "Success", isBook.get());
			}
			throw new BookException(500, "No Book found with this id");
		}
		throw new BookException(400, "Admin is not found");
	}

	@Override
	public Response deleteBook(Long id, String adminToken) {
		AdminResponse isAdmin = restTemplate.getForObject("http://Admin:8080/admin/verify/" + adminToken,
				AdminResponse.class);
		if (isAdmin.getCode() == 200) {
			// TODO Auto-generated method stub
			Optional<BookModel> isBook = repository.findById(id);
			if (isBook.isPresent()) {
				repository.delete(isBook.get());
				return new Response(200, "Success", isBook.get());
			}
			throw new BookException(500, "No Book found with this id");
		}
		throw new BookException(400, "Admin is not found");
	}

	@Override
	public Response verifyBook(Long id) {
		Optional<BookModel> isBook = repository.findById(id);
		if (isBook.isPresent()) {
			return new Response(200, "Success", isBook.get());
		}
		// TODO Auto-generated method stub
		throw new BookException(500, "No Book found with this id");
	}

	@Override
	public Response decresingBookQuantity(Long bookId, Long qunatity) {
		// TODO Auto-generated method stub
		Optional<BookModel> isBook = repository.findById(bookId);
		if (isBook.isPresent()) {
			Long bookQunatity = isBook.get().getBookQuantity() - qunatity;
			isBook.get().setBookQuantity(bookQunatity);
			repository.save(isBook.get());
			return new Response(200, "Success", isBook.get());
		}
		// TODO Auto-generated method stub
		throw new BookException(500, "No Book found with this id");
	}

	@Override
	public Response incresingBookQuantity(Long bookId, Long qunatity) {
		// TODO Auto-generated method stub
		Optional<BookModel> isBook = repository.findById(bookId);
		if (isBook.isPresent()) {
			Long bookQunatity = isBook.get().getBookQuantity() + qunatity;
			isBook.get().setBookQuantity(bookQunatity);
			repository.save(isBook.get());
			return new Response(200, "Success", isBook.get());
		}
		// TODO Auto-generated method stub
		throw new BookException(500, "No Book found with this id");
	}

	@Override
	public Response search(String bookName) {
		// TODO Auto-generated method stub
		List<BookModel> isBook = repository.findByBookNameContainsIgnoreCase(bookName);
		if (isBook.size() > 0) {
			return new Response(200, isBook.size() + " Books found with this name ", isBook);
		}
		throw new BookException(400, "not found");
	}

	@Override
	public Response searchByAuthorName(String authorName) {
		// TODO Auto-generated method stub
		List<BookModel> isAuthor = repository.findByBookAuthorContainsIgnoreCase(authorName);
		if (isAuthor.size() > 0) {
			return new Response(200, isAuthor.size() + " authors found with this name ", isAuthor);
		}
		throw new BookException(400, "not found");
	}

}
