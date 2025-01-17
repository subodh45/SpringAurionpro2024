package com.aurionpro.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.JwtAuthResponse;
import com.aurionpro.main.dto.logindto;
import com.aurionpro.main.dto.registrationdto;
import com.aurionpro.main.entity.User;
import com.aurionpro.main.service.AuthService;


@RestController  
@RequestMapping("/api")  
public class LoginController {  
  
    @Autowired  
    private AuthService authService;  
  
    @PostMapping("/register")  
    public ResponseEntity<User> register(@RequestBody registrationdto registerDto) {  
        return ResponseEntity.ok(authService.register(registerDto));  
    }  
  
    @PostMapping("/login")  
    public ResponseEntity<JwtAuthResponse> login(@RequestBody logindto loginDto) {  
        String token = authService.login(loginDto);  
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();  
        jwtAuthResponse.setAccessToken(token);  
  
        return ResponseEntity.ok(jwtAuthResponse);  
    }  
}
