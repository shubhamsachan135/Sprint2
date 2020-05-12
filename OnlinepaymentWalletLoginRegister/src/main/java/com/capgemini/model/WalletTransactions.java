package com.capgemini.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "WalletTransactions")
public class WalletTransactions implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tran_seq")
	@Column(name = "transactionID")
	private Integer transactionID;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "dateOfTransaction")
	private LocalDateTime dateOfTransaction;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "accountBalance")
	private Double accountBalance;
    
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}
	
	public Integer getTransactionID() {
		return transactionID;
	}

	public LocalDateTime getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public WalletTransactions(String description,LocalDateTime dateOfTransaction, Double amount,
			Double accountBalance) {
		super();
        this.description=description;
		this.dateOfTransaction = dateOfTransaction;
		this.amount = amount;
		this.accountBalance = accountBalance;
	}

	public WalletTransactions() {
		// TODO Auto-generated constructor stub
	}

}
