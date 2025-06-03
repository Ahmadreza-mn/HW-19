package org.example.service.impl;

import org.example.dto.MasterDto;
import org.example.model.Course;
import org.example.model.Master;
import org.example.repository.base.MasterRepository;
import org.example.service.MasterService;

import java.util.List;
import java.util.Optional;

public class MasterServiceImpl implements MasterService {

    private final MasterRepository masterRepository;

    public MasterServiceImpl(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Override
    public void masterRegister(MasterDto masterDto) {
        Optional<Master> existing = masterRepository.findByUsername(masterDto.getUsername());
        if (existing.isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Master master = new Master();
        master.setUsername(masterDto.getUsername());
        master.setPassword(hashPassword(masterDto.getPassword())); // Implement proper hashing!
        master.setFullName(masterDto.getFullName());
        masterRepository.save(master);
    }

    @Override
    public boolean updateMaster(Long id, MasterDto masterDto) {
        Optional<Master> optionalMaster = masterRepository.findById(id);
        if (optionalMaster.isEmpty()) return false;

        Master master = optionalMaster.get();
        master.setFullName(masterDto.getFullName());


        return masterRepository.update(master);
    }

    @Override
    public Master findById(Long id) {
        return masterRepository.findById(id).orElse(null);
    }

    @Override
    public List<Master> findAll() {
        return masterRepository.findAll();
    }

    @Override
    public Master findByUsername(String username) {
        return masterRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<Course> findMasterCourses(Long masterId) {
        return masterRepository.findCoursesByMasterId(masterId);
    }

    @Override
    public boolean checkPassword(Master master, String oldPassword) {

        return master.getPassword().equals(hashPassword(oldPassword));
    }

    @Override
    public void changePassword(Master master, String newPassword) {
        master.setPassword(hashPassword(newPassword));
        masterRepository.update(master);
    }

    private String hashPassword(String password) {

        return password;
    }
}