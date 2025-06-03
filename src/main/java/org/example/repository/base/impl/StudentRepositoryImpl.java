package org.example.repository.base.impl;

import org.example.model.Student;
import org.example.repository.base.StudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    private final SessionFactory sessionFactory;

    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Student student = session.get(Student.class, id);
            return Optional.ofNullable(student);
        }
    }

    @Override
    public Optional<Student> findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Student s WHERE s.username = :username";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("username", username);
            Student student = query.uniqueResult();
            return Optional.ofNullable(student);
        }
    }

    @Override
    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Student";
            return session.createQuery(hql, Student.class).list();
        }
    }
}