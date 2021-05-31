package com.fund.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.transfer.repository.entity.CustomTransaction;

public interface CustomTransactionRepository extends JpaRepository<CustomTransaction, Long> {

}
