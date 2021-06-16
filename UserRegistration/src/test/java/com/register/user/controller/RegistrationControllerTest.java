package com.register.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.register.user.dto.LoginDto;
import com.register.user.dto.RegisterDto;
import com.register.user.repository.entity.UserRegister;
import com.register.user.service.RegistrationService;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

	@Mock
	RegistrationService registrationService;

	@InjectMocks
	RegistrationController registrationController;

	static RegisterDto registerDto;

	static UserRegister register;

	static UserRegister login;

	static LoginDto loginDto;

	@BeforeAll
	public static void setUp() {
		registerDto = new RegisterDto();
		registerDto.setId(1L);
		registerDto.setFirstName("sravanthi");
		registerDto.setLastName("ch");
		registerDto.setEmail("sravanthi.ch@gm.com");
		registerDto.setMobile("8888997766");
		registerDto.setPassword("password");
		registerDto.setPincode("898789");
		registerDto.setAddress("add-tg");
		registerDto.setActive(false);

		register = new UserRegister();
		register.setId(1L);
		register.setFirstName("sravanthi");
		register.setLastName("ch");
		register.setEmail("sravanthi.ch@gm.com");
		register.setMobile("8888997766");
		register.setPassword("password");
		register.setPincode("898789");
		register.setAddress("add-tg");
		register.setActive(false);

		loginDto = new LoginDto();
		loginDto.setEmail("sravanth.ch@gg.com");
		loginDto.setPassword("password");

		login = new UserRegister();
		login.setId(1L);
		login.setFirstName("sravanthi");
		login.setLastName("ch");
		login.setEmail("sravanthi.ch@gm.com");
		login.setMobile("8888997766");
		login.setPassword("password");
		login.setPincode("898789");
		login.setAddress("add-tg");
		login.setActive(true);

	}

	@Test
	@DisplayName("Registering User")
	void addUserTest() {
		// context
		when(registrationService.registerUser(registerDto)).thenReturn(register);
		
		// event
		ResponseEntity<UserRegister> result = registrationController.registerUser(registerDto, null);
		verify(registrationService).registerUser(registerDto);

		// outcome
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	@DisplayName("login with proper username and password")
	void loginTest() {

		// context
		when(registrationService.login(loginDto.getEmail(), loginDto.getPassword())).thenReturn(register);

		// event
		ResponseEntity<UserRegister> result = registrationController.login(loginDto);
		verify(registrationService).login(loginDto.getEmail(), loginDto.getPassword());

		// outcome
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Retrieve User By Id")
	void getUserByIdTest() {

		// context
		when(registrationService.getUserById(1L)).thenReturn(register);

		// event
		ResponseEntity<UserRegister> result = registrationController.getUserById(1L);
		verify(registrationService).getUserById(1L);

		// outcome
		assertEquals(register, result.getBody());
	}

}
