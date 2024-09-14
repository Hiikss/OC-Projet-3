package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.RentalListResponseDto;
import com.openclassrooms.ocprojet3.dto.RentalRequestDto;
import com.openclassrooms.ocprojet3.dto.RentalResponseDto;

public interface RentalService {

    RentalListResponseDto getAllRentals();

    RentalResponseDto getRentalById(Long id);

    void createRental(RentalRequestDto rentalRequestDto, String ownerEmail);

    void updateRental(Long id, RentalRequestDto rentalRequestDto, String ownerEmail);
}
