package com.aurionpro.main.dto;

import java.time.LocalDate;
import com.aurionpro.main.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class TransactionDto {

    private int transactionId;
	private long senderAccountNumber;
	private long receiverAccountNumber;	
	private TransactionType transactionType;
	private LocalDate date;
	private int amount;
}
