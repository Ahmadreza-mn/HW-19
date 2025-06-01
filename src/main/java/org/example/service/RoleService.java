package org.example.service;

import org.example.model.Role;

public interface RoleService {
    void createRolesIfNotExist();

    Role findByName(String name);
}
