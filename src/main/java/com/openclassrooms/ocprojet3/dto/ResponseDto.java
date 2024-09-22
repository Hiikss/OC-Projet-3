package com.openclassrooms.ocprojet3.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response after creating or updating resource")
public record ResponseDto(

        @Schema(description = "The message", example = "Resource created !")
        String message) {
}
