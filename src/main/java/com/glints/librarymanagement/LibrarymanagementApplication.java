package com.glints.librarymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibrarymanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarymanagementApplication.class, args);
	}

}
