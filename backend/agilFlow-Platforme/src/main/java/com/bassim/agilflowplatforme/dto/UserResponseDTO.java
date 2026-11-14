package com.bassim.agilflowplatforme.dto;

import com.bassim.agilflowplatforme.entities.Role;

import java.util.Set;

public record UserResponseDTO(String user_id,
                              String firstname,
                              String lastname,
                              String email,
                              String password,
                              Set<Role> roles) {
}
