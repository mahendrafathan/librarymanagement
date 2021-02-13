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
import com.glints.librarymanagement.model.Borrower;
import com.glints.librarymanagement.model.Employee;
import com.glints.librarymanagement.model.Member;
import com.glints.librarymanagement.payload.BorrowerPayload;
import com.glints.librarymanagement.payload.ErrorResponse;
import com.glints.librarymanagement.repository.BookRepo;
import com.glints.librarymanagement.repository.BorrowerRepo;
import com.glints.librarymanagement.repository.EmployeeRepo;
import com.glints.librarymanagement.repository.MemberRepo;

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
	EmployeeRepo employeeRepo;

	@GetMapping(path = "/get", produces = "application/json")
	public ResponseEntity<?> getAll() {
//		List<Borrower> borrower = borrowerRepo.findAll();
		List<Borrower> borrower = borrowerRepo.findAllJoin();
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

		Employee employee = employeeRepo.findByUserNameIgnoreCase(payload.getEmployee());
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

	@DeleteMapping(path = "/soft-delete/{id}", produces = "application/json")
	public ResponseEntity<?> deleteAuthor(@PathVariable("id") Long id) {
		Borrower borrower = borrowerRepo.findById(id).orElse(null);
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
			borrowerRepo.save(borrower);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), "Request anda gagal"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>("Delete borrower success!", HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/cancel-delete/{id}", produces = "application/json")
	public ResponseEntity<?> cancelDeleteAuthor(@PathVariable("id") Long id) {
		Borrower borrower = borrowerRepo.findById(id).orElse(null);
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
			borrowerRepo.save(borrower);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), "Request anda gagal"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>("Undo delete borrower success!", HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/permanent-delete/{id}", produces = "application/json")
	public ResponseEntity<?> forceDeleteAuthor(@PathVariable("id") Long id) {
		Borrower borrower = borrowerRepo.findById(id).orElse(null);
		if (borrower == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrower not found", "Data borrower tidak ditemukan."), HttpStatus.NOT_FOUND);
		}
		if (borrower.isDeleted() == false) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrower data is active.", "Data borrower tidak dapat dihapus permanent."),
					HttpStatus.FORBIDDEN);
		}

		borrowerRepo.deleteById(id);
		return new ResponseEntity<String>("Delete borrower data success! borrower deleted permanent!",
				HttpStatus.ACCEPTED);
	}

	@PutMapping(path = "/return-status/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateIsReturned(@PathVariable("id") Long id, @RequestBody BorrowerPayload payload)
			throws Exception {
		Borrower existBorrower = borrowerRepo.findById(id).orElse(null);
		if (existBorrower == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Borrower not found", "Data borrower tidak ditemukan."), HttpStatus.BAD_REQUEST);
		}
		try {

			existBorrower.setIsReturned(true);
			borrowerRepo.save(existBorrower);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), "Request anda gagal"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<BorrowerPayload>(payload, HttpStatus.ACCEPTED);
	}

}
