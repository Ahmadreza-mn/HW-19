package org.example.repository;

import org.example.model.Student;
import org.example.repository.base.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentRepository extends BaseRepository<Student, Long> {
    public StudentRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Student.class);
    }
}