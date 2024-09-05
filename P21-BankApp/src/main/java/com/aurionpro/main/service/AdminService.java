package com.aurionpro.main.service;

import java.util.List;

import com.aurionpro.main.dto.AccountDto;
import com.aurionpro.main.dto.CustomerDto;
import com.aurionpro.main.dto.TransactionDto;
import com.aurionpro.main.entity.Admin;

public interface AdminService {

	Admin addAdmin(String adminName, String password);
	
	List<TransactionDto> getAllTransaction();
	
	AccountDto addAccount(AccountDto accounDto); 
	
	List<AccountDto> getAllAccounts();
	
    //CustomerDto addCustomerDto(CustomerDto customerDto);
	
	List<CustomerDto> getAllCustomerDto();
}
