package com.fund.transfer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.beans.BeanUtils;

import com.fund.transfer.dto.AccountDto;
import com.fund.transfer.repository.AccountRepository;
import com.fund.transfer.repository.entity.Account;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@Mock
	AccountRepository accountRepository;

	@InjectMocks
	AccountService accountService;

	static AccountDto accountDto;

	static Account account;

	static List<Account> accountList;

	@BeforeAll
	public static void setUp() {

		accountDto = new AccountDto();
		accountDto.setAccountHolderName("sravanthi");
		accountDto.setAccountNumber("987654321234567");
		accountDto.setBalance(new BigDecimal(20000));
		accountDto.setBankName("HDFC");
		accountDto.setIfscCode("HDFC000987");
		accountDto.setUserId(1L);
		accountDto.setId(1L);

		account = new Account();
		account.setAccountHolderName("sravanthi");
		account.setAccountNumber("987654321234567");
		account.setBalance(new BigDecimal(20000));
		account.setBankName("HDFC");
		account.setIfscCode("HDFC000987");
		account.setUserId(1L);
		account.setId(1L);

		accountList = new ArrayList<Account>();
		accountList.add(account);
	}

	@Test
	@DisplayName("Save Account")
	void addAccountTest() {

		// context
		when(accountRepository.save(any(Account.class))).thenAnswer(i -> {
			Account acc = i.getArgument(0);
			acc.setId(1L);
			account = acc;
			return account;
		});

		// event
		Account result = accountService.addAccount(accountDto);

		// outcome
		assertEquals(account, result);
	}

	@Test
	@DisplayName("Retrieve Account By Id")
	void getAccountByIdTest() {

		// context
		when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

		// event
		Account result = accountService.retriveAccount(1L);

		verify(accountRepository).findById(1L);

		// outcome
		assertEquals(account, result);
	}

	@Test
	@DisplayName("Retrieve All Accounts")
	void getAllAccountsTest() {

		// context
		when(accountRepository.findAll()).thenReturn(accountList);

		// event
		List<Account> result = accountService.retriveAllAccounts();

		verify(accountRepository).findAll();

		// outcome
		assertEquals(accountList, result);

	}

	@Test
	@DisplayName("Retrieve Account By Account Number")
	void getAccountByAccountNumber() {

		// context
		when(accountRepository.findByAccountNumber("987654321234567")).thenReturn(Optional.of(account));

		// event
		Optional<AccountDto> result = accountService.findByAccountNumber("987654321234567");

		verify(accountRepository).findByAccountNumber("987654321234567");

		Account acc = new Account();
		BeanUtils.copyProperties(result, acc);
		// outcome
		assertEquals(account, acc);
	}

	@Test
	@DisplayName("Update Account")
	void updateAccountTest() {

		// context
		when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

		when(accountRepository.save(any(Account.class))).thenAnswer(i -> {
			Account acc = i.getArgument(0);
			acc.setId(1L);
			account = acc;
			return account;
		});

		// event
		Account result = accountService.updateAccount(accountDto);

		// outcome
		assertEquals(account, result);
	}

}
