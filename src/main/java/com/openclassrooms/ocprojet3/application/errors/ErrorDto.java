package com.openclassrooms.ocprojet3.application.errors;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error response")
public record ErrorDto(

        @Schema(description = "The error message", example = "An error occured")
        String message) {
}
