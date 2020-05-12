package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entity.WalletUser;


public  interface RegistrationRepository extends JpaRepository<WalletUser, Long>{
	
	public WalletUser findByPhoneNumber(Long phonenumber);
	public WalletUser findByPhoneNumberAndPassword(Long phonenumber,String password);
	//public WalletUser save(WalletUser walletUser);
    
	

}
