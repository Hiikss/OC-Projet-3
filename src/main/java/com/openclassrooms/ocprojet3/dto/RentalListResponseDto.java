package com.openclassrooms.ocprojet3.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Rental list response data")
public record RentalListResponseDto(

        @Schema(description = "The rental list")
        List<RentalResponseDto> rentals) {
}
