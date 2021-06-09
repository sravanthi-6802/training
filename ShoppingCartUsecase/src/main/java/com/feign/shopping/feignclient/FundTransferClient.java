package com.feign.shopping.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="http://FundTransfer-service/fund/users")
public interface FundTransferClient {
	
	@GetMapping("/data")
	public String getUserData();

}
