package com.glints.librarymanagement.repository;

import com.glints.librarymanagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
