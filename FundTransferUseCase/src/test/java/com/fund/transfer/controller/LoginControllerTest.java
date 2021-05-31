package com.fund.transfer.controller;

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

import com.fund.transfer.dto.CustomUserDto;
import com.fund.transfer.repository.entity.CustomUser;
import com.fund.transfer.service.UserService;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

	@Mock
	UserService userService;

	@InjectMocks
	LoginController loginController;

	static CustomUserDto customUserDto;

	static CustomUser customUser;

	@BeforeAll
	public static void setUp() {

		customUserDto = new CustomUserDto();
		customUserDto.setFirstName("sravanthi");
		customUserDto.setPassword("password");
		customUserDto.setLoggedIn(true);

		customUser = new CustomUser();
		customUser.setFirstName("sravanthi");
		customUser.setLastName("ch");
		customUser.setCity("mock city");
		customUser.setMobileNumber(1234567678);
		customUser.setMailId("abc@gmail.com");
		customUser.setPassword("password");
	}

	@Test
	@DisplayName("Login User Test Method")
	void loginUserTest() {

		when(userService.retrieveUserByUserNameAndPassword("sravanthi", "password")).thenReturn(customUser);

		CustomUserDto result = loginController.loginUser(customUserDto);

		verify(userService).retrieveUserByUserNameAndPassword("sravanthi", "password");

		assertEquals(customUserDto, result);
	}

	@Test
	@DisplayName("Logout User Test Method")
	void logoutUserTest() {

		when(userService.retrieveUserByUserNameAndPassword("sravanthi", "password")).thenReturn(customUser);

		boolean result = loginController.logoutUser(1L);

		verify(userService).retrieveUserByUserNameAndPassword("sravanthi", "password");

		assertEquals(true, result);
	}

}
