package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.main.dto.InstructorDto;
import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.dto.StudentDto;
import com.aurionpro.main.entity.Course;
import com.aurionpro.main.entity.Instructor;
import com.aurionpro.main.entity.Student;
import com.aurionpro.main.repository.CourseRepository;
import com.aurionpro.main.repository.InstructorRepository;
import com.aurionpro.main.repository.StudentRepository;

@Service
public class InstructorServiceImpl implements InstructorService{

	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public InstructorDto addInstructor(InstructorDto instructorDto) {
		
		Instructor instructor = new Instructor();
		
		instructor.setEmail(instructorDto.getEmail());
		instructor.setName(instructorDto.getName());
		instructor.setQualification(instructorDto.getQualification());
		
		instructor = instructorRepo.save(instructor);
		
		instructorDto.setEmail(instructor.getEmail());
		instructorDto.setInstructorId(instructor.getInstructorId());
		instructorDto.setName(instructor.getName());
		instructorDto.setQualification(instructor.getQualification());
		
		return instructorDto;
	}
	
	
	public Instructor maptoInstructor(InstructorDto instructorDto)
	{
        Instructor instructor = new Instructor();
		
		instructor.setEmail(instructorDto.getEmail());
		instructor.setName(instructorDto.getName());
		instructor.setQualification(instructorDto.getQualification());
		
		return instructor;
	}
	
	public InstructorDto maptoInstructorDto(Instructor instructor)
	{
		 InstructorDto  instructorDto = new  InstructorDto();
		
		instructorDto.setEmail(instructor.getEmail());
		instructorDto.setInstructorId(instructor.getInstructorId());
		instructorDto.setName(instructor.getName());
		instructorDto.setQualification(instructor.getQualification());
		
		return instructorDto;
	}
	

	@Override
	public List<InstructorDto> getAllInstructor() {
		
		List<Instructor> instructors = instructorRepo.findAll();
		List<InstructorDto> instructorDtos= new ArrayList<>();
		
		for(Instructor i : instructors)
		{
			InstructorDto	instructorDto = maptoInstructorDto(i);
			
			instructorDtos.add(instructorDto);		
		}
		
		return instructorDtos;
	}
	
	@Override
	public InstructorDto getInstructorDtoById(int id )
	{
		
		Optional<Instructor> optionalinstructor = instructorRepo.findById(id);
		
		if(optionalinstructor.isEmpty())
		 return null;
		
		Instructor instructor = optionalinstructor.get();
		
	//	InstructorDto	instructorDto = maptoInstructorDto(instructor);
		
		return maptoInstructorDto(instructor);
		
	}


	@Override
	public Instructor allocateCoursetoInstructor(int instructorId, List<Integer> courseIds) {
	
		Instructor instructor =   maptoInstructor(getInstructorDtoById(instructorId));
		instructor.setInstructorId(instructorId);
		
		List<Course> dbCourses= instructor.getCourses();
		
		if(dbCourses ==null)
			dbCourses = new ArrayList<>();
		
		List<Course> courseToAdd = new ArrayList<>();
		
		courseIds.forEach(courseId->{
		   Optional<Course> optionalCourse =courseRepo.findById(courseId);
		   
		   if(optionalCourse.isEmpty())
			   throw new NullPointerException("course not exits");
			
			Course course = optionalCourse.get();
			course.setInstructor(instructor);
			
			courseToAdd.add(course);
		});
		
		dbCourses.addAll(courseToAdd);
		instructor.setCourses(dbCourses);
		
		return instructorRepo.save(instructor);
	}


	@Override
	public PageResponseDto<InstructorDto> getAllInstructorPage(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Instructor> instructorPage = instructorRepo.findAll(pageable);
		
		PageResponseDto<InstructorDto>  pageResponseDto = new  PageResponseDto();
		
		Page<InstructorDto> instructorDtoPage = instructorPage.map(this::maptoInstructorDto);
		
    	pageResponseDto.setPageNumber(instructorDtoPage.getNumber());
    	pageResponseDto.setContent(instructorDtoPage.getContent());
     	pageResponseDto.setPageSize(instructorDtoPage.getSize());
 	
		return  pageResponseDto;
	}


	@Override
	public List<StudentDto> getAllStudentUnderInstructor(int instructorId) {
		
		Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(()-> new NullPointerException("does not found instructor."));
		List<Course> courses = instructor.getCourses();
		
		List<StudentDto> studentList = new ArrayList<>();
		if(courses == null)
			return null;

		courses.forEach(course->{
			
			List <Student> students = course.getStudents(); 
			List<StudentDto> studentDtos= new ArrayList<>();
			StudentServiceImpl studentservice = new StudentServiceImpl();
			
			students.forEach(student-> {
				studentDtos.add(studentservice.studentDtoMapper(student));
			});
			studentList.addAll(studentDtos);
		});
		return studentList;
	}

}
