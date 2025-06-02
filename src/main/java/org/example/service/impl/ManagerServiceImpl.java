package org.example.service.impl;

import org.example.model.Manager;
import org.example.repository.base.ManagerRepository;
import org.example.service.ManagerService;

import java.util.Optional;

public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public void createAdminIfNotExists() {
        Optional<Manager> existingAdmin = managerRepository.findByUsername("admin");
        if (existingAdmin.isEmpty()) {
            Manager admin = new Manager();
            admin.setUsername("admin");
            admin.setPassword("hashed_password_here"); // hash password properly
            admin.setRole("ADMIN");
            managerRepository.save(admin);
        }
    }
}