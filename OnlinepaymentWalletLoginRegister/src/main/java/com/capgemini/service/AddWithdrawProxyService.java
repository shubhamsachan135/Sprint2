package com.capgemini.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capgemini.model.WalletAccount;

@FeignClient(name="add-withdraw-micro-service")
@RibbonClient(name="add-withdraw-micro-service")
public interface AddWithdrawProxyService {

      @PostMapping("/wallet/{id}")
	   public void setAccount(@PathVariable(name="id") long id);
	  
      
      @GetMapping("/wallet/{id}")
      public WalletAccount getAccount(@PathVariable(name="id") long id);
	
}
