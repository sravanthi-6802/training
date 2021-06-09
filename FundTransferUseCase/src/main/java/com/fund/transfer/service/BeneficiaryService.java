package com.fund.transfer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.transfer.dto.BeneficiaryDto;
import com.fund.transfer.exception.CustomEntityNotFoundException;
import com.fund.transfer.repository.BeneficiaryRepository;
import com.fund.transfer.repository.entity.Beneficiary;
import com.fund.transfer.util.FundTransferConstants;

@Service
public class BeneficiaryService {

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;

	public Beneficiary addBeneficiary(BeneficiaryDto beneficiaryDto) {
		Beneficiary beneficiary = new Beneficiary();
		BeanUtils.copyProperties(beneficiaryDto, beneficiary);
		return beneficiaryRepository.save(beneficiary);
	}

	public List<Beneficiary> retriveAllBeneficiaries() {
		return beneficiaryRepository.findAll();
	}

	public Beneficiary getBeneficiaryById(Long id) {
		Optional<Beneficiary> beneficiary = beneficiaryRepository.findById(id);
		if (!beneficiary.isPresent()) {
			throw new CustomEntityNotFoundException(FundTransferConstants.BENEFICIARYNOTFOUND);
		}

		return beneficiary.get();
	}

	public void deleteBeneficiaryById(Long id) {
		beneficiaryRepository.deleteById(id);
	}

	public Beneficiary findByAccountNumber(String accountNumber) {
		Optional<Beneficiary> beneficiary = beneficiaryRepository.findByAccountNumber(accountNumber);
		if (!beneficiary.isPresent()) {
			throw new CustomEntityNotFoundException(FundTransferConstants.BENEFICIARYNOTFOUND);
		}

		return beneficiary.get();
	}

	public Beneficiary updateBeneficiary(BeneficiaryDto beneficiaryDto) {
		Optional<Beneficiary> existingBeneficiary = beneficiaryRepository.findById(beneficiaryDto.getId());
		if (!existingBeneficiary.isPresent()) {
			throw new CustomEntityNotFoundException(FundTransferConstants.BENEFICIARYNOTFOUND);
		}

		return beneficiaryRepository.save(existingBeneficiary.get());
	}

}
