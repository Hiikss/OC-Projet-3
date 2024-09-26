package com.openclassrooms.ocprojet3.domains.rental;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Rental response data")
public record RentalResponseDto(

        @Schema(description = "The id of the rental", example = "1")
        long id,

        @Schema(description = "The name of the rental", example = "My house")
        String name,

        @Schema(description = "The surface of the rental", example = "40")
        int surface,

        @Schema(description = "The price of the rental", example = "300")
        int price,

        @Schema(description = "The url of the picture of the rental", example = "https://www.maisons-pierre.com/wp-content/uploads/2021/04/1300x600-maisons-pierre-lemag-quel-type-maison-choisir-couv.jpg")
        String picture,

        @Schema(description = "The description of the rental", example = "Lorem ipsum dolor sit amet.")
        String description,

        @Schema(description = "The owner's id of the rental", example = "1")
        long ownerId,

        @Schema(description = "The creation timestamp of the rental", example = "2024-09-22T19:06:45Z")
        LocalDateTime createdAt,

        @Schema(description = "The update timestamp of the rental", example = "2024-09-22T19:06:45Z")
        LocalDateTime updatedAt) {
}
