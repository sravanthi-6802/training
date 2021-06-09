package com.fund.transfer.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("http://product-service/shopping/products")
public interface ShoppingService {

	@GetMapping("/data")
	public String productData();
}
