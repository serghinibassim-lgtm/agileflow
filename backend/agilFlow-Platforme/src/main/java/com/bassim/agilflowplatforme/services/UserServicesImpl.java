package com.bassim.agilflowplatforme.services;

import com.bassim.agilflowplatforme.entities.Role;
import com.bassim.agilflowplatforme.entities.User;
import com.bassim.agilflowplatforme.enums.RoleName;
import com.bassim.agilflowplatforme.repositories.RoleRepository;
import com.bassim.agilflowplatforme.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServicesImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void AddRoleToUser(String email, RoleName roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByRole(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User LoadUserByUSerName(String userName) throws UsernameNotFoundException {
        return userRepository.findByFirstname(userName);
    }

    @Override
    public User LoadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> ListUsers() {
        return userRepository.findAll();
    }
}
