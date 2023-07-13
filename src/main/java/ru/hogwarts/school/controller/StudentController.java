package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping(path = "student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}") // GET
    public Student getStudentInfo(@PathVariable Long id) {
//        Student student = studentService.findStudent(id);
//        if (student == null) {
//            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(student);

        return studentService.findStudent(id);
    }

    @PostMapping // POST
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping // PUT
    public Student updateStudent(@RequestBody Student student) {
//        Student studentUPD = studentService.updateStudent(student);
//        if (studentUPD == null) {
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        return ResponseEntity.ok(studentUPD);

        return studentService.updateStudent(student);
    }

    @DeleteMapping("{id}") // DELETE
    public Student deleteStudent(@PathVariable Long id) {
//        Student student = studentService.deleteStudent(id);
//        if (student == null) {
//            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(student);

        return studentService.deleteStudent(id);
    }

    @GetMapping("byAge/{age}")
    public Collection<Student> filterStudentsByAge(@PathVariable int age) {
        return studentService.filterStudentsByAge(age);
    }
}
