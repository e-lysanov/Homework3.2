package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

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
        return studentRepository.findStudentsByAge(age);
    }

    public Collection<Student> getStudentsByAgeBetween(int fromAge, int toAge) {
        return studentRepository.findStudentsByAgeBetween(fromAge, toAge);
    }

    public Faculty getFacultyOfStudent(long id) {
        return studentRepository.getById(id).getFaculty();
    }

    public Integer getAmountOfStudents() {
        return studentRepository.getAmountOfStudents();
    }

    public Float getAverageAgeOfStudents() {
        return studentRepository.getAverageAgeOfStudents();
    }

    public List<Student> getLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }
}
