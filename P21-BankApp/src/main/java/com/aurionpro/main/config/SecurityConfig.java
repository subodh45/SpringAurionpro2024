package com.aurionpro.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;

import com.aurionpro.main.security.JwtAuthenticationEntryPoint;
import com.aurionpro.main.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@EnableMethodSecurity
@Configuration
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	static PasswordEncoder passwordEncoder()
	{
	  return new BCryptPasswordEncoder();	
	}
	
	  @Bean
	  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	    return configuration.getAuthenticationManager();
	  }
	
	  @Bean
	  SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
	    http.csrf(csrf -> csrf.disable()).cors(withDefaults());
	    http.sessionManagement(session -> session.sessionCreationPolicy(STATELESS));
	    
	    http.authorizeHttpRequests(request -> request.requestMatchers("/bankapp/register").permitAll());
	    http.authorizeHttpRequests(request -> request.requestMatchers("/bankapp/login").permitAll());
	    
	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET, "/bankapp/customers/**"));
	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/bankapp/customers/**"));
	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.PUT, "/bankapp/customers/**"));
	    http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.DELETE, "/bankapp/customers/**"));
	    http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));
	    
	    
	    http.authorizeHttpRequests(request -> 
        request.requestMatchers(HttpMethod.GET, "/bankapp/accounts/**"));
        http.authorizeHttpRequests(request -> 
        request.requestMatchers(HttpMethod.POST, "/bankapp/accounts/**"));
        http.authorizeHttpRequests(request -> 
        request.requestMatchers(HttpMethod.PUT, "/bankapp/accounts/**"));
        http.authorizeHttpRequests(request -> 
        request.requestMatchers(HttpMethod.DELETE, "/bankapp/accounts/**"));
    
        http.authorizeHttpRequests(request -> 
        request.requestMatchers(HttpMethod.GET, "/bankapp/transactions/**"));
        http.authorizeHttpRequests(request -> 
        request.requestMatchers(HttpMethod.POST, "/bankapp/transactions/**"));
        http.authorizeHttpRequests(request -> 
        request.requestMatchers(HttpMethod.PUT, "/bankapp/transactions/**"));
        http.authorizeHttpRequests(request -> 
        request.requestMatchers(HttpMethod.DELETE, "/bankapp/transactions/**"));
    
    
	    http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
	    
	    return http.build();
	  }
}
