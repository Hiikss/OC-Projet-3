package com.openclassrooms.ocprojet3.domains.rental;

import com.openclassrooms.ocprojet3.application.common.ResponseDto;
import com.openclassrooms.ocprojet3.application.errors.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Rental", description = "Rental endpoints")
public class RentalController {

    private final RentalService rentalService;

    @Operation(summary = "Get all rentals", description = "Retrieve all rentals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All rentals retrieved"),
            @ApiResponse(responseCode = "401", description = "User not authenticated", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RentalListResponseDto getAllRentals() {
        log.info("[Rental Controller] Get all rentals");

        return rentalService.getAllRentals();
    }

    @Operation(summary = "Get rental by id", description = "Retrieve a rental by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "User not authenticated", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalResponseDto getRentalById(@PathVariable Long id) {
        log.info("[Rental Controller] Get rental by id {}", id);

        return rentalService.getRentalResponseDtoById(id);
    }

    @Operation(summary = "Create rental", description = "Create a rental from a form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rental successfully created"),
            @ApiResponse(responseCode = "401", description = "User not authenticated", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Error while uploading the image file", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto createRental(@ModelAttribute RentalRequestDto rentalRequestDto, @AuthenticationPrincipal Jwt jwt) {
        log.info("[Rental Controller] Create rental");

        rentalService.createRental(rentalRequestDto, jwt.getSubject());

        return new ResponseDto("Rental created !");
    }

    @Operation(summary = "Update rental", description = "Update a rental by its id and from a form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental successfully updated"),
            @ApiResponse(responseCode = "401", description = "User not authenticated", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "The authenticated user is not the owner of the rental", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "Rental or user not found", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto updateRental(@PathVariable Long id, @ModelAttribute RentalRequestDto rentalRequestDto, @AuthenticationPrincipal Jwt jwt) {
        log.info("[Rental Controller] Update rental with id {}", id);

        rentalService.updateRental(id, rentalRequestDto, jwt.getSubject());

        return new ResponseDto("Rental updated !");
    }
}
