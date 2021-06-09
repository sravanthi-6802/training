package com.fund.transfer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fund.transfer.dto.CustomUserDto;
import com.fund.transfer.repository.UserRepository;
import com.fund.transfer.repository.entity.CustomUser;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService;

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
		customUserDto.setMobileNumber("1234567898");
		customUserDto.setPassword("test456");

		customUser = new CustomUser();
		customUser.setCity("mock city");
		customUser.setFirstName("sravanthi");
		customUser.setLastName("v");
		customUser.setMailId("cba@gmail.com");
		customUser.setMobileNumber("2123456787");
		customUser.setPassword("test123");

		CustomUser customUser1 = new CustomUser();
		customUser1.setCity("city");
		customUser1.setFirstName("pawan");
		customUser1.setLastName("v");
		customUser1.setMailId("pavan@gmail.com");
		customUser1.setMobileNumber("223456787");
		customUser1.setPassword("test123");

		customUserList = new ArrayList<CustomUser>();
		customUserList.add(customUser);
		customUserList.add(customUser1);

	}

	@Test
	@DisplayName("Save User")
	void addUserTest() {

		// context
		when(userRepository.save(any(CustomUser.class))).thenAnswer(i -> {
			CustomUser user = i.getArgument(0);
			user.setId(1L);
			customUser = user;
			return customUser;
		});

		// event
		CustomUser result = userService.addUser(customUserDto);

		// outcome
		assertEquals(customUser, result);
	}

	@Test
	@DisplayName("Update User")
	void updateUserTest() {

		// context
		when(userRepository.save(any(CustomUser.class))).thenAnswer(i -> {
			CustomUser user = i.getArgument(0);
			user.setId(1L);
			customUser = user;
			return customUser;
		});

		when(userRepository.findById(customUserDto.getId())).thenReturn(Optional.of(customUser));

		// event
		CustomUser result = userService.updateUser(customUserDto);

		// outcome
		assertEquals(customUser, result);
	}

	@Test
	@DisplayName("Get User By Id")
	void getUserByIdTest() {

		// context
		when(userRepository.findById(1L)).thenReturn(Optional.of(customUser));

		// event
		CustomUser result = userService.retrieveUserDetailsById(1L);

		verify(userRepository).findById(1L);

		// outcome
		assertEquals(customUser, result);
	}

	@Test
	@DisplayName("Get All User List")
	void getAllUsersTest() {

		// context
		when(userRepository.findAll()).thenReturn(customUserList);

		List<CustomUser> result = userService.retrieveAllUsers();

		verify(userRepository).findAll();

		// outcome
		assertEquals(customUserList, result);
	}

	@Test
	@DisplayName("Get User By Name and Password")
	void getUserByUserNameAndPasswordTest() {

		// context
		when(userRepository.findByMailIdAndPassword("abc@gmail.com", "password")).thenReturn(Optional.of(customUser));

		// event
		CustomUser result = userService.retrieveUserByUserNameAndPassword("abc@gmail.com", "password");

		verify(userRepository).findByMailIdAndPassword("abc@gmail.com", "password");

		// outcome
		assertEquals(customUser, result);
	}

	@Test
	@DisplayName("Get User By Mail")
	void getUserByMailTest() {
		// context
		when(userRepository.findByMailId("abc@gmail.com")).thenReturn(Optional.of(customUser));

		// event
		Optional<CustomUser> result = userService.retrieveUserDetailsByMail("abc@gmail.com");

		verify(userRepository).findByMailId("abc@gmail.com");

		// outcome
		assertEquals(customUser, result.get());
	}

}
