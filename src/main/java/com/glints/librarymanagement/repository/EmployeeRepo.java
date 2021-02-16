package com.glints.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glints.librarymanagement.entity.User;

public interface EmployeeRepo extends JpaRepository<User, Integer> {
	public User findByNameIgnoreCase(String name);

	public User findByUserNameIgnoreCase(String userName);
}
