package org.example.service.impl;

import org.example.dto.UserDto;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void updateUser(Long id, UserDto updatedUser) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public void approveUser(Long id) {

    }

    @Override
    public void disApproveUser(Long id) {

    }

    @Override
    public List<User> searchUsers(String keyword) {
        return List.of();
    }

    @Override
    public List<User> filterByRoleAndName(String roleName, String name) {
        return List.of();
    }
}
