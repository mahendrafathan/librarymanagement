package com.glints.librarymanagement.Repository;

import com.glints.librarymanagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
    Author findByAuthorNameIgnoreCase(String name);
}
