package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
        Student student = new Student(1L, "Anton", 12);
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        org.junit.jupiter.api.Assertions.assertEquals(student, studentService.createStudent(student));
    }

    @Test
    public void updateStudent() {
        Student student = new Student(1L, "Anton", 12);
        studentService.updateStudent(student);
        Student studentChange = studentService.updateStudent(new Student(1L, "Victor", 12));
        Mockito.when(studentRepository.save(studentChange)).thenReturn(studentChange);
        org.junit.jupiter.api.Assertions.assertEquals(studentChange, studentService.updateStudent(studentChange));
    }

    @Test
    public void deleteStudent() {
        Student student = new Student(1L, "Anton", 12);
        studentService.delStudent(1L);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        org.junit.jupiter.api.Assertions.assertEquals(null, studentService.getStudent(1L));
    }

    @Test
    public void getStudentPositive() {
        Student student = new Student(1L, "Anton", 12);
        Student student1 = new Student(2L, "Ivan", 13);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        org.junit.jupiter.api.Assertions.assertEquals(Optional.of(student), studentRepository.findById(1L));
    }

    @Test
    public void getStudentNegative() {
        Mockito.when(studentRepository.findById(2L)).thenReturn(Optional.empty());
        org.junit.jupiter.api.Assertions.assertEquals(null, studentService.getStudent(2L));
    }

    @Test
    public void getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        Student student = new Student(1L, "Anton", 12);
        Student student1 = new Student(2L, "Ivan", 13);
        allStudents.add(student);
        allStudents.add(student1);
        studentService.delStudent(1L);
        Mockito.when(studentRepository.findAll()).thenReturn(allStudents);
        org.junit.jupiter.api.Assertions.assertEquals(allStudents, studentService.getAllStudents());
    }

    @Test
    public void getStudentsByAge() {
        List<Student> allStudents = List.of(
                new Student(1L, "Anton", 12),
                new Student(2L, "Ivan", 13),
                new Student(3L, "Oleg", 13));
        List<Student> allStudentsAfterSort = studentService.getStudentsByAge(12);
        Mockito.when(studentRepository.findAll()).thenReturn(allStudentsAfterSort);
        org.junit.jupiter.api.Assertions.assertEquals(allStudentsAfterSort, studentService.getStudentsByAge(12));

    }

}






