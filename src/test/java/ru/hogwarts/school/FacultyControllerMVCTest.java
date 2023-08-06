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
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private FacultyService facultyService;

    @SpyBean
    private AvatarService avatarService;

    @SpyBean
    private StudentService studentService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void testGetFacultyInfo() throws Exception {
        Long id = 1L;
        String name = "testName";
        String color = "testColor";

        Faculty faculty = new Faculty();
        faculty.setColor(color);
        faculty.setName(name);
        faculty.setId(id);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id)) //send
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testCreateFaculty() throws Exception {
        Long id = 1L;
        String name = "testName";
        String color = "testColor";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setColor(color);
        faculty.setName(name);
        faculty.setId(id);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")//send
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        Long id = 1L;
        String name = "testName";
        String color = "testColor";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setColor(color);
        faculty.setName(name);
        faculty.setId(id);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")//send
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Long id = 1L;
        String name = "testName";
        String color = "testColor";

        Faculty faculty = new Faculty();
        faculty.setColor(color);
        faculty.setName(name);
        faculty.setId(id);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id))//send
                .andExpect(status().isOk()); //receive
    }

    @Test
    public void testFilterFacultiesByColor() throws Exception {
        String color = "testColor";

        Faculty firstFaculty = new Faculty();
        firstFaculty.setColor(color);
        firstFaculty.setName("firstFaculty");
        firstFaculty.setId(1L);

        Faculty secondFaculty = new Faculty();
        secondFaculty.setColor(color);
        secondFaculty.setName("secondFaculty");
        secondFaculty.setId(2L);

        Collection<Faculty> testFaculties = new ArrayList<>();
        testFaculties.add(firstFaculty);
        testFaculties.add(secondFaculty);

        when(facultyRepository.findFacultiesByColorIgnoreCase(any(String.class))).thenReturn(testFaculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/byColor/" + color)) //send
                .andExpect(status().isOk())//receive
                .andExpect(jsonPath("$[0].color").value(color))
                .andExpect(jsonPath("$[1].color").value(color));
    }

    @Test
    public void testGetFacultiesByNameOrColor() throws Exception {
        String color = "testColor";
        String name = "testName";

        Faculty firstFaculty = new Faculty();
        firstFaculty.setColor(color);
        firstFaculty.setName(name);
        firstFaculty.setId(1L);

        Faculty secondFaculty = new Faculty();
        secondFaculty.setColor(color);
        secondFaculty.setName("secondFaculty");
        secondFaculty.setId(2L);

        Collection<Faculty> testFacultiesByColor = new ArrayList<>();
        testFacultiesByColor.add(firstFaculty);
        testFacultiesByColor.add(secondFaculty);

        Collection<Faculty> testFacultiesByName = new ArrayList<>();
        testFacultiesByName.add(firstFaculty);

        when(facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(null, color)).thenReturn(testFacultiesByColor);
        when(facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(name, null)).thenReturn(testFacultiesByName);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/byNameOrColor?color=" + color)) //send
                .andExpect(status().isOk())//receive
                .andExpect(jsonPath("$[0].color").value(color))
                .andExpect(jsonPath("$[1].color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/byNameOrColor?name=" + name)) //send
                .andExpect(status().isOk())//receive
                .andExpect(jsonPath("$[0].name").value(name));
    }

    @Test
    public void testGetStudentsByIdOfFaculty() throws Exception {
        String color = "testColor";
        Long id = 10L;
        String name = "testName";

        Faculty faculty = new Faculty();
        faculty.setColor(color);
        faculty.setName(name);
        faculty.setId(id);

        Student firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("firstStudent");
        firstStudent.setAge(1);
        firstStudent.setFaculty(faculty);

        Student secondStudent = new Student();
        secondStudent.setId(2L);
        secondStudent.setName("secondStudent");
        secondStudent.setAge(2);
        firstStudent.setFaculty(faculty);

        Collection<Student> testStudents = new ArrayList<>();
        testStudents.add(firstStudent);
        testStudents.add(secondStudent);
        faculty.setStudents(testStudents);

        when(facultyRepository.getById(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/studentsByID/" + id)) //send
                .andExpect(status().isOk())//receive
                .andExpect(jsonPath("$[0].name").value("firstStudent"))
                .andExpect(jsonPath("$[1].name").value("secondStudent"));
    }
}
