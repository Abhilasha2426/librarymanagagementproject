package com.capgemini.librarymanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capgemini.librarymanagementsystem.filter.CustomUsernamePasswordAuthenticationFilter;
import com.capgemini.librarymanagementsystem.handlers.UserLogoutSuccessHandler;
import com.capgemini.librarymanagementsystem.security.LibraryManagementSystemAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class LibraryManagementSystemSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private LibraryManagementSystemAuthenticationEntryPoint libraryAuthenticationEntryPoint;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private UserLogoutSuccessHandler userLogoutSuccessHandler;
	
	@Bean
	public UsernamePasswordAuthenticationFilter getUsernamePasswordAuthenticationFilter() throws Exception {
		CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		filter.setAuthenticationFailureHandler(authenticationFailureHandler);
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint(libraryAuthenticationEntryPoint)
//		.and()
//		.authorizeRequests()
//		.antMatchers("/login").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/forgot-password").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/change-password").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/getallBooks").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/getbyname").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/register").permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/addBook").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/addUser").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/deleteBook").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/deleteUser").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/updateUser").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/getAllUsers").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/getbyId").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/addFine").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/acceptRequest").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/requestBook").hasRole("USER")
		.and()
		.addFilterBefore(getUsernamePasswordAuthenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class)
		.logout()
		.logoutSuccessHandler(userLogoutSuccessHandler);
	}
}
