package com.fund.transfer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fund.transfer.dto.AccountDto;
import com.fund.transfer.repository.entity.Account;
import com.fund.transfer.service.AccountService;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

	@Mock
	AccountService accountService;

	@InjectMocks
	AccountController accountController;

	static AccountDto accountDto;

	static Account account;

	static List<Account> accountList;

	@BeforeAll
	public static void setUp() {
		accountDto = new AccountDto();
		accountDto.setAccountHolderName("sravanthi");
		accountDto.setAccountNumber("98765678765456");
		accountDto.setIfscCode("HDFC000786");
		accountDto.setBankName("HDFC");
		accountDto.setBalance(new BigDecimal(10000));
		accountDto.setId(1L);
		accountDto.setUserId(1L);

		account = new Account();
		account.setAccountHolderName("sravanthi ch");
		account.setAccountNumber("98765678765456");
		account.setIfscCode("HDFC000786");
		account.setBankName("HDFC");
		account.setBalance(new BigDecimal(20000));
		account.setId(1L);
		account.setUserId(1L);

		accountList = new ArrayList<Account>();
		accountList.add(account);
	}

	@Test
	@DisplayName("Add Account Test Method")
	void addAccountTest() {

		// context
		when(accountService.addAccount(accountDto)).thenReturn(account);

		// event
		Account result = accountService.addAccount(accountDto);

		verify(accountService).addAccount(accountDto);

		// outcome
		assertEquals(account, result);
	}

	@Test
	@DisplayName("Update Account Test Method")
	void updateAccountTest() {

		// context
		when(accountService.updateAccount(accountDto)).thenReturn(account);

		// event
		Account result = accountService.updateAccount(accountDto);

		// outcome
		assertEquals(account, result);

	}

	@Test
	@DisplayName("Get Account By AccountNumber Test Method")
	void getAccountByAccountNumberTest() {

		// context
		when(accountService.findByAccountNumber("2435678")).thenReturn(Optional.of(account));

		// event
		Optional<Account> result = accountService.findByAccountNumber("2435678");

		verify(accountService).findByAccountNumber("2435678");

		// outcome
		assertEquals(account, result.get());

	}

	@Test
	@DisplayName("Get All Account Test Method")
	void getAllAccountsTest() {

		// context
		when(accountService.retriveAllAccounts()).thenReturn(accountList);

		// event
		List<Account> result = accountService.retriveAllAccounts();

		verify(accountService).retriveAllAccounts();

		// outcome
		assertEquals(accountList, result);
	}

	@Test
	@DisplayName("Get Account By ID Test Method")
	void getAccountById() {

		// context
		when(accountService.retriveAccount(1L)).thenReturn(account);

		// event
		Account result = accountService.retriveAccount(1L);

		verify(accountService).retriveAccount(1L);

		// outcome
		assertEquals(account, result);

	}

}
