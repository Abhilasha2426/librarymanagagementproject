package com.capgemini.librarymanagementsystem.response;

import java.util.List;


import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.Issue;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

import lombok.Data;

@Data
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class LibraryManagementSystemResponse {
	private int statusCode;
	private String message;
	private String decription;
	private List<Issue> beans;
	public List<BookInfo> book;
	public List<UserInfoBean> users;
	
}
