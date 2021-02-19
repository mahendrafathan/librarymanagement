package com.glints.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glints.librarymanagement.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	public Employee findByNameIgnoreCase(String name);

	public Employee findByUserNameIgnoreCase(String userName);
}
