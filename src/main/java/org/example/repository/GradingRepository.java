package org.example.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GradingRepository {
    private final SessionFactory sessionFactory;

    public GradingRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Grading grading) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(grading);
            tx.commit();
        }
    }

    public Grading findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Grading.class, id);
        }
    }

    public void delete(Grading grading) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(grading);
            tx.commit();
        }
    }

    public void update(Grading grading) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(grading);
            tx.commit();
        }
    }
}