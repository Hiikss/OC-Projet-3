package com.openclassrooms.ocprojet3.dto;

import java.time.LocalDateTime;

public record RentalResponseDto(
        long id,
        String name,
        int surface,
        int price,
        String picture,
        String description,
        long ownerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
