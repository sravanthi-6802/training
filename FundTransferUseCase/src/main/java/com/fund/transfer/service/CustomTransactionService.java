package com.fund.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.transfer.repository.CustomTransactionRepository;
import com.fund.transfer.repository.entity.CustomTransaction;

@Service
public class CustomTransactionService {

	@Autowired
	private CustomTransactionRepository transactionRepository;

	public CustomTransaction addTransaction(CustomTransaction transaction) {

		return transactionRepository.save(transaction);
	}

}
