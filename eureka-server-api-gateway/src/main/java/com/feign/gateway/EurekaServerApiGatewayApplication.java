package com.feign.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaServerApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApiGatewayApplication.class, args);
	}

}
