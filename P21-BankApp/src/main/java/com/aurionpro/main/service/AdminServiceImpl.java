package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.main.Repository.AccountRepository;
import com.aurionpro.main.Repository.AdminRepository;
import com.aurionpro.main.Repository.CustomerRepository;
import com.aurionpro.main.Repository.TransactionRepository;
import com.aurionpro.main.dto.AccountDto;
import com.aurionpro.main.dto.CustomerDto;
import com.aurionpro.main.dto.TransactionDto;
import com.aurionpro.main.entity.Account;
import com.aurionpro.main.entity.Admin;
import com.aurionpro.main.entity.Customer;
import com.aurionpro.main.exception.CustomerNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired 
	private CustomerRepository customerRepo;
	
	@Autowired
	private TransactionRepository  transactionRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	TransactionServiceImpl transactionServiceImpl = new TransactionServiceImpl();
	AccountServiceImpl  accountServiceImpl = new AccountServiceImpl();
	CustomerServiceImpl customerServiceImpl =new CustomerServiceImpl();

	@Override
	public Admin addAdmin(String adminName, String password) {
		 Admin admin = new Admin();
		 admin.setAdminName(adminName);
		 admin.setPassword(password);
		admin = adminRepo.save(admin);
		
		return admin;	
	}

	//get All Transaction
	@Override
	public List<TransactionDto> getAllTransaction() {

		return transactionServiceImpl.getAllTransactions();
	}

	//Generate New Account of Customer 
	@Override
	public AccountDto addAccount(AccountDto accountDto) {
		 Customer customer = customerRepo.findById(accountDto.getCustomerId())
		            .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + accountDto.getCustomerId() + " not found"));
		
		  List<Account> existingAccount = customer.getAccounts();
		  
		  if(existingAccount == null)
			  existingAccount = new ArrayList<>();
		  
		  Account account = accountServiceImpl.toAccountMapper(accountDto);
		  
		 existingAccount.add(account);
		 customer.setAccounts(existingAccount);
		 customer =customerRepo.save(customer);
	     account = accountRepo.save(account);
	     
	     
		 return accountServiceImpl.toAccountDtoMapper(account);
	}

	//see all account List 
	@Override
	public List<AccountDto> getAllAccounts() {
		 List<Account> accounts = accountRepo.findAll();
		    return accounts.stream()
		                   .map(this::toAccountDtoMapper)
		                   .collect(Collectors.toList());
	}

//	@Override
//	public CustomerDto addCustomerDto(CustomerDto customerDto) {
//		
//		return customerServiceImpl.addCustomerDto(customerDto);
//	}

	@Override
	public List<CustomerDto> getAllCustomerDto() {

		return customerServiceImpl.getAllCustomerDto();
	}

	public AccountDto toAccountDtoMapper(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setBalance(account.getBalance());
		Customer customer = account.getCustomer();
		//accountDto.setCustomerId(customer.getCustomerId());   ///////////////////////////
		accountDto.setCustomerId(customer.getUserId()); 
		return accountDto;
	}

}
