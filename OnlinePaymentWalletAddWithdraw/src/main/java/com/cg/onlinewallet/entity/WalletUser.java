package com.cg.onlinewallet.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "WalletUser")
public class WalletUser implements Serializable {
	@Id
	private String userID;
	private String userName;
	private String password;
	private String phoneNumber;
	

	@OneToOne(cascade = CascadeType.ALL)
	WalletAccount accountDetail;

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



	public WalletUser(String userId,String userName, String password, String phoneNumber, 
			WalletAccount accountDetail) {
		super();
		this.userID= userId;
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.accountDetail = accountDetail;
	}

	

	public WalletAccount getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(WalletAccount accountDetail) {
		this.accountDetail = accountDetail;
	}

	public WalletUser() {
		// TODO Auto-generated constructor stub
	}

}
