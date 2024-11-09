package br.com.luishmalafaia.ctrlstock.user.dto;

import br.com.luishmalafaia.ctrlstock.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterRequestDTO(
        @NotNull(message = "O campo Nome é obrigatório!")
        @NotEmpty(message = "O campo Nome é obrigatório!")
        String name,

        @NotNull(message = "O campo E-mail é obrigatório!")
        @NotEmpty(message = "O campo E-mail é obrigatório!")
        @Email(message = "O campo E-mail é inválido!")
        String email,

        @NotNull(message = "O campo Senha é obrigatório!")
        @NotEmpty(message = "O campo Senha é obrigatório!")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "O campo Senha é inválido!")
        String password,
        UserRole role
) {}
