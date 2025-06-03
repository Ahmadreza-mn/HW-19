package org.example.repository.base;

import org.example.model.Manager;

import java.util.Optional;

public interface ManagerRepository {
    Optional<Manager> findByUsername(String username);

    void save(Manager manager);
}

