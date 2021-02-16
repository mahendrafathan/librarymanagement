package com.glints.librarymanagement.controller;

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

import com.glints.librarymanagement.model.Book;
import com.glints.librarymanagement.model.Borrowing;
import com.glints.librarymanagement.entity.User;
import com.glints.librarymanagement.model.Member;
import com.glints.librarymanagement.payload.BorrowingPayload;
import com.glints.librarymanagement.payload.ErrorResponse;
import com.glints.librarymanagement.repository.BookRepo;
import com.glints.librarymanagement.repository.BorrowingRepo;
import com.glints.librarymanagement.repository.UserRepository;
import com.glints.librarymanagement.repository.MemberRepo;

@RestController
@RequestMapping("/borrowing")
public class BorrowingController {

	@Autowired
	BorrowingRepo borrowingRepo;

	@Autowired
	BookRepo bookRepo;

	@Autowired
	MemberRepo memberRepo;

	@Autowired
	UserRepository employeeRepo;

	@GetMapping(path = "/get", produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<Borrowing> borrower = borrowingRepo.findAll();
//		List<Borrowing> borrower = borrowingRepo.findAllJoin();
		return new ResponseEntity<List<Borrowing>>(borrower, HttpStatus.OK);
	}

	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Borrowing borrower = borrowingRepo.findById(id).orElse(null);
		if (borrower == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrowing not found", "Data yang anda cari tidak ditemukan"),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Borrowing>(borrower, HttpStatus.OK);
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createBorrow(@RequestBody BorrowingPayload payload) {

		User employee = employeeRepo.findByUserNameIgnoreCase(payload.getEmployee());
		if (employee == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Employee not found", "Petugas yang anda masukan tidak terdaftar"),
					HttpStatus.BAD_REQUEST);
		}

		Member member = memberRepo.findByNameIgnoreCase(payload.getMember());
		if (member == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse("Member not found", "Member tidak terdaftar"),
					HttpStatus.BAD_REQUEST);
		}

		Book book = bookRepo.findByTitleIgnoreCase(payload.getBook());
		if (book == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse("Book not found", "Buku tidak terdaftar"),
					HttpStatus.BAD_REQUEST);
		}

		try {
			Book updateBook = bookRepo.findByTitleIgnoreCase(payload.getBook());
			updateBook.setQuantity(updateBook.getQuantity() - 1);
			bookRepo.save(updateBook);

			Borrowing newBorrow = new Borrowing(null, member, employee, book, payload.getBorrowDate(),
					payload.getReturnDate(), null);
			newBorrow.setIsReturned(false);
			borrowingRepo.save(newBorrow);

		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse(e.getMessage(), "Maaf request anda tidak dapat diproses"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<BorrowingPayload>(payload, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/soft-delete/{id}", produces = "application/json")
	public ResponseEntity<?> deleteAuthor(@PathVariable("id") Long id) {
		Borrowing borrower = borrowingRepo.findById(id).orElse(null);
		if (borrower == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrower not found", "Data borrower tidak ditemukan."), HttpStatus.NOT_FOUND);
		}
		if (borrower.isDeleted() == true) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrower has deleted.", "Borrower sudah dihapus."), HttpStatus.FORBIDDEN);
		}

		try {
			borrower.setDeleted(true);
			borrowingRepo.save(borrower);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), "Request anda gagal"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>("Delete borrower success!", HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/cancel-delete/{id}", produces = "application/json")
	public ResponseEntity<?> cancelDeleteAuthor(@PathVariable("id") Long id) {
		Borrowing borrower = borrowingRepo.findById(id).orElse(null);
		if (borrower == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrower not found", "Data borrower tidak ditemukan."), HttpStatus.NOT_FOUND);

		}
		if (borrower.isDeleted() == false) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrower data is active.", "Data borrower masih tersedia."),
					HttpStatus.FORBIDDEN);
		}

		try {
			borrower.setDeleted(false);
			borrowingRepo.save(borrower);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), "Request anda gagal"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>("Undo delete borrower success!", HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/permanent-delete/{id}", produces = "application/json")
	public ResponseEntity<?> forceDeleteAuthor(@PathVariable("id") Long id) {
		Borrowing borrower = borrowingRepo.findById(id).orElse(null);
		if (borrower == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrower not found", "Data borrower tidak ditemukan."), HttpStatus.NOT_FOUND);
		}
		if (borrower.isDeleted() == false) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrower data is active.", "Data borrower tidak dapat dihapus permanent."),
					HttpStatus.FORBIDDEN);
		}

		borrowingRepo.deleteById(id);
		return new ResponseEntity<String>("Delete borrower data success! borrower deleted permanent!",
				HttpStatus.ACCEPTED);
	}

	@PutMapping(path = "/return-status/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateIsReturned(@PathVariable("id") Long id, @RequestBody BorrowingPayload payload)
			throws Exception {
		Borrowing existBorrower = borrowingRepo.findById(id).orElse(null);
		if (existBorrower == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrower not found", "Data borrower tidak ditemukan."), HttpStatus.BAD_REQUEST);
		}
		try {
			existBorrower.setIsReturned(true);
			borrowingRepo.save(existBorrower);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), "Request anda gagal"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<BorrowingPayload>(payload, HttpStatus.ACCEPTED);
	}

}
