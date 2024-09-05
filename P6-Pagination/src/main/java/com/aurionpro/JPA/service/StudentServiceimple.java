package com.aurionpro.JPA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.JPA.dto.PageResponseDto;
import com.aurionpro.JPA.entity.Student;
import com.aurionpro.JPA.repository.StudentRepository;

@Service
public class StudentServiceimple implements StudentService {
  
	@Autowired
	private StudentRepository studentRepository;
		
//	public Page<Student> getAllStudentsbyName(String name ,int pageSize,int pageNumber)
//	{
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		
//		return studentRepository.findByname(name, pageable );
//	}

	public PageResponseDto<Student> getAllStudentsbyName(String name ,int pageSize,int pageNumber)
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Student> studentpage = studentRepository.findByname(name, pageable );
		
		PageResponseDto<Student>  pageResponseDto = new  PageResponseDto();
		
		 pageResponseDto.setTotalElements(studentpage.getTotalElements());
		 pageResponseDto.setTotalPages(studentpage.getTotalPages());
		 pageResponseDto.setSize(studentpage.getSize());
		 pageResponseDto.setContent(studentpage.getContent());
		 pageResponseDto.setLastPage(studentpage.isLast());
		
		
		return pageResponseDto ;
	}
	
	public PageResponseDto<Student> getAllStudents(int pageSize,int pageNumber)
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Student> studentpage = studentRepository.findAll(pageable);
		
		PageResponseDto<Student>  pageResponseDto = new  PageResponseDto();
		
		 pageResponseDto.setTotalElements(studentpage.getTotalElements());
		 pageResponseDto.setTotalPages(studentpage.getTotalPages());
		 pageResponseDto.setSize(studentpage.getSize());
		 pageResponseDto.setContent(studentpage.getContent());
		 pageResponseDto.setLastPage(studentpage.isLast());
		 
		 
		  		
		return pageResponseDto;
	}

	

}
