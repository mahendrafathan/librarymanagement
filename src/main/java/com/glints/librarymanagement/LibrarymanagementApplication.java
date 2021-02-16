package com.glints.librarymanagement;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//import com.glints.librarymanagement.entity.User;
import com.glints.librarymanagement.entity.User;
import com.glints.librarymanagement.repository.EmployeeRepo;
//import com.glints.librarymanagement.repository.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
public class LibrarymanagementApplication {
//	@Autowired
//    private EmployeeRepo repository;
//    @PostConstruct
//    public void initUsers() {
//    	List <Employee> users = repository.findAll();
//        repository.saveAll(users);
//    }
	public static void main(String[] args) {
		SpringApplication.run(LibrarymanagementApplication.class, args);
		System.out.println("LibraryManagementApplication started!");
	}

}
