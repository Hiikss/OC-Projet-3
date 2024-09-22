package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.dto.RentalListResponseDto;
import com.openclassrooms.ocprojet3.dto.RentalRequestDto;
import com.openclassrooms.ocprojet3.dto.RentalResponseDto;
import com.openclassrooms.ocprojet3.dto.ResponseDto;
import com.openclassrooms.ocprojet3.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
    })
    @GetMapping
    public ResponseEntity<RentalListResponseDto> getAllRentals() {
        log.info("[Rental Controller] Get all rentals");

        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @Operation(summary = "Get rental by id", description = "Retrieve a rental by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDto> getRentalById(@PathVariable Long id) {
        log.info("[Rental Controller] Get rental by id {}", id);

        return ResponseEntity.ok(rentalService.getRentalResponseDtoById(id));
    }

    @Operation(summary = "Create rental", description = "Create a rental from a form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rental successfully created"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Error while uploading the image file"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> createRental(@ModelAttribute RentalRequestDto rentalRequestDto, @AuthenticationPrincipal Jwt jwt) {
        log.info("[Rental Controller] Create rental");

        rentalService.createRental(rentalRequestDto, jwt.getSubject());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Rental created !"));
    }

    @Operation(summary = "Update rental", description = "Update a rental by its id and from a form")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental successfully updated"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "404", description = "Rental or user not found"),
            @ApiResponse(responseCode = "403", description = "The authenticated user is not the owner of the rental"),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> updateRental(@PathVariable Long id, @ModelAttribute RentalRequestDto rentalRequestDto, @AuthenticationPrincipal Jwt jwt) {
        log.info("[Rental Controller] Update rental with id {}", id);

        rentalService.updateRental(id, rentalRequestDto, jwt.getSubject());

        return ResponseEntity.ok().body(new ResponseDto("Rental updated !"));
    }
}
