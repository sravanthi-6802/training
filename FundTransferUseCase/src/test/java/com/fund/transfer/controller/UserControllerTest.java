package com.fund.transfer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fund.transfer.dto.CustomUserDto;
import com.fund.transfer.repository.entity.CustomUser;
import com.fund.transfer.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	static UserService userService;

	@InjectMocks
	static UserController userController;

	static CustomUserDto customUserDto;

	static CustomUser customUser;

	static List<CustomUser> customUserList;

	@BeforeAll
	public static void set() {

		customUserDto = new CustomUserDto();
		customUserDto.setAddress("");
		customUserDto.setFirstName("sravanthi");
		customUserDto.setLastName("ch");
		customUserDto.setMailId("abc@gmail.com");
		customUserDto.setMobileNumber(1234567898);
		customUserDto.setPassword("test456");

		customUser = new CustomUser();
		customUser.setCity("mock city");
		customUser.setFirstName("sravanthi");
		customUser.setLastName("v");
		customUser.setMailId("cba@gmail.com");
		customUser.setMobileNumber(2123456787);
		customUser.setPassword("test123");

		CustomUser customUser1 = new CustomUser();
		customUser1.setCity("city");
		customUser1.setFirstName("pawan");
		customUser1.setLastName("v");
		customUser1.setMailId("pavan@gmail.com");
		customUser1.setMobileNumber(223456787);
		customUser1.setPassword("test123");

		customUserList = new ArrayList<CustomUser>();
		customUserList.add(customUser);
		customUserList.add(customUser1);

	}

	@Test
	@DisplayName("User Creation Test Method")
	void createUserTest() {

		when(userService.addUser(customUserDto)).thenReturn(customUser);

		ResponseEntity<CustomUser> result = userController.createUser(customUserDto);

		verify(userService).addUser(customUserDto);

		assertEquals(customUser, result.getBody());
	}

	@Test
	@DisplayName("User Updation Test Method")
	void updateUserTest() {

		when(userService.updateUser(customUserDto)).thenReturn(customUser);

		ResponseEntity<CustomUser> result = userController.updateUser(customUserDto);

		verify(userService).addUser(customUserDto);

		assertEquals(customUser, result.getBody());
	}

	@Test
	@DisplayName("Retrieving all Users Test Method")
	void getAllUsersTest() {

		when(userService.retrieveAllUsers()).thenReturn(customUserList);

		List<CustomUser> result = userController.getAllUsers();

		verify(userService).retrieveAllUsers();

		assertEquals(customUserList, result);
	}

	@Test
	@DisplayName("Retrieving User By Id Test Method")
	void getUserTest() {

		when(userService.retrieveUserDetailsById(1L)).thenReturn(customUser);

		CustomUser result = userController.getUserById(1L);

		verify(userService).retrieveUserDetailsById(1L);

		assertEquals(customUser, result);
	}

}
