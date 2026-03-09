package io.github.arthur.Fintrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "Campo obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Campo obrigatório")
        String password) {
}
