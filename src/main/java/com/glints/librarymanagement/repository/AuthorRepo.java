package com.glints.librarymanagement.repository;

import com.glints.librarymanagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
    List<Author> findByIsDeleted(boolean isDeleted);
}
