package org.example.repository;

import org.example.model.Course;
import org.example.repository.base.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class CourseRepository extends BaseRepository<Course, Long> {
    public CourseRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Course.class);
    }
}