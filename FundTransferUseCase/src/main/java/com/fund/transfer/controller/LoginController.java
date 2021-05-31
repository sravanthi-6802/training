package com.fund.transfer.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fund.transfer.dto.CustomUserDto;
import com.fund.transfer.exception.CustomEntityNotFoundException;
import com.fund.transfer.repository.entity.CustomUser;
import com.fund.transfer.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Api to login the user")
	@PostMapping("/login")
	public CustomUserDto loginUser(@RequestBody CustomUserDto userDto) {
		CustomUser loggedInUser = null;
		try {
			loggedInUser = userService.retrieveUserByUserNameAndPassword(userDto.getMailId(), userDto.getPassword());
			loggedInUser.setLoggedIn(true);

			BeanUtils.copyProperties(loggedInUser, userDto);
			userService.updateUser(userDto);
		} catch (CustomEntityNotFoundException e) {
			throw new CustomEntityNotFoundException("Username or Password is incorrect");
		}
		return userDto;
	}

	@ApiOperation(value = "Api to logout the user")
	@PostMapping("/logout/{id}")
	public boolean logoutUser(@PathVariable Long id) {

		CustomUser user = userService.retrieveUserDetailsById(id);
		user.setLoggedIn(false);

		CustomUserDto userDto = new CustomUserDto();
		BeanUtils.copyProperties(user, userDto);
		userService.updateUser(userDto);
		return true;
	}

}
