package com.glints.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glints.librarymanagement.model.Publisher;
import com.glints.librarymanagement.payload.ErrorResponse;
import com.glints.librarymanagement.payload.PublisherPayload;
import com.glints.librarymanagement.repository.PublisherRepo;


@RestController
@RequestMapping(path = "/publisher")
public class PublisherController {
	@Autowired
	PublisherRepo publisherRepo;
	
	@PostMapping(path = "/create", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> createPublisher(@RequestBody PublisherPayload payload) {
		
		try {
			Publisher newPublisher = new Publisher(payload.getName(), payload.getAddress(), payload.getContact(), payload.getEmail());
			publisherRepo.save(newPublisher);
		}catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getLocalizedMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<PublisherPayload>(payload, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Publisher publisher = publisherRepo.findById(id).orElse(null);
		
		try {
			if(publisher == null) {
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(
						"ID is null", 
						"ID yang dimasukkan tidak tersedia"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Publisher>(publisher, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll", produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<Publisher> publisher = publisherRepo.findAll();
		return new ResponseEntity<List<Publisher>>(publisher, HttpStatus.OK);
	}

	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updatePublisher(@PathVariable("id") Integer id, @RequestBody PublisherPayload payload) {
		
		Publisher existPublisher = publisherRepo.findById(id).orElse(null);
		if(existPublisher == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Member not found", 
					"ID tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		existPublisher.setName(payload.getName());
		existPublisher.setAddress(payload.getAddress());
		existPublisher.setContact(payload.getContact());
		existPublisher.setEmail(payload.getEmail());

		publisherRepo.save(existPublisher);
		return new ResponseEntity<Publisher>(existPublisher, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deletePublisher(@PathVariable("id") Integer id) {
		publisherRepo.deleteById(id);
		return new ResponseEntity<String>("Publisher with id : " +id+ " has been deleted!", HttpStatus.OK);
	}
}
