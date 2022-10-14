package ru.hogwarts.school;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;


import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private StudentRepository studentRepository;
    @SpyBean
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;


    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void saveFacultyTest() throws Exception {
        final String name = "griffendor";
        final String color = "red";
        final Long id = 1L;

        JSONObject jsonFaculty = new JSONObject();
        jsonFaculty.put("name", name);
        jsonFaculty.put("color", color);
        jsonFaculty.put("id", id);

        JSONObject jsonStudent = new JSONObject();
        jsonStudent.put("name", "Harry Potter");
        jsonStudent.put("age", 12);
        jsonStudent.put("id", 2L);

        List<Faculty> faculties = new ArrayList<>();
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        faculties.add(faculty);

        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setId(2L);
        student.setName("Harry Potter");
        student.setAge(12);


        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        when(facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(any(), eq(name))).thenReturn(faculties);
        when(facultyRepository.findAll()).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(jsonFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/")
                        .content(jsonFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?nameOrColor=" + name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].color").value(color));

/*

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(facultyRepository.findFacultyById(any(Long.class))).thenReturn(faculty);
        when(studentRepository.findAll()).thenReturn(students);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id + "/students")
                        .content(jsonStudent.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2L))
                .andExpect(jsonPath("$[0].name").value("Harry Potter"))
                .andExpect(jsonPath("$[0].age").value(12));

*/
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    public void negativeFacultyTest() throws Exception {
        final String name = "griffendor";
        final String color = "red";
        final Long id = 1L;

        JSONObject jsonFaculty = new JSONObject();
        jsonFaculty.put("name", name);
        jsonFaculty.put("color", color);
        jsonFaculty.put("id", id);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);

        Long id2 = 2L;

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(id2)).thenReturn(Optional.empty());

        when(facultyRepository.save(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/"))
                .andExpect(status().isBadRequest()); // этот тест работает, но покрытия в контроллере нет


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id2))
                .andExpect(status().isNotFound());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id2))
                .andExpect(status().isBadRequest());

    }


}

