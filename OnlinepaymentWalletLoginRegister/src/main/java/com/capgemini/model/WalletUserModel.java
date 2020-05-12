package com.capgemini.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


public class WalletUserModel {

	private Long phoneNumber;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private String password;
	
    private Long accountID;

	private Double accountBalance;



	public Long getAccountID() {
		return accountID;
	}
	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public WalletUserModel() {
		
	}
	public WalletUserModel(Long phoneNumber, String emailId, String firstName, String lastName, Double accountBalance) {
		super();
		this.phoneNumber=phoneNumber;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountBalance=accountBalance;
		
	}
	@Override
	public String toString() {
		return "WalletUser [phoneNumber=" + phoneNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", password=" + password + "]";
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}