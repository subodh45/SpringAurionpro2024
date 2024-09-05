package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.main.dto.ClientDto;
import com.aurionpro.main.entity.Client;
import com.aurionpro.main.entity.Employee;
import com.aurionpro.main.repository.ClientRepository;
import com.aurionpro.main.repository.EmployeeRepository;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private  EmployeeRepository employeeRepo;
	
	@Override
	public ClientDto addClientDto(ClientDto clientDto) {
		Client client = ClientMapper(clientDto);
		clientRepo.save(client);
		
         clientDto = ClientDtoMapper(client);
         
		return clientDto;
	}

	private ClientDto ClientDtoMapper(Client client) {
		ClientDto clientDto = new ClientDto();
		
		clientDto.setClientId(client.getClientId());
		clientDto.setCompanyName(client.getCompanyName());
		clientDto.setRegistrationNumber(client.getRegistrationNumber());
		clientDto.setContactPerson(client.getContactPerson());
		clientDto.setContactEmail(client.getContactEmail());
		clientDto.setContactNumber(client.getContactNumber());
		clientDto.setAddress(client.getAddress());
		clientDto.setStatus(client.getStatus());
		clientDto.setCreationalDate(client.getCreationalDate());
		clientDto.setKycStatus(client.getKycStatus());
		return clientDto;
	}

	private Client ClientMapper(ClientDto clientDto) {
		Client client = new Client();
		
		client.setCompanyName(clientDto.getCompanyName());
		client.setRegistrationNumber(clientDto.getRegistrationNumber());
		client.setContactPerson(clientDto.getContactPerson());
		client.setContactEmail(clientDto.getContactEmail());
		client.setContactNumber(clientDto.getContactNumber());
		client.setAddress(clientDto.getAddress());
		client.setStatus(clientDto.getStatus());
		client.setCreationalDate(clientDto.getCreationalDate());
		client.setKycStatus(clientDto.getKycStatus());
		
		return client;
	}

	//put update method
//	@Override
//	public ClientDto updateClientDto(ClientDto clientDto) {
//		
//		//clientRepo.save(clientDto);
//		return null;
//	}

	@Override
	public Page<Client> getAllClient(int pageSize,int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return clientRepo.findAll(pageable);
	}

	@Override
	public Page<Client> getAllClientbycompanyName(String name, int pageSize, int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return clientRepo.findBycompanyName(name, pageable);
	}

	@Override
	public List<ClientDto> getAllClientDto() {
		
		List<Client> clients = clientRepo.findAll();
		List<ClientDto> clientDtos = new ArrayList<>();
		
		for(Client c : clients )
		{
			ClientDto clientDto= ClientDtoMapper(c);
			clientDtos.add(clientDto);
		}
		return clientDtos;
	}

	@Override
	public List<Employee> allocateEmplooyee(int clientId, List<Integer> employeeIds) {
		Optional<Client> optionalClient = clientRepo.findById(clientId);
		if(optionalClient.isEmpty())
			throw new NullPointerException("client not found");
		Client client = optionalClient.get();
		List<Employee> dbemployees = client.getEmployees();
		List<Employee>employeeToAdd = new ArrayList<>();
		
		if(dbemployees == null)
			dbemployees = new ArrayList<>();
		
		employeeIds.forEach(employeeId -> {
			
			Optional<Employee> optionalEmployee =employeeRepo.findById(employeeId);
			if(optionalEmployee.isEmpty())
				throw new NullPointerException("client not found");
			
			Employee employee =  optionalEmployee.get();
			employee.setClient(client);
			
			employeeToAdd.add(employee);				
		});
		
		dbemployees.addAll(employeeToAdd);
		client.setEmployees(dbemployees);
		
		clientRepo.save(client);
				
		return employeeToAdd;
	}

}
