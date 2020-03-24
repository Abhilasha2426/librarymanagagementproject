package com.capgemini.librarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.Issue;
import com.capgemini.librarymanagementsystem.response.LibraryManagementSystemResponse;
import com.capgemini.librarymanagementsystem.service.UserService;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*",exposedHeaders="Access-Control-Allow-Origin")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/requestBook")
	public Issue requestBook(@RequestBody BookInfo book, int userId) {

		LibraryManagementSystemResponse response = new LibraryManagementSystemResponse();
		Issue req = service.requestBook(book, userId);

		if (req != null) {
			response.setStatusCode(201);
			response.setMessage("success");
		} else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}
		return req;
	}// end of requestBook

	@PostMapping("/borrow")
	public boolean borrow(int userId, String bookName) {
		LibraryManagementSystemResponse response = new LibraryManagementSystemResponse();
		boolean issue = service.borrow(userId, bookName);

		if (issue) {
			response.setStatusCode(201);
			response.setMessage("success");
		} else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}
		return issue;
	}

}
