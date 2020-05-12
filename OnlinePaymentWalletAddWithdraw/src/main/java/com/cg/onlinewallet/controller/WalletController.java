package com.cg.onlinewallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinewallet.entity.WalletAccount;
import com.cg.onlinewallet.entity.WalletTransactions;
import com.cg.onlinewallet.entity.WalletUser;
import com.cg.onlinewallet.service.WalletService;

@RestController
@RequestMapping("/wallet")
@CrossOrigin(origins="http://localhost:4200")

public class WalletController {

	
	@Autowired
	private WalletService service;
	
	
	@PostMapping("/{id}")
	 public void setAccount(@PathVariable(name="id") long id) {
		WalletAccount wAccount=new WalletAccount(id,0.00,null);
		//wAccount.setAccountID(walletAccount.getAccountID());
		//wAccount.setAccountBalance(walletAccount.getAccountBalance());
		//wAccount.setTransactionList(walletAccount.getTransactionList());
		service.createAccount( wAccount);
		
	}
	
	  @GetMapping("/{id}")
      public WalletAccount getAccount(@PathVariable(name="id") long id) {
		  
		return service.getAccount(id);
		  
	  }
	

	@GetMapping("/add/{userId}/{amount}")
	public ResponseEntity<WalletTransactions> addMoney(@PathVariable("userId") Long userId,@PathVariable("amount") Double amount) {
		
		WalletTransactions wTransaction = (WalletTransactions) service.addMoney(userId, amount);
		return new ResponseEntity<WalletTransactions>(wTransaction, HttpStatus.OK);
	}
	
	@GetMapping("/withdraw/{userId}/{amount}")
	public ResponseEntity<WalletTransactions> withdrawMoney(@PathVariable("userId") Long userId,@PathVariable("amount") Double amount) {
		WalletTransactions wTransaction = (WalletTransactions)  service.withdrawMoney(userId, amount);
		return new ResponseEntity<WalletTransactions>(wTransaction, HttpStatus.OK);
	}
	

	@GetMapping("/transfer/{userId}/{amount}/{accountId}")
	public ResponseEntity<WalletTransactions> transferwMoney(@PathVariable("userId") Long userId,@PathVariable("amount") Double amount,@PathVariable("accountId") Long accountId) {
		WalletTransactions wTransaction = (WalletTransactions)  service.transferMoney(userId, amount,accountId);
		return new ResponseEntity<WalletTransactions>(wTransaction, HttpStatus.OK);
	}
	
	
}
