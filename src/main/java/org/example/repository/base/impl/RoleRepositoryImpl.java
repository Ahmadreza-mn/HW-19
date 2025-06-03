package org.example.repository.base.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.model.Role;
import org.example.repository.base.RoleRepository;

public class RoleRepositoryImpl implements RoleRepository {

    private final EntityManager entityManager;

    public RoleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findByName(String name) {
        try {
            TypedQuery<Role> query = entityManager.createQuery(
                    "SELECT r FROM Role r WHERE r.name = :name", Role.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean existsByName(String name) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(r) FROM Role r WHERE r.name = :name", Long.class)
                .setParameter("name", name)
                .getSingleResult();
        return count > 0;
    }
}