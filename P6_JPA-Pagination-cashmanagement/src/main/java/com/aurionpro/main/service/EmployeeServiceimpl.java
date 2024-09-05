package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.main.dto.EmployeeDto;
import com.aurionpro.main.entity.Client;
import com.aurionpro.main.entity.Employee;
import com.aurionpro.main.entity.SalaryAccount;
import com.aurionpro.main.repository.ClientRepository;
import com.aurionpro.main.repository.EmployeeRepository;

@Service
public class EmployeeServiceimpl implements EmployeeService {

	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public Employee addEmployee(Employee employee) {
		
		return employeeRepo.save(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepo.save(employee);
	}

	@Override
	public Page<Employee> getAllEmployee(int pageSize, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return employeeRepo.findAll(pageable);
		
	}

	@Override
	public Page<Employee> getAllEmployeebyfirstName(String name, int pageSize, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return employeeRepo.findByfirstName(name, pageable);
	}

	@Override
	public SalaryAccount getEmployeeSalaryAccount(int employeeId) {
		
		Employee employee =null;
		
		Optional<Employee> optionalEmployee =employeeRepo.findById(employeeId);
		
		if(!optionalEmployee.isPresent())
			return null;
		employee = optionalEmployee.get();
		
		return employee.getSalaryAccount();
	}

	@Override
	public Employee updateEmployeeSalaryAccount(int employeeId, SalaryAccount salaryAccount) {
		Employee employee =null;
		Optional<Employee> optionalEmployee =employeeRepo.findById(employeeId);
		
		if(!optionalEmployee.isPresent())
			return null;
		employee = optionalEmployee.get();
		
		employee.setSalaryAccount(salaryAccount);
	
		return employeeRepo.save(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployeeDto() {
		
		List<Employee> employees = employeeRepo.findAll();
		
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		
		for(Employee employee:employees )
		{
			EmployeeDto employeeDto = EmployeeDtoMapper(employee);
			employeeDtos.add(employeeDto);
		}
		
		return employeeDtos;
	}

	private EmployeeDto EmployeeDtoMapper(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
				
		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setPhoneNumber(employee.getPhoneNumber());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setPosition(employee.getPosition());
		employeeDto.setHireDate(employee.getHireDate());
		employeeDto.setSalary(employee.getSalary());
		employeeDto.setSalaryAccount(employee.getSalaryAccount());
		employeeDto.setStatus(employee.getStatus());
		
		return employeeDto;
	}
	
	private Employee EmployeeMapper(EmployeeDto employeeDto)
	{
		Employee employee = new Employee();
		
		
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setEmail(employeeDto.getEmail());
		employee.setPosition(employeeDto.getPosition());
		employee.setHireDate(employeeDto.getHireDate());
		employee.setSalary(employeeDto.getSalary());
		employee.setSalaryAccount(employeeDto.getSalaryAccount());
		employee.setStatus(employeeDto.getStatus());
			
		return employee;
	}

	@Override
	public EmployeeDto getEmployeeDtobyId(int Id) {
		EmployeeDto employeeDto = null;
		
		Optional<Employee> optionalEmployee = employeeRepo.findById(Id);
		
		if(optionalEmployee.isEmpty())
			return null;
		
		//employee = optionalEmployee.get();
		employeeDto = EmployeeDtoMapper(optionalEmployee.get());
		return employeeDto;
	}

	@Override
	public EmployeeDto addEmployeeDto(EmployeeDto employeeDto) {
		
		Employee employee = EmployeeMapper(employeeDto);
		employeeRepo.save(employee);
		
		employeeDto = EmployeeDtoMapper(employee);
		
		return employeeDto;
	}

	@Override
	public Employee assignClient(int employeeId, int clientId) {
		Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
		
		if(optionalEmployee.isEmpty())
			throw new NullPointerException("Employee not found ");
		Employee employee = optionalEmployee.get();
	
		Optional<Client> optionalClient = clientRepo.findById(clientId);
		
		if(optionalClient.isEmpty())
			throw new NullPointerException("no client Found");
		Client client = optionalClient.get();
		employee.setClient(client);
		
		employeeRepo.save(employee);
		return employee;
	}

}
