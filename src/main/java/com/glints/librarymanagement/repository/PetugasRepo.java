package com.glints.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glints.librarymanagement.model.Petugas;

public interface PetugasRepo extends JpaRepository<Petugas, Integer> {
	public Petugas findByNamaIgnoreCase(String nama);

	public Petugas findByUserNameIgnoreCase(String nama);
}
