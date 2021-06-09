package com.fund.transfer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fund.transfer.dto.AccountDto;
import com.fund.transfer.dto.TransferFundRequest;
import com.fund.transfer.repository.entity.CustomTransaction;
import com.fund.transfer.service.AccountService;
import com.fund.transfer.service.CustomTransactionService;

@ExtendWith(MockitoExtension.class)
class FundTransferControllerTest {

	@Mock
	AccountService accountService;

	@Mock
	CustomTransactionService customTransactionService;

	@InjectMocks
	FundTransferController fundTransferController;

	static TransferFundRequest transferFundRequest;

	static CustomTransaction customTransaction;
	
	static CustomTransaction transaction;

	static AccountDto account;

	@BeforeAll
	public static void setUp() {
		transferFundRequest = new TransferFundRequest();
		transferFundRequest.setAmount(1200);
		transferFundRequest.setFromAccountNumber("987654321234567");
		transferFundRequest.setToAccountNumber("98765456");

		customTransaction = new CustomTransaction();
		customTransaction.setFromAccountnumber("987654321234567");
		customTransaction.setToAccountNumber("98765456");
		customTransaction.setId(1L);
		customTransaction.setTransactionAmount(new BigDecimal(1200));
		
		transaction = new CustomTransaction();
		transaction.setFromAccountnumber("987654321234567");
		transaction.setToAccountNumber("98765456");
		transaction.setId(1L);
		transaction.setTransactionAmount(new BigDecimal(1200));

		account = new AccountDto();
		account.setAccountHolderName("sravanthi ch");
		account.setAccountNumber("987654321234567");
		account.setIfscCode("HDFC000786");
		account.setBankName("HDFC");
		account.setBalance(new BigDecimal(20000));
		account.setId(1L);
		account.setUserId(1L);
	}

	@Test
	@DisplayName("Transfer Fund Request Test Method")
	@Disabled
	void transferTest() {

		// context
		when(customTransactionService.addTransaction(customTransaction)).thenReturn(transaction);

		when(accountService.findByAccountNumber("987654321234567", account)).thenReturn(Optional.of(account));

		// event
		CustomTransaction result = fundTransferController.sendMoney(transferFundRequest, account, transaction);

		// outcome
		assertEquals(transaction, result);
	}

}
