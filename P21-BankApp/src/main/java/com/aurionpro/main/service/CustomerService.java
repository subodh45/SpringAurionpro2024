package com.aurionpro.main.service;

import java.util.List;

import com.aurionpro.main.dto.AccountDto;
import com.aurionpro.main.dto.CustomerDto;
import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.dto.TransactionDto;

public interface CustomerService {

	//CustomerDto addCustomerDto(CustomerDto customerDto);
	
	//CustomerDto customerLogin(CustomerDto customerDto);
	
	List<CustomerDto> getAllCustomerDto();
	PageResponseDto<CustomerDto> getAllCustomers( int pageNumber, int pageSize);
	
	CustomerDto getCustomerbyEmail(String email);
	
	CustomerDto updateCustomer(CustomerDto customerDto);
	
	List<AccountDto> getAccountsOfCustomerId(int customerId);
	
	AccountDto createCustomerAccount(AccountDto accounDto); 
	
}
