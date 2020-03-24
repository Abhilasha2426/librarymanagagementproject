package com.capgemini.librarymanagementsystem.dao;

import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.Issue;

public interface UserDAO {

	public Issue requestBook(BookInfo book, int userId);
	
	public boolean borrow(int userId, String bookName);
	
}
