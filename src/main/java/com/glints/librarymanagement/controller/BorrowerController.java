package com.glints.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glints.librarymanagement.model.Book;
import com.glints.librarymanagement.model.Borrower;
import com.glints.librarymanagement.model.Member;
import com.glints.librarymanagement.model.Petugas;
import com.glints.librarymanagement.payload.BorrowerPayload;
import com.glints.librarymanagement.payload.ErrorResponse;
import com.glints.librarymanagement.repository.BookRepo;
import com.glints.librarymanagement.repository.BorrowerRepo;
import com.glints.librarymanagement.repository.MemberRepo;
import com.glints.librarymanagement.repository.PetugasRepo;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

	@Autowired
	BorrowerRepo borrowerRepo;

	@Autowired
	BookRepo bookRepo;

	@Autowired
	MemberRepo memberRepo;

	@Autowired
	PetugasRepo employeeRepo;

	@GetMapping(path = "/get", produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<Borrower> borrower = borrowerRepo.findAll();
		return new ResponseEntity<List<Borrower>>(borrower, HttpStatus.OK);
	}

	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Borrower borrower = borrowerRepo.findById(id).orElse(null);
		if (borrower == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrowing not found", "Data yang anda cari tidak ditemukan"),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Borrower>(borrower, HttpStatus.OK);
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createBorrow(@RequestBody BorrowerPayload payload) {

		Petugas employee = employeeRepo.findByUserNameIgnoreCase(payload.getEmployee());
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

			Borrower newBorrow = new Borrower(null, member, employee, book, payload.getBorrowDate(),
					payload.getReturnDate(), null);
			newBorrow.setIsReturned(false);
			borrowerRepo.save(newBorrow);

		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse(e.getMessage(), "Maaf request anda tidak dapat diproses"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<BorrowerPayload>(payload, HttpStatus.CREATED);
	}

}
