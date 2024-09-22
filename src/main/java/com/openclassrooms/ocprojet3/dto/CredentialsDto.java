package com.openclassrooms.ocprojet3.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User login request data")
public record CredentialsDto(

        @Schema(description = "The user's email", example = "test@test.com")
        String email,

        @Schema(description = "The user's password", example = "test!31")
        String password) {
}
