package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.UserDto;
import com.aurionpro.main.service.UserService;

@RestController
@RequestMapping("/cashmanagementapp")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto)
	{
		return ResponseEntity.ok(userService.adduser(userDto));
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		return ResponseEntity.ok(userService.getAllUser());
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int  userId)
	{
		return ResponseEntity.ok(userService.getuserById(userId));
	}
}
