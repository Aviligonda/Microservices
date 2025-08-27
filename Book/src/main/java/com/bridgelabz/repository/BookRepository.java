package com.bridgelabz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.BookModel;

public interface BookRepository extends JpaRepository<BookModel, Long> {

	Optional<BookModel> findByBookName(String bookName);

	List<BookModel> findByBookNameContainsIgnoreCase(String bookName);

	List<BookModel> findByBookAuthorContainsIgnoreCase(String authorName);

}
