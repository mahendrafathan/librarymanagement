package com.glints.librarymanagement.controller;

import com.glints.librarymanagement.model.Author;
import com.glints.librarymanagement.payload.AuthorPayload;
import com.glints.librarymanagement.payload.ErrorResponse;
import com.glints.librarymanagement.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(
                    "Author not available.",
                    "Author tidak tersedia."
            ), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorPayload payload) {
        if (payload.getDateOfBirth() == null)  {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(
                    "Date of Birth can't null.",
                    "Tanggal Lahir harus diisi."
            ), HttpStatus.BAD_REQUEST);
        }
        if (payload.getFirstname() == null)  {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(
                    "Firstname can't null.",
                    "Nama depan harus diisi."
            ), HttpStatus.BAD_REQUEST);
        }
        if (payload.getPlaceOfBirth() == null)  {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(
                    "Place of Birth can't null.",
                    "Tempat lahir harus diisi."
            ), HttpStatus.BAD_REQUEST);
        }
        if (payload.getEmail() == null)  {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(
                    "Email can't null.",
                    "Email harus diisi."
            ), HttpStatus.BAD_REQUEST);
        }

        try {
            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(payload.getDateOfBirth());
            Author author = new Author(
                    payload.getFirstname(),
                    payload.getSurname(),
                    payload.getPlaceOfBirth(),
                    birthDate,
                    payload.getContact(),
                    payload.getEmail(),
                    payload.getAddress()
            );
            authorRepo.save(author);
        } catch (Exception e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(
                    e.getMessage(),
                    "Request anda gagal"
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AuthorPayload>(payload, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateAuthor(@PathVariable("id") Integer id, @RequestBody AuthorPayload payload) throws Exception {
        Author existAuthor = authorRepo.findById(id).orElse(null);
        if (existAuthor == null)  {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(
                    "Author not found",
                    "Author tidak terdaftar."
            ), HttpStatus.BAD_REQUEST);
        }
        if (payload.getDateOfBirth() != null) {
            Date newBirthDate = new SimpleDateFormat("dd/MM/yyyy").parse(payload.getDateOfBirth());
            existAuthor.setDateOfBirth(newBirthDate);
        }

        try {
            existAuthor.setFirstname(payload.getFirstname());
            existAuthor.setSurname(payload.getSurname());
            existAuthor.setPlaceOfBirth(payload.getPlaceOfBirth());
            existAuthor.setContact(payload.getContact());
            existAuthor.setEmail(payload.getEmail());
            existAuthor.setAddress(payload.getAddress());
            authorRepo.save(existAuthor);
        } catch (Exception e) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(
                    e.getMessage(),
                    "Request anda gagal"
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AuthorPayload>(payload, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") Integer id) {
        Author author = authorRepo.findById(id).orElse(null);
        if (author == null) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(
                    "Author not sign, can't delete.",
                    "Author tidak tersedia."
            ), HttpStatus.NOT_FOUND);
        }
        authorRepo.deleteById(id);
        return new ResponseEntity<String>("Delete author success!", HttpStatus.OK);
    }
}
