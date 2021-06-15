package com.fund.transfer.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fund.transfer.dto.AccountDto;
import com.fund.transfer.dto.TransferFundRequest;
import com.fund.transfer.exception.CustomEntityNotFoundException;
import com.fund.transfer.repository.entity.Account;
import com.fund.transfer.repository.entity.CustomTransaction;
import com.fund.transfer.service.AccountService;
import com.fund.transfer.service.CustomTransactionService;

@RestController
public class FundTransferController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomTransactionService transactionService;

	@PostMapping("/transfer")
	public CustomTransaction transferFundRequest(@RequestBody TransferFundRequest fundRequest) {

		CustomTransaction transaction = new CustomTransaction();
		return sendMoney(fundRequest, transaction);
	}

	public CustomTransaction sendMoney(TransferFundRequest fundRequest, CustomTransaction transaction) {
		String fromAccountNumber = fundRequest.getFromAccountNumber();
		String toAccountNumber = fundRequest.getToAccountNumber();
		BigDecimal amount = new BigDecimal(fundRequest.getAmount());

		CustomTransaction result = new CustomTransaction();

		Optional<Account> account = accountService.findByAccountNumber(fromAccountNumber);
		if (!account.isPresent()) {
			throw new CustomEntityNotFoundException("From Account doesn't exist");
		}
		Account acc = account.get();
		if (acc.getBalance().compareTo(BigDecimal.ONE) == 1 && acc.getBalance().compareTo(amount) == 1) {

			AccountDto accountDto = new AccountDto();

			transaction.setFromAccountnumber(fromAccountNumber);
			transaction.setToAccountNumber(toAccountNumber);
			transaction.setTransactionAmount(amount);

			acc.setBalance(acc.getBalance().subtract(amount));

			BeanUtils.copyProperties(acc, accountDto);
			accountService.updateAccount(accountDto);

			transaction.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));

			result = transactionService.addTransaction(transaction);

		}

		return result;

	}

}
