package ru.hogwarts.school.school.Service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.school.Exception.FacultyNotFoundException;
import ru.hogwarts.school.school.entity.Faculty;
import ru.hogwarts.school.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException("Faculty wasn't found"));
    }
    public Faculty egitFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void removeFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findColorFaculty(String color) {
        return facultyRepository.findAll().stream()
                .filter(e-> Objects.equals(e.getColor(), color))
                .collect(toList());
    }

}
