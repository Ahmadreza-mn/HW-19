package org.example.repository.base;

import org.example.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void save(Student student);

    void update(Student student);

    Optional<Student> findById(Long id);

    Optional<Student> findByUsername(String username);

    List<Student> findAll();
}
