package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        logger.warn("check the correctness of the faculty id");
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

    public Collection<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String nameOrColor) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor);

    }

    public Faculty findFacultyById(long id) {
        return facultyRepository.findFacultyById(id);

    }

    public OptionalInt LongestNameOfFaculty() {
        List<String> namesFaculties = new ArrayList<>();
        List<Faculty> allFaculty = facultyRepository.findAll();
        for (Faculty faculty : allFaculty) {
            namesFaculties.add(faculty.getName());
        }
        OptionalInt longestNameOfFaculty = namesFaculties.stream().mapToInt(String::length).max();
        return longestNameOfFaculty;

    }

    public Integer getSum() {

        int sum = Stream
                .iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> (a + b));
        return sum;
    }


}
