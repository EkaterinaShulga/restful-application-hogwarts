package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;


import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public Object flag = new Object();

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.debug("The method was called: createStudent(Student student)");
        return studentRepository.save(student);

    }

    public Student getStudent(long id) {
        logger.warn("check the correctness of the faculty id");
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        logger.debug("The method was called: updateStudent(Student id)");
        return studentRepository.save(student);
    }

    public void delStudent(long id) {
        logger.debug("The method was called: delStudent(long id)");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.debug("The method was called: getAllStudents()");
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentsByAge(int age) {
        logger.debug("The method was called: getStudentsByAge(int age)");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.debug("The method was called: findByAgeBetween(int min, int max)");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findFacultyByIdStudent(Long id) {
        Student student = studentRepository.findStudentById(id);
        logger.warn("check the correctness of the faculty id");
        return student.getFaculty();
    }

    public Integer numberOfStudents() {
        logger.debug("The method was called: numberOfStudents()");
        return studentRepository.numberOfStudents();
    }

    public Double averageAgeOfStudents() {
        logger.debug("The method was called: averageAgeOfStudents()");
        return studentRepository.averageAgeOfStudents();
    }

    public List<Student> lastTwoStudents() {
        logger.debug("The method was called: lastTwoStudents()");
        return studentRepository.lastTwoStudents();
    }


    public List<String> namesOfAllStudents() {

        return studentRepository.findAll().stream()
                .map((s) -> s.getName())
                .filter(s -> s.startsWith("H"))
                .sorted()
                .map(s -> s.toUpperCase(Locale.ROOT))
                .collect(Collectors.toList());
    }

    public Double averageAgeStudents() {

        return studentRepository.findAll().stream()
                .mapToDouble(s -> s.getAge())
                .average().orElseThrow();
    }

    public void returnNamesUsingStreams() {
        getStudentsForStreams(0);
        getStudentsForStreams(1);


        new Thread(() -> {
            getStudentsForStreams(2);
            getStudentsForStreams(3);
        }).start();

        new Thread(() -> {
            getStudentsForStreams(4);
            getStudentsForStreams(5);
        }).start();
    }

    public void getStudentsForStreams(int count) {
        List<Student> students = studentRepository.findAll();
        Student student = students.get(count);
        System.out.println(student.getName());
    }

    public void getNamesPairs() {
        getNamesPairsForStreams(0, 1);

        new Thread(() -> {
            getNamesPairsForStreams(2, 3);
        }).start();

        new Thread(() -> {
            getNamesPairsForStreams(4, 5);
        }).start();
    }

    public synchronized void getNamesPairsForStreams(int count1, int count2) {
        synchronized (flag) {
            List<Student> students = studentRepository.findAll();
            Student student1 = students.get(count1);
            Student student2 = students.get(count2);
            System.out.println(student1.getName());
            System.out.println(student2.getName());

        }
    }

}













