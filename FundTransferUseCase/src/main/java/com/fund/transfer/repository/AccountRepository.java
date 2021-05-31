package com.fund.transfer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.transfer.repository.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountNumber(String accountNumber);

}
