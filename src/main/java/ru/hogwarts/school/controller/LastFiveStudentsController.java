package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
public class LastFiveStudentsController {
    private final StudentService studentService;

    public LastFiveStudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getLastFiveStudents")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }
}
