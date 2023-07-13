package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class StudentService {
    private  final HashMap<Long, Student> students = new HashMap<>();
    private long lastID = 0;

    public Student createStudent(Student student) {
        student.setId(++lastID);
        students.put(lastID, student);
        return student;
    }
//   C - create

    public Student findStudent(long id) {
//        if (students.containsKey(id)) {
//            return students.get(id);
//        }
//        return null;

        return students.get(id);
    }
//   R - read

    public Student updateStudent(Student student) {
//        if (students.containsKey(student.getId())) {
//            students.put(student.getId(), student);
//            return student;
//        } else {
//            return null;
//        }

        students.put(student.getId(), student);
        return student;
    }
//   U - update

    public Student deleteStudent(long id) {
        return students.remove(id);
    }
//  D -  delete

    public Collection<Student> filterStudentsByAge(int age) {
        Collection<Student> filteredStudents = new ArrayList<>();
        for (long i = 1; i <= lastID; i++) {
            if (students.get(i).getAge() == age) {
                filteredStudents.add(students.get(i));
            }
        }
        return filteredStudents;
    }
}
