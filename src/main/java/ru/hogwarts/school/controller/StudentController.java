package ru.hogwarts.school.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        studentService.delStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> allStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/age")
    public Collection<Student> getStudentByAge(@RequestParam("age") int age) {
        return studentService.getStudentsByAge(age);

    }

    @GetMapping("/ageBetween")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam("min") int min,
                                                                @RequestParam("max") int max) {
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping("/{id}student")
    public ResponseEntity<Faculty> getFacultyByIdStudent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.findFacultyByIdStudent(id));
    }

    @GetMapping("/numberOfStudents")
    public Integer getNumberOfStudents() {
        return studentService.numberOfStudents();
    }

    @GetMapping("/averageAgeOfStudents")
    public Double getAverageAgeOfStudents() {
        return studentService.averageAgeOfStudents();
    }

    @GetMapping("/lastTwoStudents")
    public ResponseEntity<List<Student>> getLastTwoStudents() {
        return ResponseEntity.ok(studentService.lastTwoStudents());
    }

    @GetMapping("/namesOfAllStudents")
    public ResponseEntity<List<String>> namesOfAllStudents() {
        return ResponseEntity.ok(studentService.namesOfAllStudents());
    }

    @GetMapping("/averageAgeStudents")
    public ResponseEntity<OptionalDouble> averageAgeStudents() {
        return ResponseEntity.ok(studentService.averageAgeStudents());
    }

}

