package org.example.repository.base.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.model.Course;
import org.example.model.Master;
import org.example.repository.base.MasterRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class MasterRepositoryImpl implements MasterRepository {

    private EntityManager entityManager;

    @Override
    public void save(Master master) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(master);
    }

    @Override
    public boolean update(Master master) {
        Session session = entityManager.unwrap(Session.class);
        try {
            session.merge(master);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Master> findById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Master master = session.get(Master.class, id);
        return Optional.ofNullable(master);
    }

    @Override
    public List<Master> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Master", Master.class).list();
    }

    @Override
    public Optional<Master> findByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        try {
            Master master = session.createQuery(
                            "FROM Master m WHERE m.username = :username", Master.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(master);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> findCoursesByMasterId(Long masterId) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(
                        "SELECT c FROM Course c WHERE c.master.id = :masterId", Course.class)
                .setParameter("masterId", masterId)
                .list();
    }
}