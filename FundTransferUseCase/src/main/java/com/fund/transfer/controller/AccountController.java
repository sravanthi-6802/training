package com.fund.transfer.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fund.transfer.dto.AccountDto;
import com.fund.transfer.exception.DuplicateEntryException;
import com.fund.transfer.repository.entity.Account;
import com.fund.transfer.service.AccountService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/accounts")
@Validated
public class AccountController {

	@Autowired
	private AccountService accountService;

	@ApiOperation(value = "Api to create new Account")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Account addAccount(@Valid @RequestBody AccountDto accountDto) {
		Optional<AccountDto> existing = accountService.findByAccountNumber(accountDto.getAccountNumber());
		if (existing.isPresent()) {
			throw new DuplicateEntryException("Already Account Number exists");
		}
		return accountService.addAccount(accountDto);
	}

	@ApiOperation(value = "Api to get Account Details")
	@GetMapping("/account/{id}")
	public Account getAccountById(@PathVariable Long id) {
		return accountService.retriveAccount(id);
	}

	@ApiOperation(value = "Api to delete Beneficiary By Id")
	@DeleteMapping("/account/{id}")
	public void deleteAccountById(@PathVariable Long id) {
		accountService.deleteAccountById(id);
	}

	@ApiOperation(value = "Api to get All Accounts.")
	@GetMapping
	public List<Account> getAllAccounts() {
		return accountService.retriveAllAccounts();
	}
}
