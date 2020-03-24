package com.capgemini.librarymanagementsystem.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.Issue;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;
import com.capgemini.librarymanagementsystem.response.LibraryManagementSystemResponse;
import com.capgemini.librarymanagementsystem.service.AdminService;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*",exposedHeaders="Access-Control-Allow-Origin")
public class AdminController {

	@Autowired
	private AdminService service;

	@PostMapping(path = "/addBook/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LibraryManagementSystemResponse addBook(@PathVariable(value = "userId") int userId,
			@RequestBody BookInfo bookInfo) {
		LibraryManagementSystemResponse resp = new LibraryManagementSystemResponse();
		if (service.addBook(userId, bookInfo)) {
			resp.setStatusCode(201);
			resp.setMessage("Successfull");
			resp.setDecription("Book added successfully into DB");
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Unsuccessfull");
			resp.setDecription("Inserting book has failed");
		}
		return resp;
	}

	@PostMapping(path = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LibraryManagementSystemResponse addUser(@RequestBody UserInfoBean userInfoBean) {
		LibraryManagementSystemResponse resp = new LibraryManagementSystemResponse();
		if (service.addUser(userInfoBean)) {
			resp.setStatusCode(201);
			resp.setMessage("Successfull");
			resp.setDecription("User added successfully into DB");
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Unsuccessfull");
			resp.setDecription("Inserting user has failed");
		}
		return resp;
	}

	@DeleteMapping(path = "/deleteBook/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryManagementSystemResponse deleteBookById(@PathVariable("bookId") int bookId) {
		LibraryManagementSystemResponse response = new LibraryManagementSystemResponse();
		if (service.deleteBook(bookId)) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDecription("Deleted for the requested book Id");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDecription("Book Id not found");
		}
		return response;

	}

	@DeleteMapping(path = "/deleteUser/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryManagementSystemResponse deleteUserById(@PathVariable("userId") int userId) {
		LibraryManagementSystemResponse response = new LibraryManagementSystemResponse();
		if (service.deleteUser(userId)) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDecription("Deleted for the requested user Id");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDecription("User Id not found");
		}
		return response;

	}

	@PostMapping(path = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LibraryManagementSystemResponse update(@RequestBody UserInfoBean userInfoBean) {
		LibraryManagementSystemResponse resp = new LibraryManagementSystemResponse();
		if (service.updateUser(userInfoBean)) {
			resp.setStatusCode(201);
			resp.setMessage("Successfull");
			resp.setDecription("Updation Successful");
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Unsuccessfull");
			resp.setDecription("Updation Unsuccessful");
		}
		return resp;
	}

	@GetMapping(path = "/getallUsers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LibraryManagementSystemResponse getallUsers() {
		LibraryManagementSystemResponse resp = new LibraryManagementSystemResponse();
		List<UserInfoBean> users = service.showAllUser();
		if (users != null) {
			resp.setStatusCode(201);
			resp.setMessage("Successfull");
			resp.setDecription("Users Found in DB");
			resp.setUsers(users);
			;
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Unsuccessfull");
			resp.setDecription("Users not found");
		}
		return resp;
	}

	@GetMapping(path = "/getbyId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryManagementSystemResponse getById(@PathVariable("userId") int userId) {
		LibraryManagementSystemResponse resp = new LibraryManagementSystemResponse();
		List<UserInfoBean> bean = service.getByUserId(userId);
		if (bean != null) {
			resp.setStatusCode(201);
			resp.setMessage("Successfull");
			resp.setDecription("User present");
			resp.setUsers(bean);
		} else {
			resp.setStatusCode(401);
			resp.setMessage("Unsuccessfull");
			resp.setDecription("User not present");
		}
		return resp;
	}

	@PostMapping("/addFine")
	public Issue addFine(@RequestBody int issueId, Date returnDate, ModelMap map) {
		LibraryManagementSystemResponse response = new LibraryManagementSystemResponse();
		Issue fine = service.addFine(issueId, returnDate);
		if (fine != null) {
			response.setStatusCode(201);
			response.setMessage("success");
		} else {
			response.setStatusCode(404);
			response.setMessage("failed");
		}
		return fine;

	}

	// @PostMapping("/acceptRequest")
	// public Issue acceptRequest(@RequestBody int issueId, ModelMap map) {
	// LibraryManagementSystemResponse response = new
	// LibraryManagementSystemResponse();
	// Issue req = service.acceptRequest(issueId);
	// if (req != null) {
	// response.setStatusCode(201);
	// response.setMessage("success");
	// } else {
	// response.setStatusCode(404);
	// response.setMessage("failed");
	// }
	// return req;
	//
	// }

}
