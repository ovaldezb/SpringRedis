package com.ovaldez.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ovaldez.redis.domain.Student;
import com.ovaldez.redis.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	/*public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}*/
	
	@GetMapping("/student")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(studentRepository.findAll());
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> findById(@PathVariable(name = "id") String id){
		return ResponseEntity.ok(studentRepository.findById(id));
	}
	
	@PostMapping("/student")
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		log.info("Creando el Student"+ student.getFirstName()+" "+student.getLastName());
		return new ResponseEntity<Student>(studentRepository.save(student),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable String id){
		return ResponseEntity.ok(studentRepository.delete(id));
	}
	
}
