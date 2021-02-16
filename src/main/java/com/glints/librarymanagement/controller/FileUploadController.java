package com.glints.librarymanagement.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.glints.librarymanagement.model.DatabaseFile;
import com.glints.librarymanagement.payload.Response;
import com.glints.librarymanagement.service.DatabaseFileService;

@RestController
public class FileUploadController {

	@Autowired
	private DatabaseFileService fileStrorageService;
	
	@GetMapping("/testaja")
	public String testAja() {
		return "You already in fileUploadController end point";
	}

	@PostMapping("/uploadFile")
	public Response uploadFile(@RequestParam("file") MultipartFile file) {
		DatabaseFile fileName = fileStrorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName.getFileName()).toUriString();
		
		return new Response(fileName.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadMultipleFiles")
    public List < Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
    }
}
