package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.main.dto.UserDto;
import com.aurionpro.main.entity.User;
import com.aurionpro.main.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDto adduser(UserDto userDto) {
		User user = userMapper(userDto);
		userRepo.save(user);
		
		userDto = userDtoMapper(user);
		return userDto;
	}

	public User userMapper(UserDto userDto)
	{   User user = new User();
	    user.setUsername(userDto.getUsername());
	    user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	public UserDto userDtoMapper(User user)
	{
		UserDto userDto = new UserDto();
	   
		userDto.setPassword(user.getPassword());
		userDto.setUsername(user.getUsername());
		if(user.getUserId()>0)
			userDto.setUserId(user.getUserId());
		
		return userDto;
	}
	@Override
	public List<UserDto> getAllUser() {
		
		List<User> users = userRepo.findAll();
		List<UserDto> userDtos = new ArrayList<>();
		users.forEach(user->{
			UserDto userDto = userDtoMapper(user);
			userDtos.add(userDto);
		});
		
		return userDtos;
	}

	@Override
	public UserDto getuserById(int userId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new NullPointerException("user not found"));
		
		UserDto userDto = userDtoMapper(user);
		return userDto;
	}

}
