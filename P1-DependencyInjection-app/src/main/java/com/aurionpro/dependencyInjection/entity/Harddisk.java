package com.aurionpro.dependencyInjection.entity;

import org.springframework.beans.factory.annotation.Value;

public class Harddisk {
    @Value("10")
	private int capacity;

	public Harddisk() {
		
	}
	public Harddisk(int capacity) {
		super();
		this.capacity = capacity;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "Harddisk [capacity=" + capacity + "]";
	}
	
	
}
