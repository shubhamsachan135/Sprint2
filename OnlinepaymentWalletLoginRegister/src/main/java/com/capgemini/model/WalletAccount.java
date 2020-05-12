package com.capgemini.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


public class WalletAccount implements Serializable {

	private Long accountID;
	

	private Double accountBalance;

	
	private List<WalletTransactions> transactionList;

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

	public WalletAccount(Long accountID,Double accountBalance, List<WalletTransactions> transactionList) {
		super();

		this.accountBalance = accountBalance;
		this.transactionList = transactionList;
		
	}



	public List<WalletTransactions> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<WalletTransactions> transactionList) {
		this.transactionList = transactionList;
	}

	public WalletAccount() {
		
	}

}
