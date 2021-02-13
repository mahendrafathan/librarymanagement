package com.glints.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glints.librarymanagement.model.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
	public Book findByTitleIgnoreCase(String title);

	public Book findById(int id);

}
