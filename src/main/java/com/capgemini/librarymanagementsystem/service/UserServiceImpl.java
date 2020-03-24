package com.capgemini.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.Issue;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;

	@Override
	public Issue requestBook(BookInfo book, int userId) {
		return dao.requestBook(book, userId);
	}

	@Override
	public boolean borrow(int userId, String bookName) {
		return dao.borrow(userId, bookName);
	}

}
