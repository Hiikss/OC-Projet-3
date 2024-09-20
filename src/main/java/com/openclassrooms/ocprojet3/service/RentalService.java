package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.RentalListResponseDto;
import com.openclassrooms.ocprojet3.dto.RentalRequestDto;
import com.openclassrooms.ocprojet3.dto.RentalResponseDto;
import com.openclassrooms.ocprojet3.model.Rental;

public interface RentalService {

    RentalListResponseDto getAllRentals();

    Rental getRentalById(Long id);

    RentalResponseDto getRentalResponseDtoById(Long id);

    void createRental(RentalRequestDto rentalRequestDto, String ownerEmail);

    void updateRental(Long id, RentalRequestDto rentalRequestDto, String ownerEmail);
}
