package com.cg.onlinewallet.service;

import java.time.LocalDateTime;

import com.cg.onlinewallet.entity.WalletAccount;
import com.cg.onlinewallet.entity.WalletTransactions;
import com.cg.onlinewallet.entity.WalletUser;

public interface WalletService {
	public WalletTransactions addMoney(Long userId, Double Amount);
	public WalletTransactions withdrawMoney(Long userId, Double Amount);
	//public void create(WalletUser wUser);
	public WalletTransactions createTransactionHistory(String description,LocalDateTime dateOfTransaction, Double amount,
			Double accountBalance);
	public void createAccount(WalletAccount walletAccount);
	public WalletAccount getAccount(long id);
	public WalletTransactions transferMoney(Long userId, Double amount,Long AccountId);
	
}
