package ru.hogwarts.school;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.InfoService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private StudentService studentService;

    @SpyBean
    private AvatarService avatarService;

    @SpyBean
    private FacultyService facultyService;

    @SpyBean
    private InfoService infoService;

    @InjectMocks
    private StudentController studentController;


    @Test
    public void testGetStudentInfo() throws Exception {
        Long id = 1L;
        String name = "testName";
        int age = 10;

//        JSONObject studentObject = new JSONObject();
//        studentObject.put("name", name);
//        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

//        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)) //send
//                        .content(studentObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testCreateStudent() throws Exception {
        Long id = 1L;
        String name = "testName";
        int age = 10;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student") //send
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Long id = 1L;
        String name = "testName";
        int age = 10;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student") //send
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Long id = 1L;
        String name = "testName";
        int age = 10;

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + id)) //send
                .andExpect(status().isOk()); //receive
    }

    @Test
    public void testFilterStudentsByAge() throws Exception {
        int age = 10;

        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("firstStudent");
        firstStudent.setAge(age);

        Student secondStudent = new Student();
        secondStudent.setId(2L);
        secondStudent.setName("secondStudent");
        secondStudent.setAge(age);

        Collection<Student> testStudents = new ArrayList<>();
        testStudents.add(firstStudent);
        testStudents.add(secondStudent);

        when(studentRepository.findStudentsByAge(any(int.class))).thenReturn(testStudents);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/byAge/" + age)) //send
                .andExpect(status().isOk())//receive
                .andExpect(jsonPath("$[0].age").value("10"))
                .andExpect(jsonPath("$[1].age").value("10"));

    }

    @Test
    public void testGetStudentsByAgeBetween() throws Exception {
        int fromAge = 1;
        int toAge = 10;

        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("firstStudent");
        firstStudent.setAge(2);

        Student secondStudent = new Student();
        secondStudent.setId(2L);
        secondStudent.setName("secondStudent");
        secondStudent.setAge(9);

        Collection<Student> testStudents = new ArrayList<>();
        testStudents.add(firstStudent);
        testStudents.add(secondStudent);

        when(studentRepository.findStudentsByAgeBetween(any(int.class), any(int.class))).thenReturn(testStudents);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/byAgeBetween?fromAge=" + fromAge + "&toAge=" + toAge)) //send
                .andExpect(status().isOk())//receive
                .andExpect(jsonPath("$[0].name").value("firstStudent"))
                .andExpect(jsonPath("$[1].name").value("secondStudent"));
    }

    @Test
    public void testGetFacultyOfStudent() throws Exception {
        Long id = 1L;
        String name = "testName";
        int age = 10;

        String color = "testColor";
        Long idOfFaculty = 10L;

        Faculty faculty = new Faculty();
        faculty.setColor(color);
        faculty.setName(name);
        faculty.setId(idOfFaculty);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setFaculty(faculty);

        when(studentRepository.getById(any(Long.class))).thenReturn(student);

//        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/getFacultyByID/" + id)) //send
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(idOfFaculty));
    }
}
