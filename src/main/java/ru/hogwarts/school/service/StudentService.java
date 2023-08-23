package ru.hogwarts.school.service;

import liquibase.pro.packaged.L;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }
//   C - create

    public Student findStudent(long id) {
        logger.info("Was invoked method for find student");
        return studentRepository.findById(id).get();
    }
//   R - read

    public Student updateStudent(Student student) {
        logger.info("Was invoked method for update student");
        return studentRepository.save(student);
    }
//   U - update

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }
//  D -  delete

    public Collection<Student> filterStudentsByAge(int age) {
        logger.info("Was invoked method for filter students by age");
        return studentRepository.findStudentsByAge(age);
    }

    public Collection<Student> getStudentsByAgeBetween(int fromAge, int toAge) {
        logger.info("Was invoked method for get students by age between");
        return studentRepository.findStudentsByAgeBetween(fromAge, toAge);
    }

    public Faculty getFacultyOfStudent(long id) {
        logger.info("Was invoked method for get faculty of student");
        return studentRepository.getById(id).getFaculty();
    }

    public Integer getAmountOfStudents() {
        logger.info("Was invoked method for get amount of students");
        return studentRepository.getAmountOfStudents();
    }

    public Float getAverageAgeOfStudents() {
        logger.info("Was invoked method for get average age of students");
        return studentRepository.getAverageAgeOfStudents();
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Was invoked method for get last five students");
        return studentRepository.getLastFiveStudents();
    }

    public List<String> getAllStudentsStartsWithA() {
        logger.info("Was invoked method for get all names of students starts with A");
        List<String> names = studentRepository.findAll()
                .stream()
                .filter(student -> student.getName().toUpperCase().startsWith("A"))
                .map(student -> student.getName().toUpperCase())
                .collect(Collectors.toList());
        return names;
    }

    public double getAverageAgeOfAllStudents() {
        logger.info("Was invoked method for get average age of all students");
        double averageAge = studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .getAsDouble();
        return averageAge;
    }

    public void getSixStudents() {
        logger.info("Was invoked method for get six students");
//        List<String> studentsNames = studentRepository.findAll()
//                .stream()
//                .map(student -> student.getName())
//                .collect(Collectors.toList());

//        System.out.println(studentsNames.get(0) + " 0");
//        System.out.println(studentsNames.get(1) + " 1");

        System.out.println(studentRepository.findAll().get(0).getName() + " 0");
        System.out.println(studentRepository.findAll().get(1).getName() + " 1");

        new Thread(() -> {
            System.out.println(studentRepository.findAll().get(2).getName() + " 2");
            System.out.println(studentRepository.findAll().get(3).getName() + " 3");
//            System.out.println(studentsNames.get(2) + " 2");
//            System.out.println(studentsNames.get(3) + " 3");
        }).start();

        new Thread(() -> {
            System.out.println(studentRepository.findAll().get(4).getName() + " 4");
            System.out.println(studentRepository.findAll().get(5).getName() + " 5");
//            System.out.println(studentsNames.get(4) + " 4");
//            System.out.println(studentsNames.get(5) + " 5");
        }).start();
    }

    public synchronized void printStudent(int id) {
        System.out.println(studentRepository.findAll().get(id).getName() + " " + id);
    }

    public void getSixStudentsSynchronized() {
        logger.info("Was invoked method for get six students synchronized");

        printStudent(0);
        printStudent(1);

        new Thread(() -> {
            printStudent(2);
            printStudent(3);
        }).start();

        new Thread(() -> {
            printStudent(4);
            printStudent(5);
        }).start();
    }
}
