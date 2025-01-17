package com.aurionpro.main.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aurionpro.main.Repository.UserRepository;
import com.aurionpro.main.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username).
				       orElseThrow(()-> new UsernameNotFoundException("username Not found."));
		
		Set<GrantedAuthority> authorities = user
				     .getRoles()
				     .stream()
				     .map((role)-> new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toSet());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),
				    user.getPassword(),
				    authorities
				);
	}

}
