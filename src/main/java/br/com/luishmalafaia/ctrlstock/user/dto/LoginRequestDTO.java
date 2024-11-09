package br.com.luishmalafaia.ctrlstock.user.dto;

import jakarta.validation.constraints.*;

public record LoginRequestDTO(
        @NotNull(message = "O campo E-mail é obrigatório!")
        @NotEmpty(message = "O campo E-mail é obrigatório!")
        @Email(message = "O campo E-mail é inválido!")
        String email,

        @NotNull(message = "O campo Senha é obrigatório!")
        @NotEmpty(message = "O campo Senha é obrigatório!")
        String password
) {}