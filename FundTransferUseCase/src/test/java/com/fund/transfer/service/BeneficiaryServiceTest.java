package com.fund.transfer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fund.transfer.dto.BeneficiaryDto;
import com.fund.transfer.repository.BeneficiaryRepository;
import com.fund.transfer.repository.entity.Beneficiary;

@ExtendWith(MockitoExtension.class)
class BeneficiaryServiceTest {

	@Mock
	BeneficiaryRepository beneficiaryRepository;

	@InjectMocks
	BeneficiaryService beneficiaryService;

	static BeneficiaryDto beneficiaryDto;

	static Beneficiary beneficiary;

	static List<Beneficiary> beneficiaryList;

	@BeforeAll
	public static void setUp() {

		beneficiaryDto = new BeneficiaryDto();
		beneficiaryDto.setAccountNumber("123456789");
		beneficiaryDto.setBank("SBI");
		beneficiaryDto.setId(1L);
		beneficiaryDto.setIfsc("SBI00987");
		beneficiaryDto.setName("Kavitha");
		beneficiaryDto.setUserId(1L);

		beneficiary = new Beneficiary();
		beneficiary.setAccountNumber("123456789");
		beneficiary.setBank("SBI");
		beneficiary.setId(1L);
		beneficiary.setIfsc("SBI00987");
		beneficiary.setName("Kavitha");
		beneficiary.setUserId(1L);

		beneficiaryList = new ArrayList<Beneficiary>();
		beneficiaryList.add(beneficiary);
	}

	@Test
	@DisplayName("Save Beneficiary")
	void addBeneficiaryTest() {

		// context
		when(beneficiaryRepository.save(any(Beneficiary.class))).thenAnswer(i -> {
			Beneficiary ben = i.getArgument(0);
			ben.setId(1L);
			beneficiary = ben;
			return beneficiary;
		});

		// event
		Beneficiary result = beneficiaryService.addBeneficiary(beneficiaryDto);

		verify(beneficiaryRepository).save(beneficiary);

		// outcome
		assertEquals(beneficiary, result);
	}

	@Test
	@DisplayName("Update Beneficiary")
	void updateBeneficiaryTest() {

		// context
		when(beneficiaryRepository.save(any(Beneficiary.class))).thenAnswer(i -> {
			Beneficiary ben = i.getArgument(0);
			ben.setId(1L);
			beneficiary = ben;
			return beneficiary;
		});
		
		when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(beneficiary));

		// event
		Beneficiary result = beneficiaryService.updateBeneficiary(beneficiaryDto);

		verify(beneficiaryRepository).save(beneficiary);

		// outcome
		assertEquals(beneficiary, result);
	}

	@Test
	@DisplayName("Get Beneficiary By Id")
	void getBeneficiaryByIdTest() {

		// context
		when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(beneficiary));

		// event
		Beneficiary result = beneficiaryService.getBeneficiaryById(1L);

		verify(beneficiaryRepository).findById(1L);

		// outcome
		assertEquals(beneficiary, result);
	}

	@Test
	@DisplayName("Get Beneficiary List")
	void getAllBeneficiariesTest() {

		// context
		when(beneficiaryRepository.findAll()).thenReturn(beneficiaryList);

		// event
		List<Beneficiary> result = beneficiaryService.retriveAllBeneficiaries();

		verify(beneficiaryRepository).findAll();

		// outcome
		assertEquals(beneficiaryList, result);
	}

	@Test
	@DisplayName("Get Beneficiary By Account Number")
	void getBeneficiaryByAccountNumberTest() {

		// context
		when(beneficiaryRepository.findByAccountNumber("123456789123456")).thenReturn(Optional.of(beneficiary));

		// event
		Beneficiary result = beneficiaryService.findByAccountNumber("123456789123456");

		verify(beneficiaryRepository).findByAccountNumber("123456789123456");

		// outcome
		assertEquals(beneficiary, result);
	}

}
