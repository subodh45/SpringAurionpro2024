package com.aurionpro.main.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aurionpro.main.Repository.AccountRepository;
import com.aurionpro.main.Repository.TransactionRepository;
import com.aurionpro.main.dto.TransactionDto;
import com.aurionpro.main.entity.Account;
import com.aurionpro.main.entity.Customer;
import com.aurionpro.main.entity.Transaction;
import com.aurionpro.main.entity.TransactionType;
import com.aurionpro.main.exception.AccessDeniedException;
import com.aurionpro.main.exception.InsufficientBalanceException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepo;
	
	@Autowired 
	private AccountRepository accountRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	
	@Override
	public TransactionDto performTransaction(long senderAccountNumber, long receiverAccountNumber,
			TransactionType transactionType, int amount) {
		
		 Account account =  accountRepo.findById(senderAccountNumber)
				 .orElseThrow(()-> new com.aurionpro.main.exception.AccountNotFoundException("Sender account is invalid."));
		
		// Retrieve the currently authenticated user
	    String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
	    
		Customer customer = account.getCustomer();
	
		if (customer == null || !customer.getUsername().equals(loggedInUsername)) {
	        throw new AccessDeniedException("You do not have access to perform  transactions for this account.");
	    }
		
		
		if(transactionType == TransactionType.DEBIT)
			return withdrawAmount(senderAccountNumber, receiverAccountNumber, transactionType, amount);
		else if(transactionType == TransactionType.CREDIT)
			return DepositeAmount(senderAccountNumber, receiverAccountNumber, transactionType, amount);
		else if(transactionType == TransactionType.TRANSFER)
			return transferAmount(senderAccountNumber, receiverAccountNumber, transactionType, amount);
		return null; 
	}
	
	@Transactional
	public TransactionDto DepositeAmount(long senderAccountNumber, long receiverAccountNumber,
			TransactionType transactionType, int amount)
	{     AccountServiceImpl accountServiceImpl = new AccountServiceImpl();
		 
		 Account fromAccount =  accountRepo.findById(senderAccountNumber)
				 .orElseThrow(()-> new com.aurionpro.main.exception.AccountNotFoundException("Sender account is invalid."));
		 
		 fromAccount.setBalance(fromAccount.getBalance()+amount);
		 Account updatedAccount = accountRepo.save(fromAccount);
		 
		 LocalDate date = LocalDate.now();
		 
		Transaction transaction= addTransaction( senderAccountNumber, receiverAccountNumber, transactionType,  date,amount ,updatedAccount);
		 TransactionDto transactionDto = toTransactionDtoMapper(transaction);
		 logger.info("Amount deposite  Successfully " +fromAccount.getAccountNumber());
		return transactionDto;
	}
	
	@Transactional
	public TransactionDto withdrawAmount(long senderAccountNumber, long receiverAccountNumber,
			TransactionType transactionType, int amount)
	{     AccountServiceImpl accountServiceImpl = new AccountServiceImpl();
		 
		 Account fromAccount =  accountRepo.findById(senderAccountNumber)
				 .orElseThrow(()-> new com.aurionpro.main.exception.AccountNotFoundException("Sender account is invalid."));
		 
		 if(fromAccount.getBalance()<amount)
			 throw new InsufficientBalanceException("insufficient Balance.");
		 
		 fromAccount.setBalance(fromAccount.getBalance()-amount);
		 Account updatedAccount = accountRepo.save(fromAccount);
		 
		 LocalDate date = LocalDate.now();
		 
		Transaction transaction= addTransaction( senderAccountNumber, receiverAccountNumber, transactionType,  date,amount ,updatedAccount);
		 TransactionDto transactionDto = toTransactionDtoMapper(transaction);
		 
		 logger.info("Amount withdraw Successfully " +fromAccount.getAccountNumber());
		return transactionDto;
	}
	
	@Transactional
	public TransactionDto transferAmount(long senderAccountNumber, long receiverAccountNumber,
			TransactionType transactionType, int amount)
	{     AccountServiceImpl accountServiceImpl = new AccountServiceImpl();
	
	      if(senderAccountNumber == receiverAccountNumber)
	    	  throw new com.aurionpro.main.exception.AccountNotFoundException("Sender and receiver account must be different.");
		 
		 Account fromAccount =  accountRepo.findById(senderAccountNumber)
				 .orElseThrow(()-> new com.aurionpro.main.exception.AccountNotFoundException("Sender account is invalid."));
		 
		 Account toAccount =  accountRepo.findById(receiverAccountNumber)
				 .orElseThrow(()-> new com.aurionpro.main.exception.AccountNotFoundException("receiver account is invalid."));
		 
		 if(fromAccount.getBalance()<amount)
			 throw new InsufficientBalanceException("insufficient Balance.");
		 
		 withdrawAmount(senderAccountNumber, receiverAccountNumber, transactionType.DEBIT, amount);
		 
		 TransactionDto transactionDto=DepositeAmount( receiverAccountNumber,senderAccountNumber, transactionType.CREDIT, amount);
		 
		return transactionDto;
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

	private Transaction addTransaction(long senderAccountNumber, long receiverAccountNumber, TransactionType transactionType,
			LocalDate date, int amount, Account updatedAccount) {
		
	      Transaction transaction = new Transaction();
	      transaction.setAccount(updatedAccount);
	      transaction.setAmount(amount);
	      transaction.setDate(date);
	      transaction.setReceiverAccountNumber(receiverAccountNumber);
	      transaction.setSenderAccountNumber(senderAccountNumber);
	      transaction.setTransactionType(transactionType);
	      
	      transaction = transactionRepo.save(transaction);
	
	      return transaction;
	}

	@Override
	public List<TransactionDto> getAllTransactions() {
	
		List<Transaction> transactions= transactionRepo.findAll();
		List<TransactionDto> transactionDtos = new ArrayList<>();
		
		transactions.forEach(transaction -> {
			TransactionDto transactionDto = toTransactionDtoMapper(transaction);
			transactionDtos.add(transactionDto);
			
		});
		
		return transactionDtos;
	}

}
