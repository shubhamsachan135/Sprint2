package com.cg.onlinewallet.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "WalletAccount")
public class WalletAccount implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tran_seq")
	
	@Column(name = "accountID")
	private Integer accountID;
	
	@Column(name = "accountBalance")
	private Double accountBalance;

	@OneToMany(cascade = CascadeType.ALL)
	private List<WalletTransactions> transactionList;

	public int getAccountID() {
		return accountID;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public WalletAccount(int accountID,Double accountBalance, List<WalletTransactions> transactionList) {
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
