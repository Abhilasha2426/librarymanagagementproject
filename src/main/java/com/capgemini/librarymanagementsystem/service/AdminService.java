package com.capgemini.librarymanagementsystem.service;

import java.util.Date;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.Issue;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

public interface AdminService {

	// public boolean addBook(UserInfoBean bean);

	public boolean addBook(int userId,BookInfo bean);

	public boolean addUser(UserInfoBean userInfoBean);

	public boolean deleteUser(int userId);

	public boolean deleteBook(int bookId);

	public boolean updateUser(UserInfoBean userInfoBean);

	public List<UserInfoBean> showAllUser();

	public List<UserInfoBean> getByUserId(int userId);

	public Issue addFine(int issueId, Date returnDate);

	// public Issue acceptRequest(int issueId);

	public UserInfoBean getUser(String userName);

}
