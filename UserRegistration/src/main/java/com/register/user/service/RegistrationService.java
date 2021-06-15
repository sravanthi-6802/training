package com.register.user.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.user.dto.RegisterDto;
import com.register.user.exception.CustomEntityNotFoundException;
import com.register.user.exception.DuplicateEntryException;
import com.register.user.repository.RegistrationRepository;
import com.register.user.repository.entity.UserRegister;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;

	public UserRegister registerUser(RegisterDto register) {
		UserRegister user = new UserRegister();
		BeanUtils.copyProperties(register, user);
		return registrationRepository.save(user);
	}

	public UserRegister login(String email, String password) {
		Optional<UserRegister> result = registrationRepository.findByEmailAndPassword(email, password);
		if (result.isPresent()) {
			UserRegister user = result.get();
			user.setActive(true);
			registrationRepository.save(user);
			return result.get();
		} else {
			throw new CustomEntityNotFoundException("Email/Password incorrect");
		}

	}

	public UserRegister userExists(String email, String mobile) {
		Optional<UserRegister> result = registrationRepository.findByEmailAndMobile(email, mobile);
		if (result.isPresent()) {
			throw new DuplicateEntryException("User already exists with email/mobile number.");
		} else {
			return null;
		}
	}

	public UserRegister getUserById(Long id) {
		Optional<UserRegister> result = registrationRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new CustomEntityNotFoundException("User Not Found");
		}
	}

}
