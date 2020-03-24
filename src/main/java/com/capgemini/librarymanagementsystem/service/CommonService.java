package com.capgemini.librarymanagementsystem.service;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

public interface CommonService {

//	public UserInfoBean login(String email, String password);
	public boolean register(UserInfoBean bean);

	public boolean changePassword(String email, String password);

	public boolean forgotPassword(String email, String password);

	public List<BookInfo> showAllBooks();

	public List<BookInfo> getByBookName(String name);

}
