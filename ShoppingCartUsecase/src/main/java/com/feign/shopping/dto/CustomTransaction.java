package com.feign.shopping.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CustomTransaction {

	private Long id;

	private String fromAccountnumber;

	private String toAccountNumber;

	private BigDecimal transactionAmount;

	private Timestamp transactionDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromAccountnumber() {
		return fromAccountnumber;
	}

	public void setFromAccountnumber(String fromAccountnumber) {
		this.fromAccountnumber = fromAccountnumber;
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Timestamp getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Timestamp transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public CustomTransaction() {
		super();
	}

}
