package ru.hogwarts.school.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.school.model.Avatar;

import java.util.Optional;

public interface RepositoryAvatars extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
}
