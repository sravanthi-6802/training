package com.fund.transfer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.transfer.dto.AccountDto;
import com.fund.transfer.exception.CustomEntityNotFoundException;
import com.fund.transfer.repository.AccountRepository;
import com.fund.transfer.repository.entity.Account;
import com.fund.transfer.util.FundTransferConstants;

@Service
public class AccountService {

	Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountRepository accountRepository;

	public Account addAccount(AccountDto accountDto) {
		logger.debug("log before account save");
		Account account = new Account();
		BeanUtils.copyProperties(accountDto, account);
		return accountRepository.save(account);
	}

	public Account retriveAccount(Long id) {
		Optional<Account> account = accountRepository.findById(id);
		if (!account.isPresent()) {
			logger.error(FundTransferConstants.ACCOUNTNOTFOUND);
			throw new CustomEntityNotFoundException(FundTransferConstants.ACCOUNTNOTFOUND);
		}

		return account.get();
	}

	public List<Account> retriveAllAccounts() {
		logger.debug("Retrieving all Accounts from DB.");
		return accountRepository.findAll();
	}

	public void deleteAccountById(Long id) {
		logger.debug("Deleting account by id.");
		accountRepository.deleteById(id);
	}

	public void deleteAllAccounts() {
		logger.debug("Deleting all Accounts.");
		accountRepository.deleteAll();
	}

	public Optional<AccountDto> findByAccountNumber(String accountNumber, AccountDto accountDto) {
		Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
		if (!account.isPresent()) {
			logger.error("Account Not Found Exception");
			throw new CustomEntityNotFoundException(FundTransferConstants.ACCOUNTNOTFOUND);
		}
		BeanUtils.copyProperties(account, accountDto);
		return Optional.of(accountDto);
	}

	public Account updateAccount(AccountDto accountDto) {
		Optional<Account> existingAccount = accountRepository.findById(accountDto.getId());
		if (!existingAccount.isPresent()) {
			logger.error("Account Not Found Exception");
			throw new CustomEntityNotFoundException(FundTransferConstants.ACCOUNTNOTFOUND);
		}
		return accountRepository.save(existingAccount.get());
	}

}
