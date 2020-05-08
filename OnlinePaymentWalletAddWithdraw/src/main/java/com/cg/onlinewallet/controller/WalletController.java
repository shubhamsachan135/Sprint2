package com.cg.onlinewallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinewallet.entity.WalletAccount;
import com.cg.onlinewallet.entity.WalletTransactions;
import com.cg.onlinewallet.entity.WalletUser;
import com.cg.onlinewallet.service.WalletService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="http://localhost:4200")

public class WalletController {

	
	@Autowired
	private WalletService service;
	
	
	
	@GetMapping("/create")
	 public String createSample(){
			WalletUser wUser1=new WalletUser("shubham135@gmail.com","shubhamsachan","shubham123","7985039931", new WalletAccount(0,0.0,null));
			 
			 WalletUser wUser2=new WalletUser("rahulkumar@gmail.com","rahulkumar","rahul123","7987839931", new WalletAccount(0,0.0,null));
			
			  service.create(wUser1);
		        service.create(wUser2);
			 
			return "Two user registered"; 
		  }


	@GetMapping("/wallet/add/{userId}/{amount}")
	public ResponseEntity<WalletTransactions> addMoney(@PathVariable("userId") String userId,@PathVariable("amount") Double amount) {
		
		WalletTransactions wTransaction = (WalletTransactions) service.addMoney(userId, amount);
		return new ResponseEntity<WalletTransactions>(wTransaction, HttpStatus.OK);
	}
	
	@GetMapping("/wallet/withdraw/{userId}/{amount}")
	public ResponseEntity<WalletTransactions> withdrawMoney(@PathVariable("userId") String userId,@PathVariable("amount") Double amount) {
		WalletTransactions wTransaction = (WalletTransactions)  service.withdrawMoney(userId, amount);
		return new ResponseEntity<WalletTransactions>(wTransaction, HttpStatus.OK);
	}
	
	
	
	
}
