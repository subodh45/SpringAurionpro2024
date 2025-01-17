package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.main.Repository.RoleRepository;
import com.aurionpro.main.Repository.UserRepository;
import com.aurionpro.main.dto.logindto;
import com.aurionpro.main.dto.registrationdto;
import com.aurionpro.main.entity.Admin;
import com.aurionpro.main.entity.Customer;
import com.aurionpro.main.entity.Role;
import com.aurionpro.main.entity.User;
import com.aurionpro.main.exception.SQLIntegrityConstraintViolationException;
import com.aurionpro.main.exception.UserApiException;
import com.aurionpro.main.security.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	@Override
	public User register(registrationdto registrationDto) {
     
	    if (userRepo.existsByUsername(registrationDto.getUsername())) {
	        throw new UserApiException(HttpStatus.BAD_REQUEST, "Username already exists.");
	    }

	    // Determine the role
	    Role userRole = roleRepo.findByRolename(registrationDto.getRole())
	                            .orElseThrow(() -> new UserApiException(HttpStatus.BAD_REQUEST, "Role not found."));

	    // Create a new User instance based on the role
	    User user;
	    if ("ROLE_CUSTOMER".equalsIgnoreCase(registrationDto.getRole())) {
	        Customer customer = new Customer();
	        customer.setFirstName(registrationDto.getFirstName());
	        customer.setLastName(registrationDto.getLastName());
	        customer.setEmail(registrationDto.getEmail());
	        user = customer;
	    } else if ("ROLE_ADMIN".equalsIgnoreCase(registrationDto.getRole())) {
	        Admin admin = new Admin();
	        admin.setAdminName(registrationDto.getAdminName());
	        user = admin;
	    } else {
	        throw new UserApiException(HttpStatus.BAD_REQUEST, "Invalid role specified.");
	    }

	    // Set common fields
	    user.setUsername(registrationDto.getUsername());
	    user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

	    // Assign the role
	    List<Role> roles = new ArrayList<>();
	    roles.add(userRole);
	    user.setRoles(roles);

	    User saveUser;
	    try {
	     saveUser = userRepo.save(user);
	    }
	    catch(DataIntegrityViolationException ex)
	    {
	    	throw new SQLIntegrityConstraintViolationException("Invalid user details.");
	    }
	    
	    logger.info("User added Successfully " +user.getUserId() );
	    // Save the user (Customer or Admin)
	    return saveUser;
         
	}
	
	@Override
	public String login(logindto loginDto) {
	    try {
	        // Authenticate the user
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

	        // Set the authentication context
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Fetch the authenticated user details
	        User user = userRepo.findByUsername(loginDto.getUsername())
	                .orElseThrow(() -> new UserApiException(HttpStatus.NOT_FOUND, "User not found"));

	        // Check the user's roles
	        boolean isCustomer = user.getRoles().stream()
	                .anyMatch(role -> role.getRolename().equalsIgnoreCase("ROLE_CUSTOMER"));
	        boolean isAdmin = user.getRoles().stream()
	                .anyMatch(role -> role.getRolename().equalsIgnoreCase("ROLE_ADMIN"));

	        // Generate the JWT token
	        String token = tokenProvider.generateToken(authentication);

	        // Return a response with the role information and the token
	        if (isCustomer) {
	        	logger.info("customer login Successfully " + user.getUserId() );
	            return "Customer login successful. Token: " + token;
	        } else if (isAdmin) {
	        	logger.info("admin login Successfully " + user.getUserId() );
	            return "Admin login successful. Token: " + token;
	        } else {
	        	logger.info("customer login failed with id  " + user.getUserId() );
	            throw new UserApiException(HttpStatus.UNAUTHORIZED, "User does not have a valid role");
	        }
	    } catch (BadCredentialsException e) {
	    	logger.info("customer login failed." );
	        throw new UserApiException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect");
	    }
	}



//	@Override
//	public User register(registrationdto registrationDto) {
//
//		if (userRepo.existsByUsername(registrationDto.getUsername()))
//			throw new UserApiException(HttpStatus.BAD_REQUEST, "username Already exist.");
//
//		User user = new User();
//
//		user.setUsername(registrationDto.getUsername());
//		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
//
//		List<Role> roles = new ArrayList<Role>();
//
//		Role userRole = roleRepo.findByRolename(registrationDto.getRole()).get();
//
//		roles.add(userRole);
//		user.setRoles(roles);
//		return userRepo.save(user);
//	}

//	@Override
//	public String login(logindto loginDto) {
//		try {
//			Authentication authentication = authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			String token = tokenProvider.generateToken(authentication);
//			return token;
//		} catch (BadCredentialsException e) {
//			throw new UserApiException(HttpStatus.NOT_FOUND, "Username or Password is incorrect");
//		}
//	}
//
////	@Override
////	public String login(logindto loginDto) {
////		
////		return null;
////	}

}
