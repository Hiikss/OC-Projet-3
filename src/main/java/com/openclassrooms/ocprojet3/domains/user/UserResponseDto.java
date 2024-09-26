package com.openclassrooms.ocprojet3.domains.user;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "User response data")
public record UserResponseDto(

        @Schema(description = "The user's id", example = "1")
        long id,

        @Schema(description = "The user's name", example = "John Doe")
        String name,

        @Schema(description = "The user's email", example = "test@test.com")
        String email,

        @Schema(description = "The user's creation timestamp", example = "2024-09-22T19:06:45Z")
        LocalDateTime createdAt,

        @Schema(description = "The user's last update timestamp", example = "2024-09-22T19:06:45Z")
        LocalDateTime updatedAt) {
}
