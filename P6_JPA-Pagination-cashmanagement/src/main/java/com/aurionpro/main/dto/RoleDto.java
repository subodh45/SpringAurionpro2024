package com.aurionpro.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RoleDto {

    private int roleId;

	private String roleName;
}
