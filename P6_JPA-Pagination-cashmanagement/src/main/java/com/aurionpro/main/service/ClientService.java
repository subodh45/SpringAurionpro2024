package com.aurionpro.main.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aurionpro.main.dto.ClientDto;
import com.aurionpro.main.entity.Client;
import com.aurionpro.main.entity.Employee;

public interface ClientService {

	ClientDto addClientDto(ClientDto clientDto);
	//ClientDto updateClientDto(ClientDto clientDto);
	
	Page<Client> getAllClient(int pageSize,int pageNumber);
	List<ClientDto> getAllClientDto();
	
	Page<Client> getAllClientbycompanyName(String name ,int pageSize,int pageNumber);
	
	List<Employee> allocateEmplooyee(int clientId , List<Integer> employeeIds);
	
}
