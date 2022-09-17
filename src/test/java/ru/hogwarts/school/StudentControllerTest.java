package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void postStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Hermione");
        student.setAge(12);
        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/students", student, String.class))
                .isNotNull();
    }

    @Test
    public void getStudentTest() throws Exception {
        final Long id = 52L;
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/students/" + id, String.class))
                .isEqualTo("{\"id\":52,\"name\":\"Hermione\",\"age\":12}");
    }

    @Test
    public void getStudentByAgeTest() throws Exception {
        final int age = 12;
        ResponseEntity<List<Student>> response = testRestTemplate.exchange("http://localhost:" + port + "/students/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Student>>() {
                });
        List<Student> students = response.getBody();
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/students/age?age=" + age, String.class))
                .isEqualTo("[{\"id\":42,\"name\":\"Harry Potter\",\"age\":12},{\"id\":52,\"name\":\"Hermione\",\"age\":12}]");
    }


    @Test
    public void getAllStudentsTest() throws Exception {
        ResponseEntity<List<Student>> response = testRestTemplate.exchange("http://localhost:" + port + "/students/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Student>>() {
                });
        List<Student> students = response.getBody();
        assertThat(students, hasSize(4));

    }

    private void assertThat(List<Student> students, Matcher<Collection<?>> hasSize) {
    }


    @Test
    public void updateStudentTest() throws Exception {

        Student student = new Student(52L, "Hermione Granger", 12);
        testRestTemplate.put("http://localhost:" + port + "/students/", student);
    }


    @Test
    public void findByAgeBetween() {
        final int min = 15;
        final int max = 20;
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/students/ageBetween?min=" + min + "&max=" + max, String.class))
                .isEqualTo("[]");

    }

    @Test
    public void getFacultyByIdStudent() {
        final Long id = 45L;
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/students/" + id + "student", String.class))
                .isNotEmpty();

    }

    @Test
    public void deleteStudentTest() throws Exception {
        final Long id = 52L;
        Student student = new Student();
        student.setName("Hermione Granger");
        student.setAge(12);
        student.setId(id);
        testRestTemplate.delete("http://localhost:" + port + "/students/" + id, student);
    }

}

