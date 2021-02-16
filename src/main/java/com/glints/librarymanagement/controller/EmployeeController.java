package com.glints.librarymanagement.controller;

import java.util.List;
import java.util.Optional;

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

import com.glints.librarymanagement.entity.User;
import com.glints.librarymanagement.payload.EmployeePayload;
import com.glints.librarymanagement.payload.ErrorResponse;
import com.glints.librarymanagement.repository.EmployeeRepo;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
	@Autowired
	EmployeeRepo employeeRepo;
	@GetMapping(path = "/getall", produces = "application/json")
	public ResponseEntity<?> getAll(){
		List <User> employee = employeeRepo.findAll();
		return new ResponseEntity<List<User>>(employee, HttpStatus.OK);		
	}
	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Optional<User> employee = employeeRepo.findById(id);
		return new ResponseEntity<Optional<User>>(employee, HttpStatus.OK);
	}
	 
	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeePayload payload) {
		User existEmployee = employeeRepo.findByUserNameIgnoreCase(payload.getUserName());
		if(existEmployee != null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Already exist",
					"Ganti dengan nama lain"),HttpStatus.BAD_REQUEST);							
		}
		
		try {
			User newEmployee = new User(
					payload.getUserName(), 
					payload.getPassword(), 
					payload.getName());
			employeeRepo.save(newEmployee);
			payload.setId(newEmployee.getId());
		} catch (Exception e){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(					
					"Error",
					"Maaf request anda tidak dapat diproses"),HttpStatus.INTERNAL_SERVER_ERROR);					
				
		}
		return new ResponseEntity<EmployeePayload>(payload, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createEmployee(@PathVariable("id") Integer id, @RequestBody EmployeePayload payload) {
		User existEmployee = employeeRepo.findById(id).orElse(null);;
		if(existEmployee == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Employee not found",
					"update data failed"),HttpStatus.BAD_REQUEST);					
						
		}
		existEmployee.setName(payload.getName());
		existEmployee.setPassword(payload.getPassword());
		existEmployee.setUserName(payload.getUserName());
		employeeRepo.save(existEmployee);
		payload.setId(existEmployee.getId());
		return new ResponseEntity<EmployeePayload>(payload, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/delete/{id}", produces="application/json")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
		employeeRepo.deleteById(id);
		return new ResponseEntity<String>("success deleted data where id: " + id, HttpStatus.OK);
	}
}
