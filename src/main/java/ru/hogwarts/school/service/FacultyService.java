package ru.hogwarts.school.service;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import java.util.*;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty findByNameOrColorIgnoreCase(String nameOrColor) {
        return facultyRepository.findByNameOrColorIgnoreCase(nameOrColor, nameOrColor);

    }

    public Faculty findFacultyById(long id) {
        return facultyRepository.findFacultyById(id);

    }


}
