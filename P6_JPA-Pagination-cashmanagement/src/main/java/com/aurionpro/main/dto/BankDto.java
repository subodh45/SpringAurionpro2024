package com.aurionpro.main.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class BankDto {

    private int bankId;	
	private String bankName;
	private String branch;
	private String ifscCode;
}
