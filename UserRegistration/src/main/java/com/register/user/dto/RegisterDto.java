package com.register.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterDto {

	private Long id;

	@NotNull(message = "FirstName cannot be null")
	private String firstName;

	@NotNull(message = "LastName cannot be null")
	private String lastName;

	@NotNull(message = "Email cannot be null")
	@Email(message = "Provide valid email")
	private String email;

	@NotNull(message = "Mobile number cannot be null")
	@Size(min = 8, max = 12, message = "Provide valid mobile number")
	private String mobile;

	private String address;

	private String pincode;

	private boolean active;

	@NotNull(message = "Password cannot be null")
	@Size(min = 8, max = 14, message = "Password length should be in between 8 to 14.")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
