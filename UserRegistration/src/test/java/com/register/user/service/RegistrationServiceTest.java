package com.register.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.register.user.dto.LoginDto;
import com.register.user.dto.RegisterDto;
import com.register.user.repository.RegistrationRepository;
import com.register.user.repository.entity.UserRegister;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

	@Mock
	RegistrationRepository registrationRepository;

	@InjectMocks
	RegistrationService registrationService;

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
	@DisplayName("Save User")
	void createUserTest() {

		// context
		when(registrationRepository.save(any(UserRegister.class))).thenAnswer(i -> {
			UserRegister user = i.getArgument(0);
			user.setId(1L);
			register = user;
			return register;
		});

		// event
		UserRegister result = registrationService.registerUser(registerDto);

		// outcome
		assertEquals(register, result);

	}

	@Test
	@DisplayName("Login User")
	void loginTest() {

		// context
		when(registrationRepository.findByEmailAndPassword("sravanthi.ch@gg.com", "password"))
				.thenReturn(Optional.of(register));
		when(registrationRepository.save(any(UserRegister.class))).thenAnswer(i -> {
			UserRegister user = i.getArgument(0);
			user.setId(1L);
			register = user;
			return register;
		});

		// event
		UserRegister result = registrationService.login("sravanthi.ch@gg.com", "password");
		verify(registrationRepository).findByEmailAndPassword("sravanthi.ch@gg.com", "password");

		// outcome
		assertEquals(register, result);
	}

	@Test
	@DisplayName("Get User By Id")
	void getByIdTest() {

		// context
		when(registrationRepository.findById(1L)).thenReturn(Optional.of(register));

		// event
		UserRegister result = registrationService.getUserById(1L);
		verify(registrationRepository).findById(1L);

		// outcome
		assertEquals(register, result);
	}

}
