package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.main.dto.SalaryAccountDto;
import com.aurionpro.main.entity.SalaryAccount;
import com.aurionpro.main.repository.SalaryAccountRepository;

@Service
public class SalaryAccountServiceImpl  implements SalaryAccountService{
	
	@Autowired
	private SalaryAccountRepository salaryAccountRepo;

	@Override
	public SalaryAccountDto addSalaryAccountDto(SalaryAccountDto salaryAccountDto) {
		
		SalaryAccount salaryAccount= salaryAccountMapper( salaryAccountDto);	
		salaryAccountRepo.save(salaryAccount);		
		salaryAccountDto = salaryAccountDtoMapper(salaryAccount);	
		
		return salaryAccountDto;
	}

	private SalaryAccountDto salaryAccountDtoMapper(SalaryAccount salaryAccount) {
		
		SalaryAccountDto salaryAccountDto = new SalaryAccountDto();
		
		salaryAccountDto.setAccountHolderName(salaryAccount.getAccountHolderName());
		salaryAccountDto.setAccountNumber(salaryAccount.getAccountNumber());
		
		return salaryAccountDto;
	}

	private SalaryAccount salaryAccountMapper(SalaryAccountDto salaryAccountDto) {
		
		SalaryAccount salaryAccount = new SalaryAccount();
		
		salaryAccount.setAccountHolderName(salaryAccountDto.getAccountHolderName());
		salaryAccount.setAccountNumber(salaryAccountDto.getAccountNumber());
		
		return salaryAccount;
	}

	@Override
	public List<SalaryAccountDto> getAllSalaryAccount() {
		List<SalaryAccount> salaryAccounts = salaryAccountRepo.findAll();
		List<SalaryAccountDto> salaryAccountDtos = new ArrayList<>();
		
		for(SalaryAccount a : salaryAccounts)
		{
			SalaryAccountDto salaryAccountDto = salaryAccountDtoMapper(a);
			salaryAccountDtos.add(salaryAccountDto);
			
		}
		
		return salaryAccountDtos;
	}

	@Override
	public SalaryAccountDto getSalaryAccountDtByAccountNumber(int accountNumber) {
		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepo.findById(accountNumber);
		
		if(optionalSalaryAccount.isEmpty())
		  return null;		
		SalaryAccount salaryAccount = optionalSalaryAccount.get();
		//SalaryAccountDto salaryAccountDto = salaryAccountDtoMapper(salaryAccount);
		
		return salaryAccountDtoMapper(salaryAccount);
	}

}
