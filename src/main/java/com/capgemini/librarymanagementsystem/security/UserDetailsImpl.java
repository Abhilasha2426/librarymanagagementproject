package com.capgemini.librarymanagementsystem.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.capgemini.librarymanagementsystem.dto.UserInfoBean;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Component
public class UserDetailsImpl implements UserDetails {

	private UserInfoBean userInfoBean;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userInfoBean.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		return userInfoBean.getUserPassword();
	}

	@Override
	public String getUsername() {
		return userInfoBean.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
