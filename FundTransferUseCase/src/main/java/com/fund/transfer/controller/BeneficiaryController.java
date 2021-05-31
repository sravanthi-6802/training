package com.fund.transfer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fund.transfer.dto.BeneficiaryDto;
import com.fund.transfer.repository.entity.Beneficiary;
import com.fund.transfer.service.BeneficiaryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/beneficiaries")
@Validated
public class BeneficiaryController {

	@Autowired
	private BeneficiaryService beneficiaryService;

	@ApiOperation(value = "Api to create Beneficiary.")
	@PostMapping
	public ResponseEntity<Beneficiary> addBeneficiary(@Valid @RequestBody BeneficiaryDto beneficiaryDto) {
		Beneficiary createdBeneficiary = beneficiaryService.addBeneficiary(beneficiaryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBeneficiary);
	}

	@ApiOperation(value = "Api to get Beneficiary By Id.")
	@GetMapping("/beneficiary/{id}")
	public Beneficiary getBeneficiaryById(@PathVariable Long id) {
		return beneficiaryService.getBeneficiaryById(id);
	}

	@ApiOperation(value = "Api to delete Beneficiary By Id.")
	@DeleteMapping("/beneficiary/{id}")
	public void deleteBeneficiaryById(@PathVariable Long id) {
		beneficiaryService.deleteBeneficiaryById(id);
	}

	@ApiOperation(value = "Api to get All Beneficiaries.")
	@GetMapping
	public List<Beneficiary> getAllBeneficiaries() {
		return beneficiaryService.retriveAllBeneficiaries();
	}
	
	@ApiOperation(value = "Api to get Beneficiary By Account Number.")
	@GetMapping("/beneficiary/account/{accountNumber}")
	public Beneficiary getBeneficiaryByAccountNumber(@PathVariable String accountNumber) {
		return beneficiaryService.findByAccountNumber(accountNumber);
	} 

}
