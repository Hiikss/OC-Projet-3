package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.dto.RentalListResponseDto;
import com.openclassrooms.ocprojet3.dto.RentalRequestDto;
import com.openclassrooms.ocprojet3.dto.RentalResponseDto;
import com.openclassrooms.ocprojet3.dto.ResponseDto;
import com.openclassrooms.ocprojet3.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public ResponseEntity<RentalListResponseDto> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDto> getRentalById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createRental(@RequestBody RentalRequestDto rentalRequestDto, @AuthenticationPrincipal Jwt jwt) {
        rentalService.createRental(rentalRequestDto, jwt.getSubject());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Rental created !"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateRental(@PathVariable Long id, @RequestBody RentalRequestDto rentalRequestDto, @AuthenticationPrincipal Jwt jwt) {
        rentalService.updateRental(id, rentalRequestDto, jwt.getSubject());

        return ResponseEntity.ok().body(new ResponseDto("Rental updated !"));
    }
}
