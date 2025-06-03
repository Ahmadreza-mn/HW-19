package org.example.repository.base.impl;

import org.example.model.User;
import org.example.repository.base.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            return Optional.ofNullable(user);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User u WHERE u.username = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();
            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> searchUsers(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User u WHERE lower(u.username) LIKE :kw OR lower(u.fullName) LIKE :kw";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
            return query.list();
        }
    }

    @Override
    public List<User> filterByRoleAndName(String roleName, String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = """
                SELECT u FROM User u JOIN u.roles r
                WHERE lower(r.name) = :roleName AND lower(u.fullName) LIKE :name
                """;
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("roleName", roleName.toLowerCase());
            query.setParameter("name", "%" + name.toLowerCase() + "%");
            return query.list();
        }
    }
}