package ru.hogwarts.school.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.school.entity.Faculty;

import java.util.stream.Stream;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query("SELECT f.name FROM Faculty f")
    Stream<String> findAllNamesAsStream();
}
