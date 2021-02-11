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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glints.librarymanagement.model.Petugas;
import com.glints.librarymanagement.payload.PetugasPayload;
import com.glints.librarymanagement.payload.ErrorResponse;
import com.glints.librarymanagement.repository.PetugasRepo;

@RestController
@RequestMapping(path = "/petugas")
public class PetugasController {
	@Autowired
	PetugasRepo petugasRepo;
	@GetMapping(path = "/getall", produces = "application/json")
	public ResponseEntity<?> getAll(){
		List <Petugas> petugas = petugasRepo.findAll();
		return new ResponseEntity<List<Petugas>>(petugas, HttpStatus.OK);		
	}
	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Optional<Petugas> petugas = petugasRepo.findById(id);
		return new ResponseEntity<Optional<Petugas>>(petugas, HttpStatus.OK);
	}
	
// @GetMapping(path = "/get/{id}")
//    public Petugas getById(@RequestParam int id) {
//        return petugasRepo.findById(id).get();
//    }
	 
	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createPetugas(@RequestBody PetugasPayload payload) {
		Petugas existPetugas = petugasRepo.findByNamaIgnoreCase(payload.getNama());
		if(existPetugas != null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Already exist",
					"Ganti dengan nama lain"),HttpStatus.BAD_REQUEST);					
						
		}
		try {
			Petugas newPetugas = new Petugas(
					payload.getNama(), 
					payload.getPassword(), 
					payload.getUserName());
			petugasRepo.save(newPetugas);
			payload.setId(newPetugas.getId());
		} catch (Exception e){
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(					
					
					"Error",
					"Maaf request anda tidak dapat diproses"),HttpStatus.INTERNAL_SERVER_ERROR);					
				
		}
		return new ResponseEntity<PetugasPayload>(payload, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createPetugas(@PathVariable("id") Integer id, @RequestBody PetugasPayload payload) {
		Petugas existPetugas = petugasRepo.findById(id).orElse(null);;
		if(existPetugas == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Petugas not found",
					"update data failed"),HttpStatus.BAD_REQUEST);					
						
		}
		existPetugas.setNama(payload.getNama());
		existPetugas.setPassword(payload.getPassword());
		existPetugas.setUserName(payload.getUserName());
		petugasRepo.save(existPetugas);
		payload.setId(existPetugas.getId());
		return new ResponseEntity<PetugasPayload>(payload, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/delete/{id}", produces="application/json")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
		petugasRepo.deleteById(id);
		return new ResponseEntity<String>("success deleted data where id: " + id, HttpStatus.OK);
	}
}
