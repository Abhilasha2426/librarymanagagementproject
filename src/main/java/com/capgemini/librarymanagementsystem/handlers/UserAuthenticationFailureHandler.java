package com.capgemini.librarymanagementsystem.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.capgemini.librarymanagementsystem.response.LibraryManagementSystemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authentication)
			throws IOException, ServletException {
		LibraryManagementSystemResponse genericResponse = new LibraryManagementSystemResponse();
		genericResponse.setStatusCode(410);
		genericResponse.setMessage("Failure");
		genericResponse.setDecription("Login failure");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(200);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(genericResponse));
		
	}

}
