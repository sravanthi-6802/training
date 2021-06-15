package com.register.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.register.user.dto.LoginDto;
import com.register.user.dto.RegisterDto;
import com.register.user.repository.entity.UserRegister;
import com.register.user.service.RegistrationService;

@RestController
@Validated
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/register")
	public ResponseEntity<UserRegister> registerUser(@Valid @RequestBody RegisterDto userDto, BindingResult res) {
		registrationService.userExists(userDto.getEmail(), userDto.getMobile());
		UserRegister user = registrationService.registerUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<UserRegister> login(@Valid @RequestBody LoginDto loginDto) {
		UserRegister user = registrationService.login(loginDto.getEmail(), loginDto.getPassword());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserRegister> getUserById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(registrationService.getUserById(id), HttpStatus.OK);
	}

}
