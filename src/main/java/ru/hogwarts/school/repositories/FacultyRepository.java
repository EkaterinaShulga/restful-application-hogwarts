package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;



@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Faculty findByNameOrColorIgnoreCase(String name, String Color);

    Faculty findFacultyById(long id);
}
