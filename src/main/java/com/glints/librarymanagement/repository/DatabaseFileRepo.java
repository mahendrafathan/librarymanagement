package com.glints.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.glints.librarymanagement.model.DatabaseFile;

public interface DatabaseFileRepo extends JpaRepository<DatabaseFile, String>{

}
