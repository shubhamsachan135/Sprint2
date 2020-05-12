package com.cg.onlinewallet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.cg.onlinewallet.entity.WalletTransactions;
import com.cg.onlinewallet.entity.WalletAccount;
import com.cg.onlinewallet.entity.WalletUser;

@Repository
public  class WalletDao  {
	@PersistenceContext
	private EntityManager entityManager;
	
	public WalletUser getUser(String userId) {
		WalletUser user = entityManager.find(WalletUser.class, userId);
		return user;
	}

	public WalletAccount getAccount(Long accountId) {
		WalletAccount account = entityManager.find(WalletAccount.class, accountId);
		return account;
	}

	
	public void saveTransaction(WalletTransactions transaction) {
		entityManager.persist(transaction);
	}
	
	public void saveAccount(WalletAccount walletAccount) {
		entityManager.persist(walletAccount);
	}
	
	
	
	//public List<WalletTransactions> getTransactions(Integer accountIdId) {
	//	WalletTransactions transaction = entityManager.;
	//	return transaction;
	//}
	
	 public void save(WalletUser wUser) {
		 entityManager.persist(wUser);
		 entityManager.flush();
	    }
	
}