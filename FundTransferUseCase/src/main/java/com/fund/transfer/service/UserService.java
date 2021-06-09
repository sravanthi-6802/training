package com.fund.transfer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.transfer.dto.CustomUserDto;
import com.fund.transfer.exception.CustomEntityNotFoundException;
import com.fund.transfer.exception.DuplicateEntryException;
import com.fund.transfer.repository.UserRepository;
import com.fund.transfer.repository.entity.CustomUser;
import com.fund.transfer.util.FundTransferConstants;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public CustomUser addUser(CustomUserDto userDto) {
		Optional<CustomUser> existingUser = retrieveUserDetailsByMail(userDto.getMailId());
		if (existingUser.isPresent())
			throw new DuplicateEntryException("Already User exists with same Mail Id");

		CustomUser user = new CustomUser();
		BeanUtils.copyProperties(userDto, user);
		CustomUser createdUser = userRepository.save(user);
		logger.debug("Created new User");
		return createdUser;
	}

	public CustomUser retrieveUserDetailsById(Long id) {
		Optional<CustomUser> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new CustomEntityNotFoundException(FundTransferConstants.USERNOTFOUND);
		}
		logger.debug("Retrieved User Details By Id ");
		return user.get();
	}

	public CustomUser retrieveUserByUserNameAndPassword(String username, String password) {
		Optional<CustomUser> user = userRepository.findByMailIdAndPassword(username, password);
		if (!user.isPresent()) {
			throw new CustomEntityNotFoundException(FundTransferConstants.USERNOTFOUND);
		}
		logger.debug("Retrieved user Details by Mail and password for login");
		return user.get();
	}

	public List<CustomUser> retrieveAllUsers() {
		return userRepository.findAll();
	}

	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	public CustomUser updateUser(CustomUserDto userDto) {
		Optional<CustomUser> userData = userRepository.findById(userDto.getId());
		if (!userData.isPresent())
			throw new CustomEntityNotFoundException(FundTransferConstants.USERNOTFOUND);

		CustomUser user = new CustomUser();
		BeanUtils.copyProperties(userDto, user);
		CustomUser updatedUser = userRepository.save(user);
		logger.debug("Updated User Successfully");
		return updatedUser;
	}

	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

	public Optional<CustomUser> retrieveUserDetailsByMail(String mail) {
		return userRepository.findByMailId(mail);
	}
}
