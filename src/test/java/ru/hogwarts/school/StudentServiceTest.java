package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.*;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void addStudent() {
        Mockito.when(studentRepository.save(new Student(1L, "Anton", 12))).thenReturn(new Student(1L, "Anton", 12));
        org.junit.jupiter.api.Assertions.assertEquals(new Student(1L, "Anton", 12), studentService.createStudent(new Student(1L, "Anton", 12)));
    }

    @Test
    public void updateStudent() {
        Mockito.when(studentRepository.save(new Student(1L, "Anton", 12))).thenReturn(new Student(1L, "Anton", 12));
        org.junit.jupiter.api.Assertions.assertEquals(new Student(1L, "Anton", 12), studentService.createStudent(new Student(1L, "Anton", 12)));
        studentService.updateStudent(new Student(1L, "Anton", 12));
        Mockito.when(studentRepository.save(new Student(1L, "Ivan", 13))).thenReturn(new Student(1L, "Ivan", 13));
        org.junit.jupiter.api.Assertions.assertEquals(new Student(1L, "Ivan", 13), studentService.updateStudent(new Student(1L, "Ivan", 13)));
    }

    @Test
    public void getStudentPositive() {
        Student student = new Student(1L, "Anton", 12);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        org.junit.jupiter.api.Assertions.assertEquals(Optional.of(student), studentRepository.findById(1L));
    }

    @Test
    public void getStudentNegative() {
        Mockito.when(studentRepository.findById(2L)).thenReturn(Optional.empty());
        Assertions.assertNull(studentService.getStudent(2L));
    }

    @Test
    public void deleteStudent() {
        Mockito.when(studentRepository.save(new Student(1L, "Anton", 12))).thenReturn(new Student(1L, "Anton", 12));
        org.junit.jupiter.api.Assertions.assertEquals(new Student(1L, "Anton", 12), studentService.createStudent(new Student(1L, "Anton", 12)));
        studentService.delStudent(1L);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertNull(studentService.getStudent(1L));
    }

    @Test
    public void getAllStudents() {
        Mockito.when(studentRepository.save(new Student(1L, "Anton", 12))).thenReturn(new Student(1L, "Anton", 12));
        org.junit.jupiter.api.Assertions.assertEquals(new Student(1L, "Anton", 12), studentService.createStudent(new Student(1L, "Anton", 12)));
        Mockito.when(studentRepository.save(new Student(2L, "Semen", 15))).thenReturn(new Student(2L, "Semen", 15));
        org.junit.jupiter.api.Assertions.assertEquals(new Student(2L, "Semen", 15), studentService.createStudent(new Student(2L, "Semen", 15)));
        List<Student> allStudents = List.of(
                new Student(1L, "Anton", 12),
                new Student(2L, "Ivan", 13)
        );
        Mockito.when(studentRepository.findAll()).thenReturn(allStudents);
        org.junit.jupiter.api.Assertions.assertEquals(allStudents, studentService.getAllStudents());
    }

    @Test
    public void getStudentsByAge() {
        Mockito.when(studentRepository.save(new Student(1L, "Anton", 12))).thenReturn(new Student(1L, "Anton", 12));
        org.junit.jupiter.api.Assertions.assertEquals(new Student(1L, "Anton", 12), studentService.createStudent(new Student(1L, "Anton", 12)));
        Mockito.when(studentRepository.save(new Student(2L, "Semen", 15))).thenReturn(new Student(2L, "Semen", 15));
        org.junit.jupiter.api.Assertions.assertEquals(new Student(2L, "Semen", 15), studentService.createStudent(new Student(2L, "Semen", 15)));
        Collection<Student> studentsByAge = studentService.getStudentsByAge(15);
        Mockito.when(studentRepository.findByAge(15)).thenReturn(studentsByAge);
        org.junit.jupiter.api.Assertions.assertEquals(studentsByAge, studentService.getStudentsByAge(15));

    }

    @Test
    public void findByAgeBetween() {
        Mockito.when(studentRepository.save(new Student(1L, "Anton", 12))).thenReturn(new Student(1L, "Anton", 12));
        org.junit.jupiter.api.Assertions.assertEquals(new Student(1L, "Anton", 12), studentService.createStudent(new Student(1L, "Anton", 12)));
        Mockito.when(studentRepository.save(new Student(2L, "Semen", 15))).thenReturn(new Student(2L, "Semen", 15));
        org.junit.jupiter.api.Assertions.assertEquals(new Student(2L, "Semen", 15), studentService.createStudent(new Student(2L, "Semen", 15)));
        Collection<Student> studentsByAgeBetween = studentService.findByAgeBetween(10, 13);
        studentsByAgeBetween.add(new Student(1L, "Anton", 12));
        Mockito.when(studentRepository.findByAgeBetween(10, 13)).thenReturn(studentsByAgeBetween);
        org.junit.jupiter.api.Assertions.assertEquals(studentsByAgeBetween, studentService.findByAgeBetween(10, 13));

    }

    @Test
    public void findFacultyByIdStudent() {
        Student student = new Student(1L, "Anton", 12);
        student.setFaculty(new Faculty(10L, "Griffindor", "red"));
        Mockito.when(studentRepository.findStudentById(1L)).thenReturn(student);
        org.junit.jupiter.api.Assertions.assertEquals(new Faculty(10L, "Griffindor", "red"), studentService.findFacultyByIdStudent(1L));

    }


}






