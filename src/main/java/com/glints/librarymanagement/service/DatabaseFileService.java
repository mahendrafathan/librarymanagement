package com.glints.librarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.glints.librarymanagement.exception.FileNotFoundException;
import com.glints.librarymanagement.exception.FileStorageException;
import com.glints.librarymanagement.model.DatabaseFile;
import com.glints.librarymanagement.repository.DatabaseFileRepo;

@Service
public class DatabaseFileService {
	@Autowired
	private DatabaseFileRepo databaseFileRepo;
	
	public DatabaseFile storeFile(MultipartFile file) {
		// Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        
        try {
        	// Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);	
            }

            DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());
            return databaseFileRepo.save(dbFile);
		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}  
	}
	
	public DatabaseFile getFile(String fileId) {
        return databaseFileRepo.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
