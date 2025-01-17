package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.main.Repository.AccountRepository;
import com.aurionpro.main.Repository.CustomerRepository;
import com.aurionpro.main.Repository.TransactionRepository;
import com.aurionpro.main.dto.AccountDto;
import com.aurionpro.main.dto.CustomerDto;
import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.entity.Account;
import com.aurionpro.main.entity.Customer;
import com.aurionpro.main.exception.AccessDeniedException;
import com.aurionpro.main.exception.AccountNotCreatedException;
import com.aurionpro.main.exception.CustomerNotFoundException;
import com.aurionpro.main.exception.DataIntegrityViolationException;
import com.aurionpro.main.security.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private TransactionRepository  transactionRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;	
	
	private AccountServiceImpl accountServiceImpl = new AccountServiceImpl();
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	
//	@Override
//	public CustomerDto addCustomerDto(CustomerDto customerDto) {
//		
//		Customer customer = toCustomerMapper(customerDto);
//		customerRepo.save(customer);
//		customerDto = toCustomerDtoMapper(customer);
//		logger.info(" " +user.getUserId() );
//		return customerDto ;
//	}
	
//	@Override
//	public CustomerDto customerLogin(CustomerDto customerDto) {
//		
//		Optional<Customer> customerOpt = Optional.ofNullable(customerRepo.findByEmail(customerDto.getEmail()));
//		if (customerOpt.isEmpty()) {
//			throw new CustomerNotFoundException("Customer with email "+ customerDto.getEmail() +" is  Not found.");	
//		}
//		Customer customer = customerOpt.get();
//		
//		if(!customer.getPassword().equals(customerDto.getPassword()))
//			throw new CustomerNotFoundException("Wrong Password");
//		
//		customerDto= toCustomerDtoMapper(customer);
//		
//		return customerDto;
//	}	
	

	private CustomerDto toCustomerDtoMapper(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		
//		customerDto.setCustomerId(customer.getCustomerId());       //////////////////////////////////
		customerDto.setCustomerId(customer.getUserId());       
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPassword(customer.getPassword());
		customerDto.setUsername(customer.getUsername());
		
		return customerDto;
	}

	private Customer toCustomerMapper(CustomerDto customerDto) {
		
		Customer customer = new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
		customer.setUsername(customerDto.getUsername());
		if(customerDto.getCustomerId()>0)
			//customer.setCustomerId(customerDto.getCustomerId());  ///////////////////
			customer.setUserId(customerDto.getCustomerId());
		return customer;
	}

	@Override
	public List<CustomerDto> getAllCustomerDto() {	
		
		List<Customer> customers = customerRepo.findAll();
		logger.info("Admin get All Customer. ");
		return customers.stream()
			.map(this::toCustomerDtoMapper)
			.collect(Collectors.toList());
	}

	@Override
	public CustomerDto getCustomerbyEmail(String email) {
		Optional<Customer> customerOpt = Optional.ofNullable(customerRepo.findByEmail(email));
		if (customerOpt.isPresent()) {
			return toCustomerDtoMapper(customerOpt.get());
		} else {
			throw new CustomerNotFoundException("Customer with email "+ email +" is  Not found.");
		}
		
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
	
		Customer existingCustomer = customerRepo.findById(customerDto.getCustomerId())
		        .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + customerDto.getCustomerId() + " not found"));

		// Retrieve the currently authenticated user
	    String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
	    
	
		if (existingCustomer == null || !existingCustomer.getUsername().equals(loggedInUsername)) {
	        throw new AccessDeniedException("You do not have access to update this customers.");
	    }
		if(customerDto.getFirstName() !=null)
	      existingCustomer.setFirstName(customerDto.getFirstName());
		if(customerDto.getLastName()!= null)
	    existingCustomer.setLastName(customerDto.getLastName());
		if(customerDto.getEmail()!=null)
	    existingCustomer.setEmail(customerDto.getEmail());
		if(customerDto.getPassword()!=null)
	    existingCustomer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
		if(customerDto.getUsername()!=null)
	    existingCustomer.setUsername(customerDto.getUsername());
	    
		Customer updatedCustomer;
		try {
	     updatedCustomer = customerRepo.save(existingCustomer);
		}catch(DataIntegrityViolationException ex)
		{
			throw new DataIntegrityViolationException("Data you are entering is already used.");
		}

	    // Invalidate the old token
	    jwtTokenProvider.invalidateToken(existingCustomer.getUsername());

	    logger.info("Customer updated successfully. " +updatedCustomer.getUserId() );
	    return toCustomerDtoMapper(updatedCustomer);
	}

	@Override
	public List<AccountDto> getAccountsOfCustomerId(int customerId) {
	
		Customer customer = customerRepo.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer of Id "+ customerId +" not found."));
		
		// Retrieve the currently authenticated user
	    String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
	    
		if (customer == null || !customer.getUsername().equals(loggedInUsername)) {
	        throw new AccessDeniedException("You do not have access to view accounts of this customers.");
	    }
		
		List<Account> accounts = customer.getAccounts();
		if(accounts == null)
			throw new AccountNotCreatedException("Customer don't have any Account ");
		
		List<AccountDto> accountDtos = new ArrayList<>();
		AccountServiceImpl accountServiceImpl = new AccountServiceImpl();
		accounts.forEach(account -> {
			AccountDto accountDto = accountServiceImpl.toAccountDtoMapper(account);
			accountDtos.add(accountDto);
		});
		
		 logger.info("Get customer Account.. " );
		return accountDtos;
	}

	@Override
	public AccountDto createCustomerAccount(AccountDto accountDto) {
		
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

	@Override
	public PageResponseDto<CustomerDto> getAllCustomers( int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);				
		Page<Customer> customerPage = customerRepo.findAll(pageable);
		
		PageResponseDto<CustomerDto>  pageResponseDto = new  PageResponseDto();
		
		Page<CustomerDto> customerDtoPage = customerPage.map(this:: toCustomerDtoMapper);
		
		pageResponseDto.setPageNumber(customerDtoPage.getNumber());
		pageResponseDto.setPageSize(customerDtoPage.getSize());
		pageResponseDto.setContent(customerDtoPage.getContent());
		
		 return pageResponseDto;
	}

	
} 

	
