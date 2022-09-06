package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
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
        Mockito.when(facultyRepository.save(new Faculty(1L, "Hogwarts", "red"))).thenReturn(new Faculty(1L, "Hogwarts", "red"));
        org.junit.jupiter.api.Assertions.assertEquals(new Faculty(1L, "Hogwarts", "red"), facultyService.createFaculty(new Faculty(1L, "Hogwarts", "red")));
    }

    @Test
    public void updateFaculty() {
        Mockito.when(facultyRepository.save(new Faculty(1L, "Hogwarts", "red"))).thenReturn(new Faculty(1L, "Hogwarts", "red"));
        org.junit.jupiter.api.Assertions.assertEquals(new Faculty(1L, "Hogwarts", "red"), facultyService.createFaculty(new Faculty(1L, "Hogwarts", "red")));
        facultyService.updateFaculty(new Faculty(1L, "Hogwarts", "red"));
        Mockito.when(facultyRepository.save(new Faculty(1L, "Slytherin", "black"))).thenReturn(new Faculty(1L, "Slytherin", "black"));
        org.junit.jupiter.api.Assertions.assertEquals(new Faculty(1L, "Slytherin", "black"), facultyService.updateFaculty(new Faculty(1L, "Slytherin", "black")));
    }

    @Test
    public void getFacultyPositive() {
        Faculty faculty = new Faculty(1L, "Hogwarts", "red");
        Mockito.when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
        org.junit.jupiter.api.Assertions.assertEquals(Optional.of(faculty), facultyRepository.findById(1L));
    }

    @Test
    public void getFacultyNegative() {
        Mockito.when(facultyRepository.findById(2L)).thenReturn(Optional.empty());
        Assertions.assertNull(facultyService.getFaculty(2L));
    }

    @Test
    public void deleteFaculty() {
        Mockito.when(facultyRepository.save( new Faculty(1L, "Hogwarts", "red"))).thenReturn( new Faculty(1L, "Hogwarts", "red"));
        org.junit.jupiter.api.Assertions.assertEquals( new Faculty(1L, "Hogwarts", "red"), facultyService.createFaculty( new Faculty(1L, "Hogwarts", "red")));
       facultyService.deleteFaculty(1L);
        Mockito.when(facultyRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertNull(facultyService.getFaculty(1L));
    }

    @Test
    public void getAllFaculties() {
        Mockito.when(facultyRepository.save( new Faculty(1L, "Hogwarts", "red"))).thenReturn( new Faculty(1L, "Hogwarts", "red"));
        org.junit.jupiter.api.Assertions.assertEquals( new Faculty(1L, "Hogwarts", "red"), facultyService.createFaculty( new Faculty(1L, "Hogwarts", "red")));
        Mockito.when(facultyRepository.save(new Faculty(2L, "Slytherin", "black"))).thenReturn(new Faculty(2L, "Slytherin", "black"));
        org.junit.jupiter.api.Assertions.assertEquals(new Faculty(2L, "Slytherin", "black"), facultyService.createFaculty(new Faculty(2L, "Slytherin", "black")));
        List<Faculty> allFaculties = List.of(
                new Faculty(1L, "Hogwarts", "red"),
                new Faculty(2L, "Slytherin", "black")
        );
        Mockito.when(facultyRepository.findAll()).thenReturn(allFaculties);
        org.junit.jupiter.api.Assertions.assertEquals(allFaculties, facultyService.getAllFaculties());
    }

    @Test
    public void getFacultiesByColor() {
        Mockito.when(facultyRepository.save( new Faculty(1L, "Hogwarts", "red"))).thenReturn( new Faculty(1L, "Hogwarts", "red"));
        org.junit.jupiter.api.Assertions.assertEquals( new Faculty(1L, "Hogwarts", "red"), facultyService.createFaculty( new Faculty(1L, "Hogwarts", "red")));
        Mockito.when(facultyRepository.save(new Faculty(2L, "Slytherin", "black"))).thenReturn(new Faculty(2L, "Slytherin", "black"));
        org.junit.jupiter.api.Assertions.assertEquals(new Faculty(2L, "Slytherin", "black"), facultyService.createFaculty(new Faculty(2L, "Slytherin", "black")));
        List<Faculty> facultyByColor = facultyService.findByColor("black");
        Mockito.when(facultyRepository.findAll()).thenReturn(facultyByColor);
        org.junit.jupiter.api.Assertions.assertEquals(facultyByColor, facultyService.findByColor("black"));
    }

}



