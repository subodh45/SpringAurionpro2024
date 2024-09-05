package com.aurionpro.dependencyInjection.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dependencyInjection.entity.Computer;
import com.aurionpro.dependencyInjection.entity.Harddisk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ComputerController {
 
	@Autowired
	private Computer computer;
	
	
	@GetMapping("/computers")
	public Computer getComputer()
	{
		return computer;
	}
	
	@Autowired
	private Harddisk harddisk;
	
	@GetMapping("/harddisk")
	public Harddisk getHardisk()
	{
		return harddisk;
	}
}
