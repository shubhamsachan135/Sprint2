package com.cg.onlinewallet.service;

import java.time.LocalDateTime;

import com.cg.onlinewallet.entity.WalletTransactions;
import com.cg.onlinewallet.entity.WalletUser;

public interface WalletService {
	public WalletTransactions addMoney(String userId, Double Amount);
	public WalletTransactions withdrawMoney(String userId, Double Amount);
	public void create(WalletUser wUser);
	public WalletTransactions createTransactionHistory(String description,LocalDateTime dateOfTransaction, Double amount,
			Double accountBalance);
	
}
