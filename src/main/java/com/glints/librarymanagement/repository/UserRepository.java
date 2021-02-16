package com.glints.librarymanagement.repository;

import com.glints.librarymanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);

	User findByUserNameIgnoreCase(String employee);
}
