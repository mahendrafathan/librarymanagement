package com.glints.librarymanagement.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glints.librarymanagement.model.Author;
import com.glints.librarymanagement.model.Book;
import com.glints.librarymanagement.model.Publisher;
import com.glints.librarymanagement.payload.BookPayload;
import com.glints.librarymanagement.payload.ErrorResponse;
import com.glints.librarymanagement.repository.AuthorRepo;
import com.glints.librarymanagement.repository.BookRepo;
import com.glints.librarymanagement.repository.PublisherRepo;

@RestController
@RequestMapping(path = "/book")
public class BookController {

	@Autowired
	BookRepo bookRepo;

	@Autowired
	AuthorRepo authorRepo;

	@Autowired
	PublisherRepo publisherRepo;

	@GetMapping(path = "/getAll")
	public ResponseEntity<?> getAllBook() {
		List<Book> book = bookRepo.findAll();
		List<BookPayload> response = new ArrayList<BookPayload>();
		for (Book books : book) {
			response.add(new BookPayload(books.getBook_id(), books.getAuthor().getAuthorName(), books.getTitle(),
					books.getYear(), books.getPublisher().getPublisherName(), books.getCategory(),
					books.getQuantity()));
		}
		return new ResponseEntity<List<BookPayload>>(response, HttpStatus.OK);
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<?> getBookById(@PathVariable("id") Integer id) {
		Book bookExist = bookRepo.findById(id).orElse(null);
		if (bookExist == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Book Not Found", "Book yang anda masukkan tidak terdaftar"),
					HttpStatus.BAD_REQUEST);
		}

		Book book = bookRepo.findById(id).orElse(null);
		BookPayload products = new BookPayload(book.getBook_id(), book.getAuthor().getAuthorName(), book.getTitle(),
				book.getYear(), book.getPublisher().getPublisherName(), book.getCategory(), book.getQuantity());
		return new ResponseEntity<BookPayload>(products, HttpStatus.OK);
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity createBook(@RequestBody BookPayload payload) {
		Author author = authorRepo.findByAuthorNameIgnoreCase(payload.getAuthor());
		if (author == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Author Not Found", "Author yang anda masukkan tidak terdaftar"),
					HttpStatus.BAD_REQUEST);
		}

		Publisher publisher = publisherRepo.findByPublisherNameIgnoreCase(payload.getPublisher());
		if (publisher == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Publisher Not Found", "Publisher yang anda masukkan tidak terdaftar"),
					HttpStatus.BAD_REQUEST);
		}

		try {
			Book newBook = new Book(author, payload.getTitle(), payload.getYear(), publisher, payload.getCategory(),
					payload.getQuantity());
			bookRepo.save(newBook);
			payload.setBook_id(newBook.getBook_id());
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse(e.getMessage(), "maaf request anda tidak dapat diproses"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<BookPayload>(payload, HttpStatus.CREATED);
	}

	@PutMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateBook(@PathVariable("id") Integer id, @RequestBody BookPayload payload) {
		Author author = authorRepo.findByAuthorNameIgnoreCase(payload.getAuthor());
		if (author == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Author Not Found", "Author yang anda masukkan tidak terdaftar"),
					HttpStatus.BAD_REQUEST);
		}

		Publisher publisher = publisherRepo.findByPublisherNameIgnoreCase(payload.getPublisher());
		if (publisher == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Publisher Not Found", "Publisher yang anda masukkan tidak terdaftar"),
					HttpStatus.BAD_REQUEST);
		}

		Book bookExist = bookRepo.findById(id).orElse(null);
		if (bookExist == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Book Not Found", "Book yang anda masukkan tidak terdaftar"),
					HttpStatus.BAD_REQUEST);
		}

		bookExist.setAuthor(author);
		bookExist.setTitle(payload.getTitle());
		bookExist.setYear(payload.getYear());
		bookExist.setPublisher(publisher);
		bookExist.setCategory(payload.getCategory());
		bookRepo.save(bookExist);
		payload.setBook_id(bookExist.getBook_id());
		return new ResponseEntity<BookPayload>(payload, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/delete/{id}", produces = "application/json")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) throws SQLException {
		Book bookExist = bookRepo.findById(id).orElse(null);
		if (bookExist == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Book Not Found", "Book yang anda masukkan tidak terdaftar"),
					HttpStatus.BAD_REQUEST);
		}

		bookRepo.deleteById(id);
		return new ResponseEntity<String>("Success Delete Book", HttpStatus.OK);
	}
}