package ru.hogwarts.school.school.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.school.Exception.StudentNotFoundException;
import ru.hogwarts.school.school.entity.Student;
import ru.hogwarts.school.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import static java.util.stream.Collectors.toList;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Method called : createStudent on StudentService");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElseThrow(() ->{
            logger.error("Student with id {} wasn't found", id);
            return new StudentNotFoundException("Student wasn't found");
        });
    }

    public void deleteStudent(long id) {
        logger.info("Method called : deleteStudent on StudentService");
        studentRepository.deleteById(id);
    }

    public Student editStudent(Student student) {
        logger.info("Method called : editStudent on StudentService");
        return studentRepository.save(student);
    }

    public Collection<Student> findAgeStudent(int age) {
        logger.info("Method called : findAgeStudent on StudentService");
        return studentRepository.findAll().stream()
                .filter(e -> e.getAge() == age)
                .collect(toList());
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Method called : findByAgeBetween on StudentService");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findAll() {
        logger.info("Method called : findAllStudent on StudentService");
        return studentRepository.findAll();
    }

    public int countAllStudent() {
        logger.info("Method called : countAllStudent on StudentService");
        return studentRepository.countAllStudents();
    }

    public double avgAgeAllStudents() {
        logger.info("Method called : avgAgeAllStudents on StudentService");
        return studentRepository.avgAgeAllStudents();
    }

    public List<Student> LastFiveStudents() {
        logger.info("Method called : LastFiveStudents on StudentService");
        return studentRepository.lastFiveStudents();
    }

    public List<Student> filterNamesStartingWithA() {
        return findAll().stream()
                .filter(p -> p.getName() != null && p.getName().startsWith("A"))
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    public Double findMiddleAge() {
        logger.info("Method called : findMiddleAge on StudentService");
        double avg = findAll().stream()
                .map(Student::getAge)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
        return avg;
    }

    public void printStudentsInConsoleParallel() {
        logger.info("Method called : printStudentsInConsoleParallel on StudentService");
        System.out.println(studentRepository.findById(1L));
        System.out.println(studentRepository.findById(2L));
        new Thread(() -> {
            System.out.println(studentRepository.findById(3L));
            System.out.println(studentRepository.findById(4L));
        }).start();

        new Thread(() -> {
            System.out.println(studentRepository.findById(5L));
            System.out.println(studentRepository.findById(6L));
        }).start();
    }
    public void printStudentsInConsoleSynchronized() {
        logger.info("Method called : printStudentsInConsoleSynchronized on StudentService");
        printStudentsInConsole(1L);
        printStudentsInConsole(2L);
        new Thread(() -> {
            printStudentsInConsole(3L);
            printStudentsInConsole(4L);
        }).start();

        new Thread(() -> {
            printStudentsInConsole(5L);
            printStudentsInConsole(6L);
        }).start();
    }

    public synchronized void printStudentsInConsole(Long id) {
        System.out.println(studentRepository.findById(id));
    }
}
