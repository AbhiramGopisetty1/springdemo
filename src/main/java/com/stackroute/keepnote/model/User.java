package com.stackroute.keepnote.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	private String userName;
	private String userPassword;
	private String userMobile;
	private Date userAddedDate;
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public User(String userId, String userName, String userPassword, String userMobile, Date userAddedDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userMobile = userMobile;
		this.userAddedDate = userAddedDate;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserPassword() {
		return userPassword;
	}



	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}



	public String getUserMobile() {
		return userMobile;
	}



	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}



	public Date getUserAddedDate() {
		return userAddedDate;
	}



	public void setUserAddedDate(Date userAddedDate) {
		this.userAddedDate = userAddedDate;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userMobile="
				+ userMobile + ", userAddedDate=" + userAddedDate + "]";
	}



	
}
