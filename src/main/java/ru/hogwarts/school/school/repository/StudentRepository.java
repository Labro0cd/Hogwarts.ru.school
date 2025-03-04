package ru.hogwarts.school.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.school.model.Faculty;
import ru.hogwarts.school.school.model.Student;

import java.util.Collection;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

}
