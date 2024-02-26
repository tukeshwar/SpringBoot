package com.learning.springbootjpa.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.springbootjpa.app.entities.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {

}
