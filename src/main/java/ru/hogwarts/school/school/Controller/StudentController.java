package ru.hogwarts.school.school.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.school.Service.StudentService;
import ru.hogwarts.school.school.model.Faculty;
import ru.hogwarts.school.school.model.Student;

import java.util.Collection;
import java.util.logging.Logger;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Student getStudentInfo(@PathVariable long id) {
        return service.findStudent(id);
    }

    @GetMapping
    public ResponseEntity findStudents(@RequestParam(required = false) int min,
                                            @RequestParam(required = false) int max) {
        if (min != 0 || max != 0) {
            return ResponseEntity.ok(service.findByAgeBetween(min, max));
        }
        return ResponseEntity.ok(service.findAllStudent());
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
}
