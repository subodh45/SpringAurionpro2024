package com.aurionpro.intro.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {

	@GetMapping("/hello")
	public String printHello()
	{
		return "my first spring Controller.";
	}
	
	@GetMapping("/bye")
	public String printBye()
	{
		return "Bye Method.";
	}
	
	@GetMapping("/home")
	public String home()
	{
		return "This is home page .";
	}
	
	@GetMapping("/display")
	public String display()
	{
		return "This is display page .";
	}
}
