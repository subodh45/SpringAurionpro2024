package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.dto.StudentDto;
import com.aurionpro.main.entity.Address;
import com.aurionpro.main.entity.Course;
import com.aurionpro.main.entity.Student;
import com.aurionpro.main.repository.CourseRepository;
import com.aurionpro.main.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public StudentDto addStudent(Student student) {
			
		return studentDtoMapper(studentRepo.save(student));
	}

	public StudentDto studentDtoMapper(Student student)
	{
		StudentDto studentDto = new StudentDto();
		
		studentDto.setAge(student.getAge());
		studentDto.setName(student.getName()); 
		studentDto.setRollno(student.getRollno());
		
		return studentDto;
	}
	
	public Student studentMapper(StudentDto studentDto)
	{
        Student student = new Student();
		
		student.setAge(studentDto.getAge());
		student.setName(studentDto.getName()); 
		if(studentDto.getRollno()>0)
			student.setRollno(studentDto.getRollno());	
		return student;
	}
	
	@Override
	public Page<Student> getAllStudent(int pageNumber, int pageSize) {
		
		 Pageable pageable= PageRequest.of(pageNumber, pageSize);
		 
		 Page page = studentRepo.findAll(pageable);
		 
		 PageResponseDto pageResponseDto = new PageResponseDto();
		 
		 pageResponseDto.setContent(page.getContent());
		 pageResponseDto.setPageNumber(page.getNumber());
		 pageResponseDto.setPageSize(page.getSize());
		 
		 return studentRepo.findAll(pageable);
		 
		 
	}

	@Override
	public Address findStudentAddress(int rollno) {
		
		Optional<Student> student = studentRepo.findById(rollno);
		
		if(!student.isPresent())
			return null;
		
		 Student student1 =student.get();
		 Address address = student1.getAddressId();
		 
		return address;
	}

	@Override
	public StudentDto findStudent(int rollno) {
		Optional<Student> student = studentRepo.findById(rollno);
		
		 Student student1 =student.get();
		 
		return studentDtoMapper(student1);
	}

	@Override
	public Student updateStudentAddress(int rollno,String buildingName, String areaName, String city , int pincode) {
		// TODO Auto-generated method stub
		Student student = studentMapper(findStudent(rollno));
		student.setRollno(rollno);
		
		Address newAddress = new Address(rollno,buildingName,areaName,city,pincode);
		
		student.setAddressId(newAddress);
		
		studentRepo.save(student).getAddressId();
		return student;
	}

	@Override
	public Student updateStudentAddress2(int rollno, Address address) {
		Student student =null;
		Optional<Student> student1 = studentRepo.findById(rollno);
		
		 if(!student1.isPresent())
			 return null;
		 
		 student = student1.get();
		Address newAddress = address;
		
		student.setAddressId(newAddress);
		
		studentRepo.save(student).getAddressId();
		
		return student;
	}

	@Override
	public PageResponseDto<StudentDto> getAllStudents(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Student> studentPage = studentRepo.findAll(pageable);
		
		PageResponseDto<StudentDto>  pageResponseDto = new  PageResponseDto();
		
		Page<StudentDto> studentDtoPage = studentPage.map(this::studentDtoMapper);
		
		pageResponseDto.setPageNumber(studentDtoPage.getNumber());
    	pageResponseDto.setContent(studentDtoPage.getContent());
     	pageResponseDto.setPageSize(studentDtoPage.getSize());
		
		return pageResponseDto;
	}

	@Override
	public StudentDto assigncourses(int rollno, List<Integer> courseIds) {
		
		Student dbstudent = studentRepo.findById(rollno).orElseThrow(()-> new NullPointerException("null exception"));
		
		Set<Course> existingCourses = dbstudent.getCourses();
		
		Set<Course> courseToAdd = new HashSet<>();
		
		if(existingCourses ==null)
			existingCourses = new HashSet<>();
		
		courseIds.forEach((id) -> {
			Course course = courseRepo.findById(id).orElseThrow(()-> new NullPointerException("Does not exist"));
			
			List<Student> existingStudent =  course.getStudents();
			if(existingStudent ==null)
				existingStudent = new ArrayList<>();
			
			existingStudent.add(dbstudent);		
			course.setStudents(existingStudent);
			
			courseToAdd.add(course);		
		});
		
		existingCourses.addAll(courseToAdd);
		dbstudent.setCourses(existingCourses);
		
		studentRepo.save(dbstudent);
		
		return studentDtoMapper(dbstudent);
	}
	
}
