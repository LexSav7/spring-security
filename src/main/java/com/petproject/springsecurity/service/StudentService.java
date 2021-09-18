package com.petproject.springsecurity.service;

import com.petproject.springsecurity.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getStudents();
    Optional<Student> getStudent(long id);
    Student addStudent(Student student);
    Student updateStudent(Student student);
    void deleteStudent(long id);
    boolean existsById(long id);
}
