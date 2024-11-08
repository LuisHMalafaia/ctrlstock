package br.com.luishmalafaia.ctrlstock.user.dto;

import br.com.luishmalafaia.ctrlstock.user.UserRole;

public record RegisterRequestDTO(String name, String email, String password, UserRole role) {
}
