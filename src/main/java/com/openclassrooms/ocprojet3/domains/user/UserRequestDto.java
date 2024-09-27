package com.openclassrooms.ocprojet3.domains.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User request data")
public record UserRequestDto(

        @Schema(description = "The user's email", example = "test@test.com")
        String email,

        @Schema(description = "The user's password", example = "test!31")
        String password,

        @Schema(description = "The user's name", example = "John Doe")
        String name) {
}
