package org.example.repository.base;

import org.example.model.Course;
import org.example.model.Master;

import java.util.List;
import java.util.Optional;

public interface MasterRepository {
    void save(Master master);
    boolean update(Master master);
    Optional<Master> findById(Long id);
    List<Master> findAll();
    Optional<Master> findByUsername(String username);
    List<Course> findCoursesByMasterId(Long masterId);
}