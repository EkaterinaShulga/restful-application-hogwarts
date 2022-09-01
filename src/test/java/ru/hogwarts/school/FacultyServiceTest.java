package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacultyServiceTest {

    private final FacultyService facultyService = new FacultyService();

    @Test
    public void createFacultyTest() {
        HashMap<Long, Faculty> allFaculties = new HashMap<>();
        Faculty faculty1 = new Faculty(1L, "psychology", "red");
        Assertions.assertThat(facultyService.createFaculty(faculty1)).isEqualTo(allFaculties.put(1L, faculty1));

    }

    @Test
    public void getFacultyTest() {
        Faculty faculty1 = new Faculty(1L, "psychology", "red");
        Faculty faculty2 = new Faculty(2L, "medical", "blue");
        Faculty faculty3 = new Faculty(3L, "law", "black");

        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        facultyService.createFaculty(faculty3);

        Faculty result1 = facultyService.getFaculty(4L);
        Assertions.assertThat(result1).isEqualTo(null);
        Faculty result2 = facultyService.getFaculty(1L);
        Assertions.assertThat(result2).isEqualTo(faculty1);

    }

    @Test
    public void updateFacultyPositiveTest() {
        Faculty faculty1 = new Faculty(1L, "psychology", "red");
        Faculty faculty2 = new Faculty(2L, "medical", "blue");
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);

        Faculty result1 = facultyService.updateFaculty(faculty1);
        Assertions.assertThat(result1).isEqualTo(faculty1);

    }

    @Test
    public void updateFacultyNegativeTest() {
        Faculty faculty1 = new Faculty(1L, "psychology", "red");
        Faculty faculty2 = new Faculty(2L, "medical", "blue");
        facultyService.createFaculty(faculty1);


        Faculty result1 = facultyService.updateFaculty(faculty2);
        Assertions.assertThat(result1).isEqualTo(null);

    }

    @Test
    public void deleteFacultyTest() {
        Faculty faculty1 = new Faculty(1L, "psychology", "red");
        Faculty faculty2 = new Faculty(2L, "medical", "blue");
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        Assertions.assertThat(facultyService.deleteFaculty(1L)).isEqualTo(faculty1);
        Assertions.assertThat(facultyService.deleteFaculty(3L)).isEqualTo(null);

    }

    @Test
    public void getAllFacultiesTest() {
        Faculty faculty1 = new Faculty(1L, "psychology", "red");
        Faculty faculty2 = new Faculty(2L, "medical", "blue");
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        Collection<Faculty> expectedFacultiesList = facultyService.getAllFaculties();

        Collection<Faculty> realFacultiesList = facultyService.getAllFaculties();
        assertEquals(expectedFacultiesList, realFacultiesList);

    }

    @Test
    public void getStudentsByAge() {
        Faculty faculty1 = new Faculty(1L, "psychology", "red");
        Faculty faculty2 = new Faculty(2L, "medical", "blue");
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        List<Faculty> expectedFacultiesList = facultyService.getFacultiesByColor("red");
        List<Faculty> actualFacultiesList = new ArrayList<>();
        actualFacultiesList.add(faculty1);
        assertEquals(expectedFacultiesList, actualFacultiesList);


    }

}





