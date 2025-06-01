package org.example.service.impl;

import org.example.repository.ManagerRepository;
import org.example.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public void createAdminIfNotExists() {

    }
}
