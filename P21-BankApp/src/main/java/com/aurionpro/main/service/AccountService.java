package com.aurionpro.main.service;

import java.util.List;

import com.aurionpro.main.dto.AccountDto;
import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.dto.TransactionDto;

public interface AccountService {

	AccountDto addAccount(AccountDto accounDto); 
	
	List<AccountDto> getAllAccounts();
	
	AccountDto getAccountbyAccountNumber(long accountNumber);
	
	List<TransactionDto> getAccountTransaction(long accountNumber);
	
	PageResponseDto<TransactionDto> getAccountTransaction2(long accountNumber, int pageNumber, int pageSize);
		
}
