package com.aurionpro.main.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CustomerDto {

    private int customerId;   //here customerId is userId
	private String firstName;
    private String lastName;
	private String email;
	private String username;
	private String password;
}
