package org.example.service;

import org.example.dto.MasterDto;
import org.example.model.Course;
import org.example.model.Master;

import java.util.List;

public interface MasterService {
    void masterRegister(MasterDto masterDto);

    boolean updateMaster(Long id, MasterDto masterDto);

    Master findById(Long id);

    List<Master> findAll();

    Master findByUsername(String username);

    List<Course> findMasterCourses(Long masterId);

    boolean checkPassword(Master master, String oldPassword);

    void changePassword(Master master, String newPassword);

}
