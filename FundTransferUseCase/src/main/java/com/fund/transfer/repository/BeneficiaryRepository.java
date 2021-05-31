package com.fund.transfer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fund.transfer.repository.entity.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

	Optional<Beneficiary> findByAccountNumber(String accountNumber);

}
