package org.example.repository.base.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.model.Manager;
import org.example.repository.base.ManagerRepository;
import org.hibernate.Session;

import java.util.Optional;

public class ManagerRepositoryImpl implements ManagerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Manager> findByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        try {
            Manager manager = session.createQuery(
                            "FROM Manager m WHERE m.username = :username", Manager.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(manager);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Manager manager) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(manager);
    }
}