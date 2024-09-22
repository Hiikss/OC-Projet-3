package com.openclassrooms.ocprojet3.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

@Schema(description = "The rental request data")
public record RentalRequestDto(

        @Schema(description = "The name of the rental", example = "My house")
        String name,

        @Schema(description = "The surface of the rental", example = "40")
        int surface,

        @Schema(description = "The price of the rental", example = "300")
        int price,

        @Schema(description = "The picture of the rental")
        MultipartFile picture,

        @Schema(description = "The description of the rental", example = "Lorem ipsum dolor sit amet.")
        String description) {
}
