package com.glints.librarymanagement.Repository;


import com.glints.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepo extends JpaRepository<Book, Integer> {
}
