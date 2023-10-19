package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testGetFacultyInfo() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("testName");
        faculty.setColor("testColor");
        faculty.setId(1L);

        this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + 1, Faculty.class))
                .isNotNull().isEqualTo(faculty);
    }

    @Test
    public void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("testName");
        faculty.setColor("testColor");

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        Faculty facultyUPD = new Faculty();
        facultyUPD.setName("testNameUPD");
        facultyUPD.setColor("testColorUPD");
        facultyUPD.setId(1L);

        this.restTemplate.put("http://localhost:" + port + "/faculty", facultyUPD, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/1", String.class))
                .contains("UPD");

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/1", Faculty.class))
                .isEqualTo(facultyUPD);
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        this.restTemplate.delete("http://localhost:" + port + "/faculty/" + 100);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/100", String.class))
                .doesNotContain("id");

        Faculty nullFaculty = new Faculty();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/100", Faculty.class))
                .isEqualTo(nullFaculty);
    }

    @Test
    public void testFilterFacultiesByColor() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/byColor/testColor", String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultiesByNameOrColor() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/byNameOrColor?color=testColor", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByIdOfFaculty() throws Exception {
//        Faculty faculty = new Faculty();
//        faculty.setName("testName");
//        faculty.setColor("testColor");
//        faculty.setId(1L);

//        this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class);
//
//        Student firstStudent = new Student();
//        firstStudent.setId(1L);
//        firstStudent.setName("firstStudent");
//        firstStudent.setAge(2);
//
//        Student secondStudent = new Student();
//        secondStudent.setId(2L);
//        secondStudent.setName("secondStudent");
//        secondStudent.setAge(9);
//
//        Collection<Student> testStudents = new ArrayList<>();
//        testStudents.add(firstStudent);
//        testStudents.add(secondStudent);
//        faculty.setStudents(testStudents);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/studentsByID/1", String.class))
                .isNotNull();

//        Assertions
//                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/studentsByID/1", Collection.class))
//                .isEqualTo(testStudents);
    }
}
