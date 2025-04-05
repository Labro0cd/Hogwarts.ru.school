package ru.hogwarts.school.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.school.entity.Avatar;

import java.util.List;
import java.util.Optional;

public interface RepositoryAvatars extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);

}
