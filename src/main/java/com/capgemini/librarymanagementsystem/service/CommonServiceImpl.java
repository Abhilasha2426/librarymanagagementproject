package com.capgemini.librarymanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystem.dao.CommonDAO;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	private CommonDAO dao;

	@Autowired
	private BCryptPasswordEncoder encoder;

//	@Override
//	public UserInfoBean login(String email, String password) {
//		return dao.login(email, password);
//	}
	
	@Override
	public boolean register(UserInfoBean bean) {
		bean.setUserPassword(encoder.encode(bean.getUserPassword()));
		return dao.register(bean);
	}


	@Override
	public boolean changePassword(String email, String password) {
		return dao.changePassword(email, password);
	}

	@Override
	public boolean forgotPassword(String email, String password) {
		return dao.forgotPassword(email, password);
	}

	@Override
	public List<BookInfo> showAllBooks() {
		return dao.showAllBooks();
	}

	@Override
	public List<BookInfo> getByBookName(String name) {
		return dao.getByBookName(name);
	}

}
