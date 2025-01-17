package com.aurionpro.main.service;

import java.util.List;

import com.aurionpro.main.dto.TransactionDto;
import com.aurionpro.main.entity.TransactionType;

public interface TransactionService {

	TransactionDto performTransaction(long senderAccountNumber,long receiverAccountNumber, TransactionType transactionType, int amount);
	
	
	List<TransactionDto> getAllTransactions();
}
