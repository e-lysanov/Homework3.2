package ru.hogwarts.school;

import net.minidev.json.JSONObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
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
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + 1, String.class))
                .isNotNull();
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setName("test");
        student.setAge(77);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();

//        Assertions
//                .assertThat(this.restTemplate.delete("http://localhost:" + port + "/student/" + student.getId(), String.class);)
//                .isNotNull();

//        this.restTemplate.delete("http://localhost:" + port + "/student/" + student.getId());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student studentUPD = new Student();
        studentUPD.setName("testUPD");
        studentUPD.setAge(88);
        studentUPD.setId(39L);

//        JSONObject studentObject;
//        studentObject = new JSONObject();
//        studentObject.put("id", 39L);
//        studentObject.put("name", "testUPD");
//        studentObject.put("age", 88);
//        System.out.println(studentObject);

        this.restTemplate.put("http://localhost:" + port + "/student", studentUPD, String.class);

//        Assertions
//                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/39", String.class))
//                .isEqualTo(studentObject.toJSONString());

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/39", String.class))
                .contains("UPD");
    }

    @Test
    public void testDeleteStudent() throws Exception {
        this.restTemplate.delete("http://localhost:" + port + "/student/" + 100);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/100", String.class))
                .doesNotContain("id");
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
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/getFacultyByID/1", String.class))
                .isNotNull();
    }
}
