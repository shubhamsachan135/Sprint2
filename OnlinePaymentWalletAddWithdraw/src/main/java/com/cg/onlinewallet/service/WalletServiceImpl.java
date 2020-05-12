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
	
	
	public WalletTransactions transferMoney(Long userId, Double amount,Long AccountId) {
		Long senderAccountId=userId;
		Long recieverAccountId=AccountId;
		WalletAccount account1 = dao.getAccount(senderAccountId);
		WalletAccount account2 = dao.getAccount(recieverAccountId);
		Double balance1 = account1.getAccountBalance();
		Double balance2 = account2.getAccountBalance();
		if((account2!=null)&& (balance1>=amount)) {
		   balance2 += amount;
		   account2.setAccountBalance(balance2);
		   balance1 -= amount;
		   account1.setAccountBalance(balance1);

		   Double balance = account1.getAccountBalance();

			WalletTransactions transaction = createTransactionHistory("Transferred Successfully to "+recieverAccountId,
					LocalDateTime.now(), amount, balance);

			return transaction;
			
		}
		else if(balance1<amount) {
			 Double balance = account1.getAccountBalance();

				WalletTransactions transaction = createTransactionHistory("Can't Transfer,Less Wallet Balance",
						LocalDateTime.now(), amount, balance);

				return transaction;
		}
		else {
			Double balance = account1.getAccountBalance();

			WalletTransactions transaction = createTransactionHistory("Can't Transfer,"+recieverAccountId+"is Not a registered AccountId",
					LocalDateTime.now(), amount, balance);

			return transaction;	
			
		}
		
		
		
		

		
		
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
