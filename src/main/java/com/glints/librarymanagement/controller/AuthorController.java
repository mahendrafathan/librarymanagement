package com.glints.librarymanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.glints.librarymanagement.payload.AuthorPayload;
import com.glints.librarymanagement.payload.ErrorResponse;
import com.glints.librarymanagement.repository.AuthorRepo;

@RestController
@RequestMapping(path = "/author")
public class AuthorController {
	@Autowired
	AuthorRepo authorRepo;

	@GetMapping(path = "/getall", produces = "application/json")
	public ResponseEntity<?> getAllAuthor() {
		List<Author> authors = authorRepo.findAll();
		return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
	}

	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Author author = authorRepo.findById(id).orElse(null);
		if (author == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Author not available.", "Author tidak tersedia."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createAuthor(@RequestBody AuthorPayload payload) {
		if (payload.getDateOfBirth() == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Date of Birth can't null.", "Tanggal Lahir harus diisi."),
					HttpStatus.BAD_REQUEST);
		}
		if (payload.getFirstname() == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Firstname can't null.", "Nama depan harus diisi."), HttpStatus.BAD_REQUEST);
		}
		if (payload.getPlaceOfBirth() == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Place of Birth can't null.", "Tempat lahir harus diisi."),
					HttpStatus.BAD_REQUEST);
		}
		if (payload.getEmail() == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse("Email can't null.", "Email harus diisi."),
					HttpStatus.BAD_REQUEST);
		}

		try {
			Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(payload.getDateOfBirth());
			Author author = new Author(payload.getFirstname(), payload.getSurname(), payload.getPlaceOfBirth(),
					birthDate, payload.getContact(), payload.getEmail(), payload.getAddress());
			authorRepo.save(author);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), "Request anda gagal"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AuthorPayload>(payload, HttpStatus.CREATED);
	}

	@PutMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateAuthor(@PathVariable("id") Integer id, @RequestBody AuthorPayload payload)
			throws Exception {
		Author existAuthor = authorRepo.findById(id).orElse(null);
		if (existAuthor == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse("Author not found", "Author tidak terdaftar."),
					HttpStatus.BAD_REQUEST);
		}
		if (payload.getDateOfBirth() != null) {
			Date newBirthDate = new SimpleDateFormat("dd/MM/yyyy").parse(payload.getDateOfBirth());
			existAuthor.setDateOfBirth(newBirthDate);
		}
		if (payload.getFirstname() != null) {
			existAuthor.setFirstname(payload.getFirstname());
		}
		if (payload.getSurname() != null) {
			existAuthor.setSurname(payload.getSurname());
		}
		if (payload.getPlaceOfBirth() != null) {
			existAuthor.setPlaceOfBirth(payload.getPlaceOfBirth());
		}
		if (payload.getContact() != null) {
			existAuthor.setContact(payload.getContact());
		}
		if (payload.getEmail() != null) {
			existAuthor.setEmail(payload.getEmail());
		}
		if (payload.getAddress() != null) {
			existAuthor.setAddress(payload.getAddress());
		}

		try {
			authorRepo.save(existAuthor);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), "Request anda gagal"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<AuthorPayload>(payload, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/delete/{id}", produces = "application/json")
	public ResponseEntity<?> deleteAuthor(@PathVariable("id") Integer id) {
		Author author = authorRepo.findById(id).orElse(null);
		if (author == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Author not sign, can't delete.", "Author tidak tersedia."),
					HttpStatus.NOT_FOUND);
		}
		if (author.isDeleted() == true) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse("Author has deleted.", "Author sudah dihapus."),
					HttpStatus.FORBIDDEN);
		}

		try {
			author.setDeleted(true);
			authorRepo.save(author);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), "Request anda gagal"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>("Delete author success!", HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/cancel-delete/{id}", produces = "application/json")
	public ResponseEntity<?> cancelDeleteAuthor(@PathVariable("id") Integer id) {
		Author author = authorRepo.findById(id).orElse(null);
		if (author == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Author not sign, can't delete.", "Author tidak tersedia."),
					HttpStatus.NOT_FOUND);
		}
		if (author.isDeleted() == false) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Author has active.", "Author aktif, tidak perlu mengurungkan delete kembali."),
					HttpStatus.FORBIDDEN);
		}

		try {
			author.setDeleted(false);
			authorRepo.save(author);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), "Request anda gagal"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>("Undo delete author success!", HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/delete-forever/{id}", produces = "application/json")
	public ResponseEntity<?> forceDeleteAuthor(@PathVariable("id") Integer id) {
		Author author = authorRepo.findById(id).orElse(null);
		if (author == null) {
			return new ResponseEntity<ErrorResponse>(
					new ErrorResponse("Author not sign, can't delete.", "Author tidak tersedia."),
					HttpStatus.NOT_FOUND);
		}
		if (author.isDeleted() == false) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse("Author has active.",
					"Author tidak dapat langsung melakukan penghapusan selamanya."), HttpStatus.FORBIDDEN);
		}

		authorRepo.deleteById(id);
		return new ResponseEntity<String>("Delete author success! Author deleted forever!", HttpStatus.ACCEPTED);
	}
}
