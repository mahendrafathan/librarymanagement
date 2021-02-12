package com.glints.librarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.glints.librarymanagement.model.Borrower;

public interface BorrowerRepo extends JpaRepository<Borrower, Long> {
	@Query(value = "SELECT * FROM borrowers LEFT JOIN employees ON employees.id=borrowers.employee_id LEFT JOIN books  ON books.book_id = borrowers.book_id LEFT JOIN members  ON members.id = borrowers.member_id;", nativeQuery = true)
	List<Borrower> findAllJoin();
}
