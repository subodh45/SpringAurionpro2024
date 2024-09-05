package com.aurionpro.main.service;

import java.util.List;

import com.aurionpro.main.dto.RoleDto;

public interface RoleService {

	
RoleDto addRole(RoleDto roleDto);
	
	List<RoleDto> getAllRoler();
	
	RoleDto getRoleById(int roleId);
}
