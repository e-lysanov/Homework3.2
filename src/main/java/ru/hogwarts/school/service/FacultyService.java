package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
//   C - create

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }
//   R - read

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
//   U - update

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }
//  D -  delete

    public Collection<Faculty> filterFacultiesByColor(String color) {
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

//    public Collection<Faculty> filterFacultiesByName(String name) {
//        return facultyRepository.findFacultiesByNameIgnoreCase(name);
//    }

    public Collection<Faculty> findFacultiesByNameOrColor(String name, String color) {
        return facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
}
