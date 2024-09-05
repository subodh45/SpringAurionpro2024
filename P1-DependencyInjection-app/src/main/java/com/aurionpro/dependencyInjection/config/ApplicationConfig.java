package com.aurionpro.dependencyInjection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aurionpro.dependencyInjection.entity.Computer;
import com.aurionpro.dependencyInjection.entity.Harddisk;

@Configuration
public class ApplicationConfig {

	@Bean
	Computer getComputer()
	{
		return new Computer();
	}
	
	@Bean
	Harddisk getharddisk()
	{
		return new Harddisk();
	}
	
}
