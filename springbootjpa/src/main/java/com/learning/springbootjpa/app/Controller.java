package com.learning.springbootjpa.app;

import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootjpa.app.entities.Student;
import com.learning.springbootjpa.app.repos.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Controller {

	@Autowired
	private StudentRepository repo;

	@PostMapping(path = "/student")
	public Student save(@RequestBody Student student) {

		repo.save(student);

		return repo.findById(student.getId()).get();

	}

	@GetMapping(path = "/student")
	public Student get(@RequestParam Long id) {
		return repo.findById(id).get();
	}

}
