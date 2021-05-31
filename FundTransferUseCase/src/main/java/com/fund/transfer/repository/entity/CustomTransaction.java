package com.fund.transfer.repository.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String fromAccountnumber;

	@Column
	private String toAccountNumber;

	@Column
	private BigDecimal transactionAmount;

	@Column
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
