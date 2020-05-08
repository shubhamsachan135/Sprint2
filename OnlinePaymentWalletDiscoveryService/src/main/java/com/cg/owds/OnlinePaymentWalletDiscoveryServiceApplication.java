package com.cg.owds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OnlinePaymentWalletDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinePaymentWalletDiscoveryServiceApplication.class, args);
	}

}
