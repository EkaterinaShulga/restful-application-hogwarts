package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StudentServiceTest {

    private final StudentService studentService = new StudentService();

    @Test
    public void putStudentsTest() {
        HashMap<Long, Student> allStudents = new HashMap<>();
        Student student1 = new Student(1L, "Anton", 12);
        Assertions.assertThat(studentService.createStudent(student1)).isEqualTo(allStudents.put(1L, student1));

    }

    @Test
    public void getStudentsTest() {
        Student student1 = new Student(1L, "Anton", 12);
        Student student2 = new Student(2L, "Oleg", 14);
        studentService.createStudent(student1);
        studentService.createStudent(student2);

        Student result1 = studentService.getStudent(3L);
        Assertions.assertThat(result1).isEqualTo(null);
        Student result2 = studentService.getStudent(1L);
        Assertions.assertThat(result2).isEqualTo(student1);

    }

    @Test
    public void updateStudentPositiveTest() {
        Student student1 = new Student(1L, "Anton", 12);
        Student student2 = new Student(2L, "Oleg", 14);
        studentService.createStudent(student1);
        studentService.createStudent(student2);

        Student result1 = studentService.updateStudent(student1);
        Assertions.assertThat(result1).isEqualTo(student1);

    }

    @Test
    public void updateStudentNegativeTest() {
        Student student1 = new Student(1L, "Anton", 12);
        Student student2 = new Student(2L, "Oleg", 14);
        Student student3 = new Student(3L, "Egor", 10);
        studentService.createStudent(student1);
        studentService.createStudent(student2);

        Student result2 = studentService.updateStudent(student3);
        Assertions.assertThat(result2).isEqualTo(null);

    }

    @Test
    public void deleteStudentsTest() {
        Student student1 = new Student(1L, "Anton", 12);
        Student student2 = new Student(2L, "Oleg", 14);
        studentService.createStudent(student1);
        studentService.createStudent(student2);
        Assertions.assertThat(studentService.delStudent(1L)).isEqualTo(student1);
        Assertions.assertThat(studentService.delStudent(3L)).isEqualTo(null);

    }

    @Test
    public void getAllStudentsTest() {
        Student student1 = new Student(1L, "Anton", 12);
        Student student2 = new Student(2L, "Oleg", 14);
        studentService.createStudent(student1);
        studentService.createStudent(student2);
        Collection<Student> expectedStudentsList = studentService.getAllStudents();

        Collection<Student> realStudentsList = studentService.getAllStudents();
        assertEquals(expectedStudentsList, realStudentsList);

    }

    @Test
    public void getStudentsByAge() {
        Student student1 = new Student(1L, "Anton", 12);
        Student student2 = new Student(2L, "Oleg", 14);
        studentService.createStudent(student1);
        studentService.createStudent(student2);
        List<Student> expectedStudentsList = studentService.getStudentsByAge(12);
        List<Student> actualStudentsList = new ArrayList<>();
        actualStudentsList.add(student1);
        assertEquals(expectedStudentsList, actualStudentsList);

    }

}






