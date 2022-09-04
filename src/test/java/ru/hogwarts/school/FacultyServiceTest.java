package ru.hogwarts.school;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;


import java.util.*;


@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {
    @Mock
    private FacultyRepository facultyRepository;
    @InjectMocks
    private FacultyService facultyService;

    @Test
    public void addFaculty() {
        Faculty faculty = new Faculty(1L, "Hogwarts", "red");
        Mockito.when(facultyRepository.save(faculty)).thenReturn(faculty);
        org.junit.jupiter.api.Assertions.assertEquals(faculty, facultyService.createFaculty(faculty));
    }

    @Test
    public void updateFaculty() {
        Faculty faculty = new Faculty(1L, "Hogwarts", "red");
        facultyService.updateFaculty(faculty);
        Faculty facultyChange = facultyService.updateFaculty(new Faculty(1L, "Slytherin", "black"));
        Mockito.when(facultyRepository.save(facultyChange)).thenReturn(facultyChange);
        org.junit.jupiter.api.Assertions.assertEquals(facultyChange, facultyService.updateFaculty(facultyChange));
    }

    @Test
    public void getFacultyPositive() {
        Faculty faculty = new Faculty(1L, "Hogwarts", "red");
        Faculty faculty1 = new Faculty(2L, "Slytherin", "black");
        Mockito.when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        org.junit.jupiter.api.Assertions.assertEquals(Optional.of(faculty), facultyRepository.findById(1L));
    }

    @Test
    public void getFacultyNegative() {
        Mockito.when(facultyRepository.findById(2L)).thenReturn(Optional.empty());
        org.junit.jupiter.api.Assertions.assertEquals(null, facultyService.getFaculty(2L));
    }

    @Test
    public void deleteFaculty() {
        Faculty faculty = new Faculty(1L, "Hogwarts", "red");
        facultyService.deleteFaculty(1L);
        Mockito.when(facultyRepository.findById(1L)).thenReturn(Optional.empty());
        org.junit.jupiter.api.Assertions.assertEquals(null, facultyService.getFaculty(1L));
    }

    @Test
    public void getAllStudents() {
        List<Faculty> allFaculties = new ArrayList<>();
        Faculty faculty = new Faculty(1L, "Hogwarts", "red");
        Faculty faculty1 = new Faculty(2L, "Slytherin", "black");
        allFaculties.add(faculty);
        allFaculties.add(faculty1);
        facultyService.deleteFaculty(1L);
        Mockito.when(facultyRepository.findAll()).thenReturn(allFaculties);
        org.junit.jupiter.api.Assertions.assertEquals(allFaculties, facultyService.getAllFaculties());
    }

    @Test
    public void getFacultiesByColor() {
        List<Faculty> allStudents = List.of(
                new Faculty(1L, "Hogwarts", "red"),
                new Faculty(2L, "Slytherin", "black"));
        List<Faculty> allFacultiesAfterSort = facultyService.getFacultiesByColor("black");
        Mockito.when(facultyRepository.findAll()).thenReturn(allFacultiesAfterSort);
        org.junit.jupiter.api.Assertions.assertEquals(allFacultiesAfterSort, facultyService.getFacultiesByColor("black"));

    }


}



