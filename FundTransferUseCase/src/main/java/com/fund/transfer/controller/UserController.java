package com.fund.transfer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fund.transfer.dto.CustomUserDto;
import com.fund.transfer.feignclient.ShoppingService;
import com.fund.transfer.repository.entity.CustomUser;
import com.fund.transfer.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	Environment environment;

	@ApiOperation(value = "Api to register new User")
	@PostMapping("/register")
	public ResponseEntity<CustomUser> createUser(@Valid @RequestBody CustomUserDto userDto, BindingResult res) {
		CustomUser customUser = userService.addUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(customUser);
	}

	@ApiOperation(value = "Api to update Existing User")
	@PutMapping
	public ResponseEntity<CustomUser> updateUser(@Valid @RequestBody CustomUserDto userDto) {
		CustomUser customUser = userService.updateUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(customUser);
	}

	@ApiOperation(value = "Api to delete All Users")
	@DeleteMapping
	public void deleteUsers() {
		userService.deleteAllUsers();
	}

	@ApiOperation(value = "Api to get All Users")
	@GetMapping
	public List<CustomUser> getAllUsers() {
		return userService.retrieveAllUsers();
	}

	@ApiOperation(value = "Api to get User By Id")
	@GetMapping("/user/{id}")
	public CustomUser getUserById(@PathVariable Long id) {
		return userService.retrieveUserDetailsById(id);
	}

	@GetMapping("/data")
	public String userData() {
		String port = environment.getProperty("local.server.port");
		return "Fund Transfer User Controller Test Data running on "+port;
	}
	
	@GetMapping("/product")
	public String shoppingService() {
		return shoppingService.productData();
	}
}
