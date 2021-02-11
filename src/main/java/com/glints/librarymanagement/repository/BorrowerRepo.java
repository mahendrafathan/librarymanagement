package com.glints.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glints.librarymanagement.model.Borrower;

public interface BorrowerRepo extends JpaRepository<Borrower, String> {

}
