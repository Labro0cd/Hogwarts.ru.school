package ru.hogwarts.school.school.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.school.Exception.StudentNotFoundException;
import ru.hogwarts.school.school.Service.StudentService;
import ru.hogwarts.school.school.entity.Student;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Student getStudentInfo(@PathVariable long id) throws StudentNotFoundException {
        return service.findStudent(id);
    }

    @GetMapping
    public ResponseEntity findStudents(@RequestParam(required = false) int min,
                                       @RequestParam(required = false) int max) {
        if (min != 0 || max != 0) {
            return ResponseEntity.ok(service.findByAgeBetween(min, max));
        }
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public Student createStudent(Student student) {
        return service.createStudent(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        service.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public Student editStudent(Student student) {
        return service.editStudent(student);
    }

    @GetMapping("findAge")
    public Collection<Student> findStudentsFromAge(int age) {
        return service.findAgeStudent(age);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @GetMapping("countStudent")
    public int countAllStudent() {
        return service.countAllStudent();
    }

    @GetMapping("AvgAgeStudent")
    public double avgAgeAllStudents() {
        return service.avgAgeAllStudents();
    }

    @GetMapping("lastFiveStudents")
    public List<Student> lastFiveStudents() {
        return service.LastFiveStudents();
    }

    @GetMapping("allStudentNameStarringWithA")
    public List<Student> allStudentNameStarringWithA() {
        return service.filterNamesStartingWithA();
    }

    @GetMapping("findAverageStudentAge")
    public double findAverageStudentAge() {
        return service.findMiddleAge();
    }
}
