package org.example.repository.base;

import org.example.model.Role;

public interface RoleRepository {
    void save(Role role);
    Role findByName(String name);
    boolean existsByName(String name);
}