package com.bassim.agilflowplatforme.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "Le prénom est obligatoire")
        String firstname,

        @NotBlank(message = "Le nom est obligatoire")
        String lastname,

        @NotBlank @Email(message = "Format d'email invalide")
        String email,

        @NotBlank @Size(min = 4, message = "Minimum 8 caractères")
        String password
) {


}
