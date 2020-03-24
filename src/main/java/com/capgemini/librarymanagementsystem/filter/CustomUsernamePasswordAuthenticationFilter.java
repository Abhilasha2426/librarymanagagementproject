package com.capgemini.librarymanagementsystem.filter;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capgemini.librarymanagementsystem.dto.UserInfoBean;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	// private UserInfoBean userInfoBean;

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			try {
				UserInfoBean infoBean = getUserInfo(request);
				return infoBean.getUserPassword();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		return super.obtainPassword(request);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			try {
				UserInfoBean infoBean = getUserInfo(request);
				return infoBean.getUsername();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		return super.obtainUsername(request);
	}

	// converting json into java manually
	// private synchronized UserInfoBean getUserInfo(HttpServletRequest request)
	// throws IOException {
	// BufferedReader reader = request.getReader();
	// try {
	// ObjectMapper mapper = new ObjectMapper();
	// String json = "";
	// while (reader.ready()) {
	// json = json + reader.readLine();
	// System.err.println(json);
	// }
	// System.out.println("Done Reading");
	// System.out.println(json);
	// UserInfoBean userInfoBean = mapper.readValue(json, UserInfoBean.class);
	// return userInfoBean;
	// } finally {
	// reader.reset();
	// }
	// }

	private UserInfoBean getUserInfo(HttpServletRequest request) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		BufferedReader reader = request.getReader();
		while (reader.ready()) {
			json = json + reader.readLine();
		}
		System.out.println(json);
		UserInfoBean userInfoBean = mapper.readValue(json, UserInfoBean.class);
		reader.reset();
		return userInfoBean;
	}
}
