package com.cg.onlinewallet.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "WalletAccounts")
public class WalletAccount implements Serializable {
	
	@Id
	@Column(name = "accountID")
	private Long accountID;
	
	@Column(name = "accountBalance")
	private Double accountBalance;

	@OneToMany(cascade = CascadeType.ALL)
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
                this.accountID=accountID;
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
