package com.fund.transfer.dto;

import javax.validation.constraints.NotEmpty;

public class BeneficiaryDto {

	private Long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String accountNumber;

	@NotEmpty
	private String ifsc;

	private String bank;

	@NotEmpty
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BeneficiaryDto() {
		super();
	}

}
