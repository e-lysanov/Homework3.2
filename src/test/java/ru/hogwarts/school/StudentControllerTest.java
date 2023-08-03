package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudentInfo() throws Exception {
        Student student = new Student();
        student.setName("test");
        student.setAge(77);
        student.setId(1L);

        this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + 1, Student.class))
                .isEqualTo(student);
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setName("test");
        student.setAge(77);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student studentUPD = new Student();
        studentUPD.setName("testUPD");
        studentUPD.setAge(88);
        studentUPD.setId(1L);

        this.restTemplate.put("http://localhost:" + port + "/student", studentUPD, String.class);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", String.class))
                .contains("UPD");

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", Student.class))
                .isEqualTo(studentUPD);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        this.restTemplate.delete("http://localhost:" + port + "/student/" + 100);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/100", String.class))
                .doesNotContain("id");

        Student nullStudent = new Student();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/100", Student.class))
                .isEqualTo(nullStudent);
    }

    @Test
    public void testFilterStudentsByAge() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/byAge/1", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByAgeBetween() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/byAgeBetween?fromAge=1&toAge=10", String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyOfStudent() throws Exception {
//        Faculty faculty = new Faculty();
//        faculty.setColor("color");
//        faculty.setName("name");
//        faculty.setId(1L);

//        Student student = new Student();
//        student.setName("test");
//        student.setAge(77);
//        student.setFaculty(faculty);

//        this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class);

//        Assertions
//                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/getFacultyByID/1", Faculty.class))
//                .isEqualTo(faculty);

//        Assertions
//                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/getFacultyByID/1", Faculty.class))
//                .isNull();

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/getFacultyByID/100", String.class))
                .isNotNull();
    }
}
