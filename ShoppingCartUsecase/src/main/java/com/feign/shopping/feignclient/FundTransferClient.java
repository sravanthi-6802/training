package com.feign.shopping.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.feign.shopping.dto.CustomTransaction;
import com.feign.shopping.dto.TransferFundRequest;

@FeignClient(name="http://FundTransfer-service/fund")
public interface FundTransferClient {
	
	@GetMapping("/users/data")
	public String getUserData();
	
	@PostMapping("/transfer")
	public CustomTransaction transferFundRequest(@RequestBody TransferFundRequest pay);

}
