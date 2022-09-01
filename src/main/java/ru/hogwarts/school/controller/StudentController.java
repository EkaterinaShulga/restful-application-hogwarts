package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{count}")
    public ResponseEntity<Student> getStudent(@PathVariable Long count) {
        Student student = studentService.getStudent(count);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{count}")
    public Student deleteStudent(@PathVariable Long count) {
        return studentService.delStudent(count);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> allStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/age/{age}")
    public List<Student> getStudentByAge(@PathVariable int age) {
        return studentService.getStudentsByAge(age);

    }


}
