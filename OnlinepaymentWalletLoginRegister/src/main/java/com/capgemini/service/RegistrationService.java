package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.WalletAccount;
import com.capgemini.model.WalletUserModel;
import com.capgemini.entity.WalletUser;
import com.capgemini.repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	private AddWithdrawProxyService awProxyService;

	@Autowired
	private RegistrationRepository repo;

	public WalletUser saveWalletUser(WalletUser walletUser) {
		Long accountId = walletUser.getPhoneNumber();
		// WalletAccount wAccount=new WalletAccount(accountId,0.00,null);
		// WalletAccount wAccount = null;
		// wAccount.setAccountID(accountId);
		// wAccount.setAccountBalance(0.00);
		// wAccount.setTransactionList(null);
		awProxyService.setAccount(accountId);
		return repo.save(walletUser);
	}

	public WalletUser fetchWalletUserByPhoneNumber(Long phonenumber) {
		return repo.findByPhoneNumber(phonenumber);
	}

	public WalletUserModel fetchWalletUserByPhoneNumberAndPassword(Long phoneNumberTemp, String passwordTemp) {
		// WalletUserModel wUserModel = null;
		WalletUser walletUser = repo.findByPhoneNumberAndPassword(phoneNumberTemp, passwordTemp);

		WalletAccount wAccount = awProxyService.getAccount(phoneNumberTemp);
		// wUserModel.setEmailId(walletUser.getEmailId());
		// wUserModel.setFirstName(walletUser.getFirstName());
		// wUserModel.setLastName(walletUser.getLastName());
		// wUserModel.setAccountBalance(wAccount.getAccountBalance());
		WalletUserModel wUserModel = new WalletUserModel(walletUser.getPhoneNumber(), walletUser.getEmailId(),
				walletUser.getFirstName(), walletUser.getLastName(), wAccount.getAccountBalance());
		return wUserModel;
	}

	public List<WalletUser> fetchAllUsers() {
		return repo.findAll();
	}

}
