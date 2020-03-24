package com.capgemini.librarymanagementsystem.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.Issue;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;

	@Override
	public boolean addUser(UserInfoBean userInfoBean) {
		return dao.addUser(userInfoBean);
	}

	@Override
	public boolean deleteUser(int userId) {
		return dao.deleteUser(userId);
	}

	@Override
	public boolean deleteBook(int bookId) {
		return dao.deleteBook(bookId);
	}

	@Override
	public boolean updateUser(UserInfoBean userInfoBean) {
		return dao.updateUser(userInfoBean);
	}

	@Override
	public List<UserInfoBean> showAllUser() {
		return dao.showAllUser();
	}

	@Override
	public List<UserInfoBean> getByUserId(int userId) {
		return dao.getByUserId(userId);
	}

	@Override
	public Issue addFine(int issueId, Date returnDate) {
		return dao.addFine(issueId, returnDate);
	}
	//
	// @Override
	// public Issue acceptRequest(int issueId) {
	// return dao.acceptRequest(issueId);
	// }

	@Override
	public UserInfoBean getUser(String userName) {
		return dao.getUser(userName);
	}

	@Override
	public boolean addBook(int userId, BookInfo bean) {
		return dao.addBook(userId, bean);
	}

}
