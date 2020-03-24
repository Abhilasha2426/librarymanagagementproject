package com.capgemini.librarymanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.security.UserDetailsImpl;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AdminDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
		userDetailsImpl.setUserInfoBean(dao.getUser(username));
		return userDetailsImpl;
	}

}
