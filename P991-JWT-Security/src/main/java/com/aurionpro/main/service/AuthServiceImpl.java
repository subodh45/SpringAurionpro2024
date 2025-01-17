package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
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
import com.aurionpro.main.entity.Role;
import com.aurionpro.main.entity.User;
import com.aurionpro.main.exception.UserApiException;
import com.aurionpro.main.security.JwtTokenProvider;

@Service
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

	@Override
	public User register(registrationdto registrationDto) {

		if (userRepo.existsByUsername(registrationDto.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST, "username Already exist.");

		User user = new User();

		user.setUsername(registrationDto.getUsername());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

		List<Role> roles = new ArrayList<Role>();

		Role userRole = roleRepo.findByRolename(registrationDto.getRole()).get();

		roles.add(userRole);
		user.setRoles(roles);
		return userRepo.save(user);
	}

	@Override
	public String login(logindto loginDto) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication);
			return token;
		} catch (BadCredentialsException e) {
			throw new UserApiException(HttpStatus.NOT_FOUND, "Username or Password is incorrect");
		}
	}

//	@Override
//	public String login(logindto loginDto) {
//		
//		return null;
//	}

}
