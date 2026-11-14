package com.bassim.agilflowplatforme.web;


import com.bassim.agilflowplatforme.dto.UserRequestDTO;
import com.bassim.agilflowplatforme.dto.UserResponseDTO;
import com.bassim.agilflowplatforme.entities.User;
import com.bassim.agilflowplatforme.mappers.UserMapper;
import com.bassim.agilflowplatforme.repositories.RoleRepository;
import com.bassim.agilflowplatforme.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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


    @PostMapping("/users")
    public UserResponseDTO createUser(
            @Valid
            @RequestBody UserRequestDTO userRequest ){


        User user =userMapper.toEntity(userRequest);
        User savedUser=userRepository.save(user);
        return UserMapper.toResponseDto(user);


    }

    @GetMapping("/users")
    public List<UserResponseDTO> displayUsers(){


        List<User>users= userRepository.findAll();

        List<UserResponseDTO> userResponseDTOList=users.stream().map(UserMapper::toResponseDto).toList();

        return userResponseDTOList;
    }
}
