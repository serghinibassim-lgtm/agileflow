package com.bassim.agilflowplatforme.config;

import com.bassim.agilflowplatforme.entities.Role;
import com.bassim.agilflowplatforme.entities.User;
import com.bassim.agilflowplatforme.enums.RoleName;
import com.bassim.agilflowplatforme.repositories.RoleRepository;
import com.bassim.agilflowplatforme.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@Configuration
public class DataInitializer {

 public static final String DEFAULT_PASSWORD = "Password123!";

    @Bean
    public CommandLineRunner initDatabase(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            for (RoleName roleName : RoleName.values()) {
                if (roleRepository.findByRole(roleName) == null) {
                    Role role = new Role();
                    role.setRole(roleName);
                    roleRepository.save(role);
                }
            }

            Role adminSysRole = roleRepository.findByRole(RoleName.ADMIN_SYS);
            Role adminOrgRole = roleRepository.findByRole(RoleName.ADMIN_ORGANISATION);
            Role productOwnerRole = roleRepository.findByRole(RoleName.PRODUCT_OWNER);
            Role scrumMasterRole = roleRepository.findByRole(RoleName.SCRUM_MASTER);
            Role developpeurRole = roleRepository.findByRole(RoleName.DEVELOPPER);


            String encodedPassword = passwordEncoder.encode(DEFAULT_PASSWORD);

            createUserIfNotFound(userRepository, "Admin", "System", "admin.sys@agileflow.com", encodedPassword, Set.of(adminSysRole));
            createUserIfNotFound(userRepository, "Admin", "Organisation", "admin.org@agileflow.com", encodedPassword, Set.of(adminOrgRole));
            createUserIfNotFound(userRepository, "Product", "Owner", "po@agileflow.com", encodedPassword, Set.of(productOwnerRole));
            createUserIfNotFound(userRepository, "Scrum", "Master", "sm@agileflow.com", encodedPassword, Set.of(scrumMasterRole));
            createUserIfNotFound(userRepository, "Dev", "Junior", "dev@agileflow.com", encodedPassword, Set.of(developpeurRole));

            createUserIfNotFound(userRepository, "Lead", "Dev", "lead.dev@agileflow.com", encodedPassword, Set.of(developpeurRole, scrumMasterRole));
            createUserIfNotFound(userRepository, "Super", "Admin", "superadmin@agileflow.com", encodedPassword, Set.of(adminSysRole, adminOrgRole));


        };
    }

    private void createUserIfNotFound(
            UserRepository userRepository,
            String firstname,
            String lastname,
            String email,
            String encodedPassword,
            Set<Role> roles
    ) {
        if (userRepository.findByEmail(email) == null) {
            User user = User.builder()
                    .firstname(firstname)
                    .lastname(lastname)
                    .email(email)
                    .password(encodedPassword)
                    .roles(new HashSet<>(roles))
                    .build();
            userRepository.save(user);
            System.out.println("👤 Utilisateur créé : {} | Rôles : {}"+ email + roles.stream().map(r -> r.getRole().name()).toList());
        } else {

            System.out.println("ℹ️ Utilisateur existant déjà : {}"+ email);
        }
    }
}
