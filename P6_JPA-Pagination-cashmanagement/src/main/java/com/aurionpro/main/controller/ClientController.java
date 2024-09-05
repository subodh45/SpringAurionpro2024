package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.ClientDto;
import com.aurionpro.main.entity.Client;
import com.aurionpro.main.entity.Employee;
import com.aurionpro.main.service.ClientService;


@RestController
@RequestMapping("/cashmanagementapp")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping("clients")
	public ClientDto addClientDto(@RequestBody ClientDto clientDto)
	{
		return clientService.addClientDto(clientDto);
		
	}
	
//	@PutMapping("clients")
//	public String updateClient(@RequestBody Client client)
//	{
//		clientService.updateClient(client);;
//		return "Client updated  Successfully.";
//	}
	
	@GetMapping("clients")
	public ResponseEntity<Page<Client>> getAllClient(@RequestParam(required = false) String name,@RequestParam int pageSize ,@RequestParam int pageNumber)
	{
		if(name !=null)
		   return  ResponseEntity.ok( clientService.getAllClientbycompanyName(name, pageSize, pageNumber));
		
		return  ResponseEntity.ok( clientService.getAllClient(pageSize, pageNumber));

	}
	
	@GetMapping("clientDtos")
	public ResponseEntity<List<ClientDto>> getAllClient()
	{		
		return  ResponseEntity.ok( clientService.getAllClientDto());

	}
	
	@PutMapping("/clients/employees")
	public ResponseEntity<List<Employee>>  allocateEmployee(@RequestParam int clientId , @RequestBody List<Integer> employeeIds)
	{
		return ResponseEntity.ok(clientService.allocateEmplooyee(clientId, employeeIds));
	}
	
	
}
