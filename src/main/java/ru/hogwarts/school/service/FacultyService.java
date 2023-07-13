package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastID = 0;


    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastID);
        faculties.put(lastID, faculty);
        return faculty;
    }
//   C - create

    public Faculty findFaculty(long id) {
        if (faculties.containsKey(id)) {
            return faculties.get(id);
        }
        return null;
    }
//   R - read

    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }
//   U - update

    public Faculty deleteFaculty(long id) {
        if (faculties.containsKey(id)) {
            return faculties.remove(id);
        }
        return null;
    }
//  D -  delete

    public Collection<Faculty> filterFacultiesByColor(String color) {
        Collection<Faculty> filteredFaculties = new ArrayList<>();
        for (long i = 1; i <= lastID; i++) {
            if (faculties.get(i).getColor().equals(color)) {
                filteredFaculties.add(faculties.get(i));
            }
        }
        return filteredFaculties;
    }
}
