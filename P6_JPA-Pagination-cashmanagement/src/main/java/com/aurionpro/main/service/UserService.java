package com.aurionpro.main.service;

import java.util.List;

import com.aurionpro.main.dto.UserDto;

public interface UserService {

	UserDto adduser(UserDto userDto);
	
	List<UserDto> getAllUser();
	
	UserDto getuserById(int userId);
}
