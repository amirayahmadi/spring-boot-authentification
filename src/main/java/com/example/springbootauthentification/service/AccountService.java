package com.example.springbootauthentification.service;

import com.example.springbootauthentification.models.ERole;
import com.example.springbootauthentification.models.Role;
import com.example.springbootauthentification.models.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    void addRoleToUser(String username, ERole name);
    Optional<User> loadUserByUsername(String username);
    List<User> listUsers();

    List<Role> listRoles();
}
