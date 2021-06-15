package com.feign.shopping.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.feign.shopping.dto.UserDto;

@FeignClient(name="http://user-service/users")
public interface UserClient {
	
	@GetMapping("/user/{id}")
	public UserDto findByUserId(@PathVariable("id") Long id);

}
