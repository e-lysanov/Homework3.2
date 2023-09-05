package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.StudentService;

@RestController
public class AmountOfStudentsController {

    private final StudentService studentService;

    public AmountOfStudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAmountOfStudents")
    public Integer getAmountOfStudents() {
        return studentService.getAmountOfStudents();
    }
}
