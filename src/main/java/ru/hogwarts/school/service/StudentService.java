package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long count = 0;

    public Student createStudent(Student student) {
        student.setId(++count);
        return students.put(count, student);

    }

    public Student getStudent(long id) {
        return students.get(id);
    }

    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student delStudent(long id) {
        return students.remove(id);

    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public List<Student> getStudentsByAge(int age) {
        List<Student> studentsByAge = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                studentsByAge.add(student);
            }
        }
        return studentsByAge;
    }

    @Override
    public String toString() {
        return "StudentService{" +
                "students=" + students +
                ", count=" + count +
                '}';
    }


}
