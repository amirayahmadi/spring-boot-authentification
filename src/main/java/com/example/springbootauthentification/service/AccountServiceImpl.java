package com.example.springbootauthentification.service;

import com.example.springbootauthentification.models.ERole;
import com.example.springbootauthentification.models.Role;
import com.example.springbootauthentification.models.User;
import com.example.springbootauthentification.repository.RoleRepository;
import com.example.springbootauthentification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AccountServiceImpl  implements AccountService {

    //injections des dependances
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    PasswordEncoder passwordEncoder;




    //methods
    @Override
    public User addNewUser(User user) {
        String pwd = user.getPassword();
        user.setPassword(passwordEncoder.encode(pwd));
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {

        return roleRepository.save(role);
    }

   /* @Override
    public void addRoleToUser(String username, ERole roleName) {
        Optional<User> user = userRepository.findByUsername(username);
       Optional<Role> role = roleRepository.findByName(roleName);
       user.getRoles().add(role);
    }*/

    @Override
    public void addRoleToUser(String username, ERole roleName) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Role> roleOptional = roleRepository.findByName(roleName);
            if (roleOptional.isPresent()) {
                Role role = roleOptional.get();
                user.getRoles().add(role);
                userRepository.save(user);
            } else {
                throw new RuntimeException("Role not found");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }


    @Override
    public   Optional<User> loadUserByUsername (String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

}
