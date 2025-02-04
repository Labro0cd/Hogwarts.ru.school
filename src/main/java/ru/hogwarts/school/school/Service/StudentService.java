package ru.hogwarts.school.school.Service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.school.Exception.StudentNotFoundException;
import ru.hogwarts.school.school.model.Student;
import ru.hogwarts.school.school.repository.StudentRepository;
import ru.hogwarts.school.school.Exception.StudentNotFoundException;
import java.util.Collection;


import static java.util.stream.Collectors.toList;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student wasn't found"));
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public Collection<Student> findAgeStudent(int age) {
        return studentRepository.findAll().stream()
                .filter(e -> e.getAge()==age)
                .collect(toList());
    }
}
