package org.example.service.impl;

import org.example.dto.UserDto;
import org.example.model.User;
import org.example.repository.base.UserRepository;
import org.example.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateUser(Long id, UserDto updatedUser) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = optUser.get();
        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());

        userRepository.update(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void approveUser(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            User user = optUser.get();
            user.setApproved(true);
            userRepository.update(user);
        }
    }

    @Override
    public void disApproveUser(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            User user = optUser.get();
            user.setApproved(false);
            userRepository.update(user);
        }
    }

    @Override
    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsers(keyword);
    }

    @Override
    public List<User> filterByRoleAndName(String roleName, String name) {
        return userRepository.filterByRoleAndName(roleName, name);
    }
}
