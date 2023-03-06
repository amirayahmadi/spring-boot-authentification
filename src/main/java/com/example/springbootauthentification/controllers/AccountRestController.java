package com.example.springbootauthentification.controllers;

import com.example.springbootauthentification.models.ERole;
import com.example.springbootauthentification.models.Role;
import com.example.springbootauthentification.models.User;
import com.example.springbootauthentification.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test1")
public class AccountRestController {

    @Autowired
    AccountService accountService;


    @GetMapping(path="/users")
    public List<User> listUsers(){
        return  accountService.listUsers();
    }

    @GetMapping(path="/roles")
    public List<Role> listRoles(){
        return  accountService.listRoles();
    }

    @GetMapping(path = "/loadUserByUsername")
    public Optional<User> loadUserByUsername(@RequestParam("username") String username){
        return  accountService.loadUserByUsername(username);

    }

    @PostMapping(path = "/users")
    public User saveUser(@RequestBody User appUser){
        return accountService.addNewUser(appUser);
    }


    @PostMapping(path = "/roles")
    public Role saveRole(@RequestBody Role role){
        return accountService.addNewRole(role);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }

}



@Data
class RoleUserForm{
    private String username;
    private ERole roleName;
}