package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aurionpro.main.Repository.AccountRepository;
import com.aurionpro.main.Repository.CustomerRepository;
import com.aurionpro.main.Repository.TransactionRepository;
import com.aurionpro.main.dto.AccountDto;
import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.dto.TransactionDto;
import com.aurionpro.main.entity.Account;
import com.aurionpro.main.entity.Customer;
import com.aurionpro.main.entity.Transaction;
import com.aurionpro.main.exception.AccessDeniedException;
import com.aurionpro.main.exception.AccountNotFoundException;
import com.aurionpro.main.exception.CustomerNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
 
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired 
	private CustomerRepository customerRepo;
	
	@Autowired
	private TransactionRepository  transactionRepo;
	
	TransactionServiceImpl transactionServiceImpl = new TransactionServiceImpl();
	
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Override
	public AccountDto addAccount(AccountDto accountDto) {
		
		 Customer customer = customerRepo.findById(accountDto.getCustomerId())
		            .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + accountDto.getCustomerId() + " not found"));
		
		  List<Account> existingAccount = customer.getAccounts();
		  
		  if(existingAccount == null)
			  existingAccount = new ArrayList<>();
		  
		  Account account = toAccountMapper(accountDto);
		  
		 existingAccount.add(account);
		 customer.setAccounts(existingAccount);
		 customer =customerRepo.save(customer);
	     account = accountRepo.save(account);
	     
	     logger.info("Customer account created. " +account.getAccountNumber());
		 return toAccountDtoMapper(account);
		
	}
	
	public Account toAccountMapper(AccountDto accountDto) {
	    Account account = new Account();
	    account.setAccountNumber(accountDto.getAccountNumber());
	    account.setBalance(accountDto.getBalance());
	    Customer customer = customerRepo.findById(accountDto.getCustomerId())
	            .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + accountDto.getCustomerId() + " not found"));
	
	    account.setCustomer(customer);
	    
	    return account;
	}

	public AccountDto toAccountDtoMapper(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setBalance(account.getBalance());
		Customer customer = account.getCustomer();
		//accountDto.setCustomerId(customer.getCustomerId());    ///////////////////////////
		accountDto.setCustomerId(customer.getUserId());
		return accountDto;
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		 List<Account> accounts = accountRepo.findAll();
		    return accounts.stream()
		                   .map(this::toAccountDtoMapper)
		                   .collect(Collectors.toList());
	}

	@Override
	public AccountDto getAccountbyAccountNumber(long accountNumber) {
		 Account account = accountRepo.findById(accountNumber)
		            .orElseThrow(() -> new AccountNotFoundException("Account with number " + accountNumber + " not found"));
		    return toAccountDtoMapper(account);
	}

	@Override
	public List<TransactionDto> getAccountTransaction(long accountNumber) {
		
		Account account = accountRepo.findById(accountNumber)
	            .orElseThrow(() -> new AccountNotFoundException("Account with number " + accountNumber + " not found"));
		
		// Retrieve the currently authenticated user
	    String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
	    
		Customer customer = account.getCustomer();
	
		if (customer == null || !customer.getUsername().equals(loggedInUsername)) {
	        throw new AccessDeniedException("You do not have access to view transactions for this account");
	    }
	
		List<Transaction> transactionList = account.getTransactions();
		
		
		List<TransactionDto> transactionDtoList = new ArrayList<>();
		
		transactionList.forEach(transaction ->{
			TransactionDto transactionDto= transactionServiceImpl.toTransactionDtoMapper(transaction);
			 transactionDtoList.add(transactionDto);
		});
			
		 logger.info("Customer get transactions of account " + accountNumber );
		return transactionDtoList;
	}

	
	//page
	@Override
	public PageResponseDto<TransactionDto> getAccountTransaction2(long accountNumber, int pageNumber, int pageSize) {
		
		Account account = accountRepo.findById(accountNumber).orElseThrow(() -> new AccountNotFoundException("account not found with number "+ accountNumber));
		
		// Retrieve the currently authenticated user
	    String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
	    
		Customer customer = account.getCustomer();
	
		if (customer == null || !customer.getUsername().equals(loggedInUsername)) {
	        throw new AccessDeniedException("You do not have access to view transactions for this account");
	    }
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);				
		Page<Transaction> transactionPage = transactionRepo.findByAccount(account, pageable);
		
		PageResponseDto<TransactionDto>  pageResponseDto = new  PageResponseDto();
		
		Page<TransactionDto> transactionDtoPage = transactionPage.map(this:: toTransactionDtoMapper);
		
		pageResponseDto.setPageNumber(transactionDtoPage.getNumber());
		pageResponseDto.setPageSize(transactionDtoPage.getSize());
		pageResponseDto.setContent(transactionDtoPage.getContent());
		
		logger.info("Customer get transactions of account " + accountNumber );
		return pageResponseDto ;
	}
	
	public TransactionDto toTransactionDtoMapper(Transaction transaction) {
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setAmount(transaction.getAmount());
		transactionDto.setDate(transaction.getDate());
		transactionDto.setReceiverAccountNumber(transaction.getReceiverAccountNumber());
		transactionDto.setSenderAccountNumber(transaction.getSenderAccountNumber());
		transactionDto.setTransactionId(transaction.getTransactionId());
		transactionDto.setTransactionType(transaction.getTransactionType());
		return transactionDto;
	}

}

