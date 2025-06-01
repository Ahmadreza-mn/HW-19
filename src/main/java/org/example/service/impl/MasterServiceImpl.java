package org.example.service.impl;

import org.example.dto.MasterDto;
import org.example.model.Course;
import org.example.model.Master;
import org.example.repository.MasterRepository;
import org.example.service.MasterService;

import java.util.List;

public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;

    public MasterServiceImpl(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Override
    public void masterRegister(MasterDto masterDto) {

    }

    @Override
    public boolean updateMaster(Long id, MasterDto masterDto) {
        return false;
    }

    @Override
    public Master findById(Long id) {
        return null;
    }

    @Override
    public List<Master> findAll() {
        return List.of();
    }

    @Override
    public Master findByUsername(String username) {
        return null;
    }

    @Override
    public List<Course> findMasterCourses(Long masterId) {
        return List.of();
    }

    @Override
    public boolean checkPassword(Master master, String oldPassword) {
        return false;
    }

    @Override
    public void changePassword(Master master, String newPassword) {

    }
}
