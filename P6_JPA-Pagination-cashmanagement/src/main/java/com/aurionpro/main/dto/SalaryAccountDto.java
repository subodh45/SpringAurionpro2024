package com.aurionpro.main.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SalaryAccountDto {

    private int accountNumber;
	private String accountHolderName;
}
