package ru.hogwarts.school.school.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.school.Service.FacultyService;

import ru.hogwarts.school.school.model.Faculty;


import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Faculty getFacultyInfo(@PathVariable long id) {
        return service.findFaculty(id);
    }

    @PostMapping
    public Faculty createFaculty(Faculty faculty) {
        return service.createFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        service.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public Faculty editFaculty(Faculty faculty) {
        return service.egitFaculty(faculty);
    }

    @GetMapping("findColor")
    public Collection<Faculty> findColorFaculties(String color) {
        return service.findColorFaculty(color);
    }
}
