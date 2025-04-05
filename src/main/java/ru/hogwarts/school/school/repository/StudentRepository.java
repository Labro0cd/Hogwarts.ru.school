package ru.hogwarts.school.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.school.entity.Student;

import java.util.Collection;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    int countAllStudents();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    double avgAgeAllStudents();

    @Query(value = "SELECT * FROM student ORDER BY student.id DESC LIMIT 5", nativeQuery = true)
    List<Student> lastFiveStudents();


}
