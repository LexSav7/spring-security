package com.petproject.springsecurity.contoller;

import com.petproject.springsecurity.model.Student;
import com.petproject.springsecurity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private final StudentService studentService;

    @Autowired
    public StudentManagementController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getStudents();

        if (students.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Student> registerNewStudent(@RequestBody Student student) {
        if (student.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Student savedStudent = studentService.addStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") long studentId) {
        if (studentService.existsById(studentId)) {
            studentService.deleteStudent(studentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") long studentId, @RequestBody Student student) {
        boolean arePathAndBodyIdEqual = (student.getId() == null) || (studentId == student.getId());

        if (!arePathAndBodyIdEqual && !studentService.existsById(student.getId())) {
            return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
        }
        Student updatedStudent = studentService.updateStudent(student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.CREATED);
    }
}
