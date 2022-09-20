package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    Student findStudentById(Long id);

    @Query(value = "SELECT COUNT (*) FROM student", nativeQuery = true)
    Integer numberOfStudents();

    @Query(value = "SELECT AVG (age) FROM student", nativeQuery = true)
    Integer averageAgeOfStudents();

    @Query(value = "SELECT * FROM student  ORDER BY id DESC  LIMIT 2", nativeQuery = true)
    List<Student> lastTwoStudents();
}
