package com.capgemini.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table(name ="walletusers")
public class WalletUser {
	@Id
	private Long phoneNumber;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "password")
	private String password;
	
	
	
	
	public WalletUser() {
		
	}
	public WalletUser(Long phoneNumber, String firstName, String lastName, String emailId, String password) {
		super();
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
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