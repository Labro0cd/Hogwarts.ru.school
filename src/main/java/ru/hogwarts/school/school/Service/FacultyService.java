package ru.hogwarts.school.school.Service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.school.Exception.FacultyNotFoundException;
import ru.hogwarts.school.school.entity.Faculty;
import ru.hogwarts.school.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;


import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class FacultyService {

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Method called : createFaculty on FacultyService");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow(() -> {
            logger.error("Faculty with id {} wasn't found", id);
            return new FacultyNotFoundException("Faculty wasn't found");
        });
    }

    public Faculty egitFaculty(Faculty faculty) {
        logger.info("Method called : editFaculty on FacultyService");
        return facultyRepository.save(faculty);
    }

    public void removeFaculty(long id) {
        logger.info("Method called : removeFaculty on FacultyService");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findColorFaculty(String color) {
        logger.info("Method called : findColorFaculty on FacultyService");
        return facultyRepository.findAll().stream()
                .filter(e -> Objects.equals(e.getColor(), color))
                .collect(toList());
    }

    public Optional<String> findLongestNameFaculty() {
        logger.info("Method called : findLongestNameFaculty on FacultyService");
        try (Stream<String> names = facultyRepository.findAllNamesAsStream()) {
            return names
                    .filter(Objects::nonNull)
                    .max(Comparator.comparingInt(String::length));
        }
    }

}
