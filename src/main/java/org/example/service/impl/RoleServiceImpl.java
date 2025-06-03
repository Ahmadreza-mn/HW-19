package org.example.service.impl;

import jakarta.persistence.EntityManager;
import org.example.model.Role;
import org.example.repository.base.RoleRepository;
import org.example.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final EntityManager entityManager;

    public RoleServiceImpl(RoleRepository roleRepository, EntityManager entityManager) {
        this.roleRepository = roleRepository;
        this.entityManager = entityManager;
    }

    @Override
    public void createRolesIfNotExist() {
        entityManager.getTransaction().begin();

        List<String> defaultRoles = List.of("ADMIN", "STUDENT", "MASTER");
        for (String roleName : defaultRoles) {
            if (!roleRepository.existsByName(roleName)) {
                roleRepository.save(new Role(roleName));
            }
        }

        entityManager.getTransaction().commit();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}