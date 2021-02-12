package com.glints.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glints.librarymanagement.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
	Author findByFirstnameIgnoreCase(String name);
}
