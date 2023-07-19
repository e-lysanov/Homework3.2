package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping // POST
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping // PUT
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student studentUPD = studentService.updateStudent(student);
        if (studentUPD == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(studentUPD);
    }

    @DeleteMapping("{id}") // DELETE
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("byAge/{age}")
    public Collection<Student> filterStudentsByAge(@PathVariable int age) {
        return studentService.filterStudentsByAge(age);
    }

    @GetMapping("byAgeBetween")
    public Collection<Student> getStudentsByAgeBetween(@RequestParam int fromAge, @RequestParam int toAge) {
        return studentService.getStudentsByAgeBetween(fromAge, toAge);
    }

    @GetMapping("getFacultyByID/{id}")
    public String getFacultyOfStudent(@PathVariable Long id) {
        return studentService.getFacultyOfStudent(id).toString();
    }
}
