package com.cg.onlinewallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
public class OnlinePaymentWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinePaymentWalletApplication.class, args);
	}

}
