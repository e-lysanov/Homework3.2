package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping(path = "faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}") // GET
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping // POST
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping // PUT
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty facultyUPD = facultyService.updateFaculty(faculty);
        if (facultyUPD == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(facultyUPD);
    }

    @DeleteMapping("{id}") // DELETE
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("byColor/{color}")
    public Collection<Faculty> filterFacultiesByColor(@PathVariable String color) {
        return facultyService.filterFacultiesByColor(color);
    }


    @GetMapping("byNameOrColor")
    public ResponseEntity getFacultiesByNameOrColor(@RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.findFacultiesByNameOrColor(name, color));
    }

    @GetMapping("studentsByID/{id}")
    public Collection<Student> getStudentsByIdOfFaculty(@PathVariable Long id) {
        return facultyService.getStudentsOfFaculty(id);
    }

    @GetMapping("largestNameOfFaculty")
    public ResponseEntity<String> getLargestNameOfFaculty() {
        String largestName = facultyService.getLargestNameOfFaculty();
        return ResponseEntity.ok(largestName);
    }
}
