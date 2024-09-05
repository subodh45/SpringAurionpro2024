package com.aurionpro.main.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.main.dto.CourseDto;
import com.aurionpro.main.dto.InstructorDto;
import com.aurionpro.main.entity.Course;
import com.aurionpro.main.entity.Instructor;
import com.aurionpro.main.entity.Student;
import com.aurionpro.main.repository.CourseRepository;
import com.aurionpro.main.repository.InstructorRepository;
import com.aurionpro.main.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService  {

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private StudentRepository studentrepo;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
//	@Override
//	public CourseDto addNewCourse(CourseDto courseDto) {
//		
//		Course course = new Course();
//		
//		course.setDuration(courseDto.getDuration());
//		course.setFees(courseDto.getFees());
//		course.setName(courseDto.getName());
//		
//		course = courseRepo.save(course);
//		
//		courseDto.setCourseId(course.getCourseId());
//		courseDto.setDuration(course.getDuration());
//		courseDto.setFees(course.getFees());
//		courseDto.setName(course.getName());
//		
//		return courseDto;
//	}
	
	
	@Override
	public CourseDto addNewCourse(CourseDto courseDto) {
		
		Course course = courseMapper(courseDto);	
		course = courseRepo.save(course);
		courseDto = courseDtoMapper(course);
		
		logger.info("course added Successfully " + course.getCourseId());
		return courseDto;
	}
	
	public Course courseMapper(CourseDto courseDto)
	{
        Course course = new Course();
		
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
		course.setName(courseDto.getName());
		
		return course;
	}
	
	public CourseDto courseDtoMapper(Course course)
	{
		CourseDto courseDto = new CourseDto();
		
		courseDto.setCourseId(course.getCourseId());
		courseDto.setDuration(course.getDuration());
		courseDto.setFees(course.getFees());
		courseDto.setName(course.getName());
		
		return courseDto;		
	}

	@Override
	public List<CourseDto> getAllCourse() {
		
		List<Course> courses = courseRepo.findAll();
		
		List<CourseDto> courseDtos= new ArrayList<>();
		
		for(Course i : courses)
		{
			CourseDto	courseDto = courseDtoMapper(i);
			
			courseDtos.add(courseDto);		
		}
		
		return courseDtos;
	}

	@Override
	public CourseDto getCourseDtoById(int id) {

      Optional<Course> optionalcourse = courseRepo.findById(id);
		
		if(optionalcourse.isEmpty()) {
			
			logger.error("course with "+ id +"not present");
		 return null;
		}
		Course course = optionalcourse.get();
		
	//	InstructorDto	instructorDto = maptoInstructorDto(instructor);
		
		return courseDtoMapper(course);
	}

	@Override
	public Course updateInstructor(int courseId, int instructorId) {
		
		Optional<Course> optionalCourse = courseRepo.findById(courseId);
		
		if(optionalCourse.isEmpty())
		{
			logger.error("course with "+ courseId +"not present");
			return null;
		}
		
		Course course = optionalCourse.get();		
		
		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);
		
		if(optionalInstructor.isEmpty()) {
			logger.error("instructor with "+ instructorId +" is not present");
			return null;
		}
		course.setInstructor(optionalInstructor.get());
		
		courseRepo.save(course);
		logger.info("instructor with "+ instructorId +" is updated Successfully.");
		return course;
	}

	@Override
	public CourseDto addStudentToCourse(int courseId, List<Integer> studentIds) {
		
		Course dbcourse = courseRepo.findById(courseId).orElseThrow(()-> new NullPointerException("Course not found"));
		
		List<Student> existingStudents = dbcourse.getStudents();
		List<Student> studentToAdd = new ArrayList<>();
		
		if(existingStudents==null)
			existingStudents = new ArrayList<>();
		
		studentIds.forEach(id->{
			Student student = studentrepo.findById(id).orElseThrow(()->new NullPointerException("Course not found"));
			
			Set courses = student.getCourses();		
			courses.add(dbcourse);
			student.setCourses(courses);
			
			studentToAdd.add(student);
			
			
			
		});
		
		existingStudents.addAll(studentToAdd);
		
		dbcourse.setStudents(existingStudents);
		
		Course course =courseRepo.save(dbcourse);
		
		return courseDtoMapper(course);
	}

}
