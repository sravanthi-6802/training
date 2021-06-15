package com.feign.shopping.dto;

import javax.validation.constraints.NotEmpty;

public class TransferFundRequest {

	@NotEmpty
	private String fromAccountNumber;

	private String toAccountNumber;

	private int amount;

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public TransferFundRequest() {
		super();
	}

}
