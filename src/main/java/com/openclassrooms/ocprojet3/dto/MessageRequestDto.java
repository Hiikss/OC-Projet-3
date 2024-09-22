package com.openclassrooms.ocprojet3.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Message request data")
public record MessageRequestDto(

        @Schema(description = "The content of the message", example = "Lorem ipsum dolor sit amet.")
        String message,

        @Schema(description = "The author id", example = "1")
        Long userId,

        @Schema(description = "The rental id", example = "1")
        Long rentalId) {
}
