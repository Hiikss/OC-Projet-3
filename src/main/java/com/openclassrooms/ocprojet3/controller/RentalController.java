package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.dto.RentalListResponseDto;
import com.openclassrooms.ocprojet3.dto.RentalRequestDto;
import com.openclassrooms.ocprojet3.dto.RentalResponseDto;
import com.openclassrooms.ocprojet3.dto.ResponseDto;
import com.openclassrooms.ocprojet3.service.RentalService;
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
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public ResponseEntity<RentalListResponseDto> getAllRentals() {
        log.info("[Rental Controller] Get all rentals");

        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDto> getRentalById(@PathVariable Long id) {
        log.info("[Rental Controller] Get rental by id {}", id);

        return ResponseEntity.ok(rentalService.getRentalResponseDtoById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> createRental(@ModelAttribute RentalRequestDto rentalRequestDto, @AuthenticationPrincipal Jwt jwt) {
        log.info("[Rental Controller] Create rental");

        rentalService.createRental(rentalRequestDto, jwt.getSubject());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Rental created !"));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> updateRental(@PathVariable Long id, @ModelAttribute RentalRequestDto rentalRequestDto, @AuthenticationPrincipal Jwt jwt) {
        log.info("[Rental Controller] Update rental with id {}", id);

        rentalService.updateRental(id, rentalRequestDto, jwt.getSubject());

        return ResponseEntity.ok().body(new ResponseDto("Rental updated !"));
    }
}
