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
public class WalletServiceImpl implements WalletService{
	
	
	WalletAccount account;
	@Autowired
	private WalletDao dao;
	
	@Transactional(propagation=Propagation.REQUIRED)
    public void create(WalletUser wUser) {
        dao.save(wUser);
    }
	
	
	@Override
	public WalletTransactions addMoney(String userId, Double Amount) {
		WalletUser user = dao.getUser(userId);
		if(user==null)
		    throw new InvalidException("User with this user Id  Not Found");
		else {
		int accountId = user.getAccountDetail().getAccountID();
		 account = dao.getAccount(accountId);
	         if(account==null) {
			        throw new InvalidException("Account  Not Found");
	       	 }
		    else {
	             	Double balance = account.getAccountBalance();
	              	balance += Amount;
	             	account.setAccountBalance(balance);
		       }
		
		}
		
		
         Double balance= account.getAccountBalance();
    
      // WalletTransactions transaction = new WalletTransactions("Added to wallet", LocalDateTime.now(), Amount, balance);
  	  // List<WalletTransactions> transactionList = account.getTransactionList();
  	  //  if (transactionList == null)
  	//	      transactionList = new ArrayList<WalletTransactions>();
    //   transactionList.add(transaction);
  	//	dao.saveTransaction(transaction);
       
         
      WalletTransactions transaction=createTransactionHistory("Added to wallet", LocalDateTime.now(), Amount, balance);
         
  	   return transaction;
	}

	@Override
	public WalletTransactions withdrawMoney(String userId, Double Amount) {
		WalletUser user = dao.getUser(userId);
		if(user==null)
		    throw new InvalidException("User with this user Id  Not Found");
		else {
		int accountId = user.getAccountDetail().getAccountID();
		 account = dao.getAccount(accountId);
	         if(account==null) {
			        throw new InvalidException("Account  Not Found");
	       	 }
		    else {
	             	Double balance = account.getAccountBalance();
	              	balance -= Amount;
	             	account.setAccountBalance(balance);
		       }
		
		}
		
		
         Double balance= account.getAccountBalance();
    
      // WalletTransactions transaction = new WalletTransactions("Added to wallet", LocalDateTime.now(), Amount, balance);
  	  // List<WalletTransactions> transactionList = account.getTransactionList();
  	  //  if (transactionList == null)
  	//	      transactionList = new ArrayList<WalletTransactions>();
    //   transactionList.add(transaction);
  	//	dao.saveTransaction(transaction);
       
         
      WalletTransactions transaction=createTransactionHistory("withdrawn from wallet", LocalDateTime.now(), Amount, balance);
         
  	   return transaction;
	}

	 
	   
	   public WalletTransactions createTransactionHistory(String description,LocalDateTime dateOfTransaction, Double amount,
				Double accountBalance) {
		   WalletTransactions transaction = new WalletTransactions(description,dateOfTransaction, amount, accountBalance);
	  	   List<WalletTransactions> transactionList = account.getTransactionList();
	  	    if (transactionList == null)
	  		      transactionList = new ArrayList<WalletTransactions>();
	  		transactionList.add(transaction);
	  		dao.saveTransaction(transaction);
	         
	  		 return transaction;
		   
		   
	   }
	   
	
}
