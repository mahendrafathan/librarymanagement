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

import com.glints.librarymanagement.model.Member;
import com.glints.librarymanagement.payload.ErrorResponse;
import com.glints.librarymanagement.payload.MemberPayload;
import com.glints.librarymanagement.repository.MemberRepo;


@RestController
@RequestMapping(path = "/member")
public class MemberController {
	@Autowired
	MemberRepo memberRepo;
	
	@PostMapping(path = "/create", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> createMember(@RequestBody MemberPayload payload) {
		
		try {
			Member newMember = new Member(payload.getName(), payload.getGender(), payload.getContact(), payload.getAddress());
			memberRepo.save(newMember);
		}catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getLocalizedMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<MemberPayload>(payload, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Member member = memberRepo.findById(id).orElse(null);
		
		try {
			if(member == null) {
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(
						"ID is null", 
						"ID yang dimasukkan tidak tersedia"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll", produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<Member> members = memberRepo.findAll();
		return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
	}

	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateMember(@PathVariable("id") Integer id, @RequestBody MemberPayload payload) {
		
		Member existMember = memberRepo.findById(id).orElse(null);
		if(existMember == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Member not found", 
					"ID tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		existMember.setName(payload.getName());
		existMember.setGender(payload.getGender());
		existMember.setContact(payload.getContact());
		existMember.setAddress(payload.getAddress());

		memberRepo.save(existMember);
		return new ResponseEntity<Member>(existMember, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable("id") Integer id) {
		memberRepo.deleteById(id);
		return new ResponseEntity<String>("Member with id : " +id+ " has been deleted!", HttpStatus.OK);
	}
}
