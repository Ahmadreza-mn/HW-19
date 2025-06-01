package org.example.service;

import org.example.dto.UserDto;
import org.example.model.User;

import java.util.List;

public interface UserService {
    void updateUser(Long id, UserDto updatedUser);

    User findByUsername(String username);

    User findUserById(Long id);

    void approveUser(Long id);

    void disApproveUser(Long id);

    List<User> searchUsers(String keyword);

    List<User> filterByRoleAndName(String roleName, String name);
}
