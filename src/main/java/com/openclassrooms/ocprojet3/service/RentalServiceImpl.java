package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.RentalListResponseDto;
import com.openclassrooms.ocprojet3.dto.RentalRequestDto;
import com.openclassrooms.ocprojet3.dto.RentalResponseDto;
import com.openclassrooms.ocprojet3.exception.RentalException;
import com.openclassrooms.ocprojet3.mapper.RentalMapper;
import com.openclassrooms.ocprojet3.model.Rental;
import com.openclassrooms.ocprojet3.model.User;
import com.openclassrooms.ocprojet3.repository.RentalRepository;
import com.openclassrooms.ocprojet3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    private final RentalMapper rentalMapper;

    private final UserRepository userRepository;

    @Override
    public RentalListResponseDto getAllRentals() {
        return rentalMapper.toRentalListResponseDto(rentalRepository.findAll());
    }

    @Override
    public RentalResponseDto getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalException(HttpStatus.NOT_FOUND, "Rental not found"));

        return rentalMapper.toRentalResponseDto(rental);
    }

    @Override
    public void createRental(RentalRequestDto rentalRequestDto, String ownerEmail) {
        User user = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new RentalException(HttpStatus.NOT_FOUND, "User not found"));

        Rental rental = rentalMapper.toRental(rentalRequestDto);
        rental.setOwner(user);
        rentalRepository.save(rental);
    }

    @Override
    public void updateRental(Long id, RentalRequestDto rentalRequestDto, String ownerEmail) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalException(HttpStatus.NOT_FOUND, "Rental not found"));

        User user = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new RentalException(HttpStatus.NOT_FOUND, "User not found"));

        if (!Objects.equals(user.getId(), rental.getOwner().getId())) {
            throw new RentalException(HttpStatus.BAD_REQUEST, "Can't update a rental that you don't own");
        }

        Rental updatedRental = rentalMapper.toRental(rentalRequestDto);
        updatedRental.setId(id);

        rentalRepository.save(updatedRental);
    }

}
