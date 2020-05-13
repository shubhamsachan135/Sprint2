package com.capgemini.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.BadCredentialException;
import com.capgemini.exception.ResourceNotFoundException;
import com.capgemini.model.WalletUserModel;
import com.capgemini.entity.WalletUser;
import com.capgemini.repository.RegistrationRepository;
import com.capgemini.service.AddWithdrawProxyService;
import com.capgemini.service.RegistrationService;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
	@Autowired
	private AddWithdrawProxyService awProxyService;
	
	@Autowired
	private RegistrationService service;
	
	@Autowired
	private RegistrationRepository repo;
	
	//registration of a new wallet user
	
	@PostMapping("/register")
	@CrossOrigin(origins = "http://localhost:4200")
	public WalletUser registerUser(@RequestBody WalletUser walletUser) throws BadCredentialException {
		Long phoneNumberTemp= walletUser.getPhoneNumber();
		
		
//		if(phoneNumberTemp!=null && !"".equals(phoneNumberTemp)) {
		if(phoneNumberTemp!=null) {
		WalletUser userObj=	service.fetchWalletUserByPhoneNumber(phoneNumberTemp);
			if(userObj!=null) {
				throw new BadCredentialException("Phone Number "+phoneNumberTemp+" already registered ");
			}		
		}
		WalletUser walletuserObj=null;
		
		
		walletuserObj= service.saveWalletUser(walletUser);
		return walletuserObj; 
	}
	
	
	//login of a wallet user by his phone number and password
	
	@PostMapping(value = "/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public WalletUserModel loginUser(@RequestBody WalletUser walletUser) throws BadCredentialException {
	
	Long PhoneNumberTemp=walletUser.getPhoneNumber();
	String PasswordTemp=walletUser.getPassword();
	WalletUserModel walletuserobj=null;
	if(PhoneNumberTemp!=null && PasswordTemp!=null) {
		walletuserobj=service.fetchWalletUserByPhoneNumberAndPassword(PhoneNumberTemp, PasswordTemp);
	}
	if(walletuserobj==null) {
		throw new BadCredentialException("Bad Credentials");
	}
	return walletuserobj;
	}
	
	
	//find  one wallet user by phone number
	
	
	
			@GetMapping(value = "/walletUser/{phonenumber}")
			@CrossOrigin(origins = "http://localhost:4200")
			public ResponseEntity<WalletUser> getWalletUserById(@PathVariable(value="phonenumber") Long phonenumber )throws ResourceNotFoundException {
				WalletUser walletUser=repo.findById(phonenumber)
						.orElseThrow(() -> new ResourceNotFoundException("Wallet user not found for phone number : " + phonenumber));
				return ResponseEntity.ok().body(walletUser);
			
			}
			
	
	//getting data about all the users
			
			
	
	@GetMapping(value = "/walletUsers")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<WalletUser> getWalletUsers( WalletUser walletUser){
		return service.fetchAllUsers();
	}
	
	
	//update wallet user

		@PutMapping(value = "/updateWalletUser/{phonenumber}")
		@CrossOrigin(origins = "http://localhost:4200")
		public  ResponseEntity<WalletUser> updateWalletUser(@PathVariable(value = "phonenumber") Long phonenumber,
				@Valid @RequestBody WalletUser walletuserDetails) throws ResourceNotFoundException {
			WalletUser walletUser = repo.findById(phonenumber)
					.orElseThrow(() -> new ResourceNotFoundException("Wallet user not found for this phone number : " + phonenumber));
			
            //walletUser.setPhoneNumber(walletuserDetails.getPhoneNumber());
			walletUser.setFirstName(walletuserDetails.getFirstName());
			walletUser.setLastName(walletuserDetails.getLastName());
			walletUser.setEmailId(walletuserDetails.getEmailId());
		    //  walletUser.setPassword(walletuserDetails.getPassword());
			final WalletUser updatedWalletUser = repo.save(walletUser);
			return ResponseEntity.ok(updatedWalletUser);
		}
		
	//delete wallet users based on phone number 
		
		
	@DeleteMapping(value = "/deleteWalletUser/{phonenumber}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Map<String, Boolean> deleteWalletUser(@PathVariable(value = "phonenumber") Long phonenumber)
			throws ResourceNotFoundException {
		WalletUser walletUser = repo.findById(phonenumber)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + phonenumber));
		
		awProxyService.deleteAccount(phonenumber);
		repo.delete(walletUser);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
	
	

}
