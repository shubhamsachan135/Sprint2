package com.cg.onlinewallet.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlinewallet.dao.WalletDao;

import com.cg.onlinewallet.entity.WalletTransactions;
import com.cg.onlinewallet.entity.WalletAccount;
import com.cg.onlinewallet.entity.WalletUser;
import com.cg.onlinewallet.exception.InvalidException;
import com.cg.onlinewallet.exception.InvalidException;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {

	WalletAccount account;
	@Autowired
	private WalletDao dao;



	@Override
	public void createAccount(WalletAccount walletAccount) {
		dao.saveAccount(walletAccount);
		
	}
	
	@Override
	public void  deleteAccount(long id) {
		dao.deleteAccount(id);
	}
	
	@Override
	public WalletAccount getAccount(long id) {
		
		return dao.getAccount(id);
	}
	   
	
	
	@Override
	public WalletTransactions addMoney(Long userId, Double Amount) {
		Long accountId = userId;

		account = dao.getAccount(accountId);
		if (account == null) {
			throw new InvalidException("Account  Not Found");
		} else {
			Double balance = account.getAccountBalance();
			balance += Amount;
			account.setAccountBalance(balance);
		}

		Double balance = account.getAccountBalance();

		WalletTransactions transaction = createTransactionHistory("Added to wallet", LocalDateTime.now(), Amount,
				balance);

		return transaction;
	}

	@Override
	public WalletTransactions withdrawMoney(Long userId, Double Amount) {
		Long accountId = userId;

		account = dao.getAccount(accountId);
		Double balance = account.getAccountBalance();
		if (balance >= Amount) {
			balance -= Amount;
			account.setAccountBalance(balance);

			balance = account.getAccountBalance();

			WalletTransactions transaction = createTransactionHistory("withdrawn from wallet", LocalDateTime.now(),
					Amount, balance);

			return transaction;
		} else {

			balance = account.getAccountBalance();

			WalletTransactions transaction = createTransactionHistory("can't withdraw,Less wallet balance",
					LocalDateTime.now(), Amount, balance);

			return transaction;

		}
	}
	
	
	public WalletTransactions transferMoney(Long userId, Double Amount,Long AccountId) {
		dao.getAccount(userId).setAccountBalance(dao.getAccount(userId).getAccountBalance()+0.00);
		WalletTransactions transaction = null ;
		Long sid=userId;
		Long rid=AccountId;
		System.out.println(sid);
		System.out.println(rid);
		
    	if((dao.getAccount(rid)!=null)&& (dao.getAccount(sid).getAccountBalance()>=Amount)) {
		 
			
		dao.getAccount(sid).setAccountBalance(dao.getAccount(sid).getAccountBalance()-Amount);
			
		dao.getAccount(rid).setAccountBalance(dao.getAccount(rid).getAccountBalance()+Amount);

		  transaction = createTransactionHistory("Transferred Successfully to "+ rid,
				LocalDateTime.now(), Amount, dao.getAccount(sid).getAccountBalance());
		      
			
		}
    	
    	else if(dao.getAccount(sid).getAccountBalance()<Amount) {
    		 transaction = createTransactionHistory("Can't Transfer,Less Wallet Balance ",
    					LocalDateTime.now(), Amount, dao.getAccount(sid).getAccountBalance());
    	}
		
    	else {
    		 transaction = createTransactionHistory("Can't Transfer,Invalid AccountId ",
 					LocalDateTime.now(), Amount, dao.getAccount(sid).getAccountBalance());
    	}
		return transaction;
		
    
		
		
	}
	
	
	
	
	

	public WalletTransactions createTransactionHistory(String description, LocalDateTime dateOfTransaction,
			Double amount, Double accountBalance) {
		WalletTransactions transaction = new WalletTransactions(description, dateOfTransaction, amount, accountBalance);
		List<WalletTransactions> transactionList = account.getTransactionList();
		if (transactionList == null)
			transactionList = new ArrayList<WalletTransactions>();
		transactionList.add(transaction);
		dao.saveTransaction(transaction);

		return transaction;

	}


}
