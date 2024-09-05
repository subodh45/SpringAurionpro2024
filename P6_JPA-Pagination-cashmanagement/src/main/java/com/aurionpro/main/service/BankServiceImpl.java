package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.main.dto.BankDto;
import com.aurionpro.main.entity.Bank;
import com.aurionpro.main.entity.SalaryAccount;
import com.aurionpro.main.repository.BankRepository;
import com.aurionpro.main.repository.SalaryAccountRepository;

@Service
public class BankServiceImpl implements BankService {
 
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private SalaryAccountRepository salaryAccountRepo;

	@Override
	public BankDto addBankDto(BankDto bankDto) {
	
		Bank bank = bankMapper(bankDto);
		bankRepository.save(bank);
		bankDto = bankDtoMapper(bank);
		
		return bankDto;
	}

	private BankDto bankDtoMapper(Bank bank) {
		
        BankDto bankDto = new BankDto();
		
        bankDto.setBankId(bank.getBankId());
		bankDto.setBankName(bank.getBankName());
		bankDto.setBranch(bank.getBranch());
		bankDto.setIfscCode(bank.getIfscCode());
		return bankDto;
	}

	private Bank bankMapper(BankDto bankDto) {
		Bank bank = new Bank();
		
		bank.setBankName(bankDto.getBankName());
		bank.setBranch(bankDto.getBranch());
		bank.setIfscCode(bankDto.getIfscCode());
		return bank;
	}

	@Override
	public List<BankDto> getAllbankDto() {
		
		List<Bank> banks = bankRepository.findAll();
		List<BankDto> bankDtos = new ArrayList<>();
		
		for(Bank bank : banks)
		{
			BankDto bankDto = bankDtoMapper(bank);
			bankDtos.add(bankDto);
		}
		return bankDtos;
	}

	@Override
	public BankDto getBankDtobyId(int id) {
		
		Optional<Bank> optionalBank = bankRepository.findById(id);
		if(optionalBank.isEmpty())
			return null;
	
		BankDto bankDto = bankDtoMapper(optionalBank.get()); 
		
		return bankDto;
	}

	@Override
	public BankDto assignSalaryAccountToBank(int bankId, List<Integer> accountNumbers) {
		
		Bank bank = bankRepository.findById(bankId).orElseThrow(()-> new NullPointerException("bank does not Exist"));
		
		List<SalaryAccount> existingAccounts = bank.getAccounts();
		List<SalaryAccount> accountToAdd = new ArrayList<>();
		
		if(existingAccounts==null)
			existingAccounts = new ArrayList<>();
		
		accountNumbers.forEach(accountNumber ->{
			SalaryAccount salaryAccount = salaryAccountRepo.findById(accountNumber).orElseThrow(()-> new NullPointerException("account not found"));
			salaryAccount.setBank(bank);
			accountToAdd.add(salaryAccount);
			
		});
		
		existingAccounts.addAll(accountToAdd);
		bank.setAccounts(existingAccounts);
		
		bankRepository.save(bank);
		return bankDtoMapper(bank);
	}
	
	
}
