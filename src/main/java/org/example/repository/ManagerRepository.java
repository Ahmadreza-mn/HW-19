package org.example.repository;

import org.example.model.Manager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ManagerRepository {
    private final SessionFactory sessionFactory;

    public ManagerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Manager manager) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(manager);
            tx.commit();
        }
    }

    public Manager findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Manager.class, id);
        }
    }

    public void delete(Manager manager) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(manager);
            tx.commit();
        }
    }

    public void update(Manager manager) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(manager);
            tx.commit();
        }
    }
}
