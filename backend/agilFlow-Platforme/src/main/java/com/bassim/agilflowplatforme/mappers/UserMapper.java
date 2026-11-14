package com.bassim.agilflowplatforme.mappers;


import com.bassim.agilflowplatforme.dto.UserRequestDTO;
import com.bassim.agilflowplatforme.dto.UserResponseDTO;
import com.bassim.agilflowplatforme.entities.Role;
import com.bassim.agilflowplatforme.entities.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO userRequestDTO){
        if(userRequestDTO==null){
            return null;
        }
        return User.builder().
                firstname(userRequestDTO.firstname()).
                lastname(userRequestDTO.lastname()).
                email(userRequestDTO.email()).
                password(userRequestDTO.password())
                .build();
    }

    public static UserResponseDTO toResponseDto(User user){
        if(user==null) return null;

        Set<Role> rolNames=(user.getRoles()!=null)? new HashSet<>(user.getRoles()) :
                Collections.emptySet();


        return new UserResponseDTO(
                user.getUser_id(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),rolNames);
    }


    public void updateEntityFromDto(){

    }
}
