package com.fund.transfer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fund.transfer.repository.CustomTransactionRepository;
import com.fund.transfer.repository.entity.CustomTransaction;

@ExtendWith(MockitoExtension.class)
class CustomTransactionServiceTest {

	@Mock
	CustomTransactionRepository transactionRepository;

	@InjectMocks
	CustomTransactionService customTransactionService;

	static CustomTransaction customTransaction;

	@BeforeAll
	public static void setUp() {
		customTransaction = new CustomTransaction();
		customTransaction.setId(1L);
		customTransaction.setFromAccountnumber("12345678912345");
		customTransaction.setToAccountNumber("9876543212345");
		customTransaction.setTransactionAmount(new BigDecimal(800));
		customTransaction.setTransactionDateTime(new Timestamp(Instant.now().toEpochMilli()));
	}

	@Test
	@DisplayName("Save transaction")
	void addTransactionTest() {

		// context
		when(transactionRepository.save(customTransaction)).thenReturn(customTransaction);

		// event
		CustomTransaction result = customTransactionService.addTransaction(customTransaction);

		verify(transactionRepository).save(customTransaction);

		// outcome
		assertEquals(customTransaction, result);
	}

}
