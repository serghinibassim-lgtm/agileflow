package com.bassim.agilflowplatforme.services;

import com.bassim.agilflowplatforme.entities.Role;
import com.bassim.agilflowplatforme.entities.User;
import com.bassim.agilflowplatforme.enums.RoleName;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserServices {

    User addUser(User user);
    Role addNewRole(Role role);
    void AddRoleToUser(String username, RoleName roleName);
    User LoadUserByUSerName(String userName)throws UsernameNotFoundException;
    User LoadUserByEmail(String email);
    List<User>  ListUsers();
}
