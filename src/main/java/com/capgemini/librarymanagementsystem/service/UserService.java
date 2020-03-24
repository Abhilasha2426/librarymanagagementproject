package com.capgemini.librarymanagementsystem.service;

import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.Issue;

public interface UserService {

	public Issue requestBook(BookInfo book, int userId);
	
	public boolean borrow(int userId, String bookName);

}
