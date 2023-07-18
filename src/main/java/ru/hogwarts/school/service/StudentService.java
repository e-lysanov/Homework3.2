package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
//   C - create

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }
//   R - read

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
//   U - update

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
//  D -  delete

    public Collection<Student> filterStudentsByAge(int age) {
        Collection<Student> filteredStudents = studentRepository.findAll();
        filteredStudents.removeIf(student -> student.getAge() != age);
        return filteredStudents;
    }
}
