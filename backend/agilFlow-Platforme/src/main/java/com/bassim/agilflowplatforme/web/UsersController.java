package com.bassim.agilflowplatforme.web;


import com.bassim.agilflowplatforme.dto.UserRequestDTO;
import com.bassim.agilflowplatforme.dto.UserResponseDTO;
import com.bassim.agilflowplatforme.entities.User;
import com.bassim.agilflowplatforme.exception.ResourceAlreadyExistsException;
import com.bassim.agilflowplatforme.exception.ResourceNotFoundException;
import com.bassim.agilflowplatforme.mappers.UserMapper;
import com.bassim.agilflowplatforme.repositories.RoleRepository;
import com.bassim.agilflowplatforme.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
 @AllArgsConstructor
public class UsersController {
    RoleRepository roleRepository;
    UserRepository userRepository;
    UserMapper userMapper;


    private final PasswordEncoder passwordEncoder;

    @GetMapping("/404")
    public void notfoundtest(){
        throw new  ResourceNotFoundException(" cet page n'exit pas  set pour test ");
    }

    @GetMapping("/409")
    public void alreadyexist(){
        throw new ResourceAlreadyExistsException(" cet page n'exit pas  set pour tst des conflit  ");
    }

    @GetMapping("/")
    public String home() {
        return "Bienvenue sur l'API AgileFlow ! L'application fonctionne correctement.";
    }

    @PostMapping("/users")
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO userRequest) {
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return UserMapper.toResponseDto(savedUser);
    }

    @GetMapping("/users")
    public List<UserResponseDTO> displayUsers(){


        List<User>users= userRepository.findAll();

        List<UserResponseDTO> userResponseDTOList=users.stream().map(UserMapper::toResponseDto).toList();

        return userResponseDTOList;
    }

    @GetMapping("/admin_sys")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN_SYS')")
    public String helloadmin() {
        return "hello admin system (accès réservé à ADMIN_SYS)";
    }

    @GetMapping("/devlopper")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('DEVELOPPER')")
    public String hellodevloper() {
        return "hello devlopper (accès réservé à DEVELOPPER)";
    }

    @GetMapping("/scrumMaster")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('SCRUM_MASTER')")
    public String helloScrumMaster() {
        return "hello scrum master (accès réservé à SCRUM_MASTER)";
    }

    @GetMapping("/adminOrganisation")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN_ORGANISATION')")
    public String helloadminOrganisation() {
        return "hello admin Organisation (accès réservé à ADMIN_ORGANISATION)";
    }

    @GetMapping("/productOwner")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('PRODUCT_OWNER')")
    public String helloProductOwner() {
        return "hello Product Owner (accès réservé à PRODUCT_OWNER)";
    }

}
