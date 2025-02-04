package ru.hogwarts.school.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.school.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
}
