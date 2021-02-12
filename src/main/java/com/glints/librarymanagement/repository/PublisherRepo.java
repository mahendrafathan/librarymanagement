package com.glints.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glints.librarymanagement.model.Publisher;

public interface PublisherRepo extends JpaRepository<Publisher, Integer>{
	public Publisher findById(int id);
}
