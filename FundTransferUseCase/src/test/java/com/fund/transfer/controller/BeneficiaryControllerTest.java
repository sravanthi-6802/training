package com.fund.transfer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fund.transfer.dto.BeneficiaryDto;
import com.fund.transfer.repository.entity.Beneficiary;
import com.fund.transfer.service.BeneficiaryService;

@ExtendWith(MockitoExtension.class)
class BeneficiaryControllerTest {

	@Mock
	BeneficiaryService beneficiaryService;

	@InjectMocks
	BeneficiaryController beneficiaryController;

	static Beneficiary beneficiary;

	static BeneficiaryDto beneficiaryDto;

	static List<Beneficiary> beneficiaryList;

	@BeforeAll
	public static void setUp() {

		beneficiary = new Beneficiary();
		beneficiary.setAccountNumber("9876543211234567");
		beneficiary.setIfsc("SBI00867");
		beneficiary.setBank("SBI");
		beneficiary.setName("Kavitha");
		beneficiary.setUserId(1L);
		beneficiary.setId(1L);

		beneficiaryDto = new BeneficiaryDto();
		beneficiaryDto.setAccountNumber("9876543211234567");
		beneficiaryDto.setIfsc("SBI00867");
		beneficiaryDto.setBank("SBI");
		beneficiaryDto.setName("Kavitha");
		beneficiaryDto.setUserId(1L);
		beneficiaryDto.setId(1L);

		beneficiaryList = new ArrayList<Beneficiary>();
		beneficiaryList.add(beneficiary);

	}

	@Test
	@DisplayName("Add Beneficiary Test Method")
	void addBeneficiary() {

		// context
		when(beneficiaryService.addBeneficiary(beneficiaryDto)).thenReturn(beneficiary);

		// event
		ResponseEntity<Beneficiary> result = beneficiaryController.addBeneficiary(beneficiaryDto);

		verify(beneficiaryService).addBeneficiary(beneficiaryDto);

		// outcome
		assertEquals(beneficiary, result.getBody());
	}

	@Test
	@DisplayName("Get All Beneficiaries Test Method")
	void getAllBeneficiariesTest() {

		// context
		when(beneficiaryService.retriveAllBeneficiaries()).thenReturn(beneficiaryList);

		// event
		List<Beneficiary> result = beneficiaryController.getAllBeneficiaries();

		verify(beneficiaryService).retriveAllBeneficiaries();

		// outcome
		assertEquals(beneficiaryList, result);
	}

	@Test
	@DisplayName("Get Beneficiary By Id Test Method")
	void getBeneficiaryByIdTest() {

		// context
		when(beneficiaryService.getBeneficiaryById(1L)).thenReturn(beneficiary);

		// event
		Beneficiary result = beneficiaryController.getBeneficiaryById(1L);

		verify(beneficiaryService).getBeneficiaryById(1L);

		// outcome
		assertEquals(beneficiary, result);
	}

	@Test
	@DisplayName("Get Beneficiary By AccountNumber Test Method")
	void getBeneficiaryByAccountNumberTest() {

		// context
		when(beneficiaryService.findByAccountNumber("98765432123456")).thenReturn(beneficiary);

		// event
		Beneficiary result = beneficiaryController.getBeneficiaryByAccountNumber("98765432123456");

		verify(beneficiaryService).findByAccountNumber("98765432123456");

		// outcome
		assertEquals(beneficiary, result);
	}

}
