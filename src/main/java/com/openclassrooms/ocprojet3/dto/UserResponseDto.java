package com.openclassrooms.ocprojet3.dto;

import java.time.LocalDateTime;

public record UserResponseDto(
        long id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
