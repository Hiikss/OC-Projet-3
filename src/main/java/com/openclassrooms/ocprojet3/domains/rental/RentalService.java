package com.openclassrooms.ocprojet3.domains.rental;

public interface RentalService {

    RentalListResponseDto getAllRentals();

    Rental getRentalById(Long id);

    RentalResponseDto getRentalResponseDtoById(Long id);

    void createRental(RentalRequestDto rentalRequestDto, String ownerEmail);

    void updateRental(Long id, RentalRequestDto rentalRequestDto, String ownerEmail);
}
