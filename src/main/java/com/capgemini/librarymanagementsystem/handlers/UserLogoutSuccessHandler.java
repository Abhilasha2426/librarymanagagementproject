package com.capgemini.librarymanagementsystem.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.capgemini.librarymanagementsystem.response.LibraryManagementSystemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		LibraryManagementSystemResponse genericResponse = new LibraryManagementSystemResponse();
		genericResponse.setStatusCode(201);
		genericResponse.setMessage("Success");
		genericResponse.setDecription("You are successfully logout");
		response.setStatus(200);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(genericResponse));
	}
	

}
