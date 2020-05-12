package com.cg.onlinewallet.entity;

import java.io.Serializable;

import javax.persistence.*;

public class WalletUser implements Serializable {
	
	private String userID;
	private String userName;
	private String password;
	private String phoneNumber;
	

	
	

	public String getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public WalletUser(String userId,String userName, String password, String phoneNumber) {
		super();
		this.userID= userId;
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
	
	}

	

	public WalletUser() {
		// TODO Auto-generated constructor stub
	}

}
