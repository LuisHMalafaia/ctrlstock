package br.com.luishmalafaia.ctrlstock.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponseDTO(
        String token,
        @JsonProperty("expires_in")
        int expiresIn,
        @JsonProperty("token_type")
        String tokenType) {
}
