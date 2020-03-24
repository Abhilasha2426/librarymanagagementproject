package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "book_info")
public class BookInfo implements Serializable {

	@Id
	@GeneratedValue
	@Column
	private int bookId;
	@Column
	@Size(min = 3, max = 30, message = "User name should be greater than 3 and should be less than 20")
	@Pattern(regexp = "[a-zA-Z]*")
	private String bookName;
	@Column
	@Pattern(regexp = "[a-zA-Z]*")
	@Size(min = 3, max = 30, message = "User name should be greater than 3 and should be less than 20")
	private String bookAuthor;
	@Column
	// @Pattern(regexp="[0-9]*")
	private int noOfBooks;
	@Column
	@Pattern(regexp = "[a-zA-Z]*")
	@Size(min = 3, max = 30, message = "User name should be greater than 3 and should be less than 20")
	private String publisher;
	@Column
	private int userId;

	// @Exclude
	// @ManyToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name="bookId",referencedColumnName="userId",insertable=false,updatable=false)
	// private UserInfoBean user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "books")
	private Collection<Issue> issue;

}
