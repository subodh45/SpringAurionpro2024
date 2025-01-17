package com.aurionpro.main.dto;

import com.aurionpro.main.entity.Role;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class registrationdto {
	@NotBlank(message = "Username cannot be blank")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;
	@NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
	@NotBlank(message = "Role cannot be blank")
    private String role;
    
    // Customer specific fields
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only alphabetic characters") 
    private String firstName;
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only alphabetic characters")
    private String lastName;
	@Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;
    
    // Admin specific fields
    @Size(min = 2, max = 50, message = "Admin Name must be between 2 and 50 characters")
    private String adminName;

    // getters and setters
}




//@AllArgsConstructor
//@RequiredArgsConstructor
//@Data
//public class registrationdto {
//
//	private String username;
//	private String password;
//	private String role;
//	
//}
