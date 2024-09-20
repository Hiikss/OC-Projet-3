package com.openclassrooms.ocprojet3.service;

import com.openclassrooms.ocprojet3.dto.RentalListResponseDto;
import com.openclassrooms.ocprojet3.dto.RentalRequestDto;
import com.openclassrooms.ocprojet3.dto.RentalResponseDto;
import com.openclassrooms.ocprojet3.exception.RentalException;
import com.openclassrooms.ocprojet3.mapper.RentalMapper;
import com.openclassrooms.ocprojet3.model.Rental;
import com.openclassrooms.ocprojet3.model.User;
import com.openclassrooms.ocprojet3.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    private final RentalMapper rentalMapper;

    private final UserService userService;

    private final UploadService uploadService;

    @Override
    public RentalListResponseDto getAllRentals() {
        log.info("[Rental Service] Get all rentals");

        return rentalMapper.toRentalListResponseDto(rentalRepository.findAll());
    }

    @Override
    public Rental getRentalById(Long id) {
        log.info("[Rental Service] Get rental by id {}", id);

        return rentalRepository.findById(id)
                .orElseThrow(() -> new RentalException(HttpStatus.NOT_FOUND, "Rental not found"));
    }

    @Override
    public RentalResponseDto getRentalResponseDtoById(Long id) {
        return rentalMapper.toRentalResponseDto(getRentalById(id));
    }

    @Override
    public void createRental(RentalRequestDto rentalRequestDto, String ownerEmail) {
        log.info("[Rental Service] Create rental by user {}", ownerEmail);

        User user = userService.getUserByEmail(ownerEmail);

        String fileUrl = uploadService.uploadFile(rentalRequestDto.getPicture());

        Rental rental = rentalMapper.toRental(rentalRequestDto);
        rental.setOwner(user);
        rental.setPicture(fileUrl);

        rentalRepository.save(rental);
    }

    @Override
    public void updateRental(Long id, RentalRequestDto rentalRequestDto, String ownerEmail) {
        log.info("[Rental Service] Update rental {} by user {}", id, ownerEmail);

        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalException(HttpStatus.NOT_FOUND, "Rental not found"));

        User user = userService.getUserByEmail(ownerEmail);

        if (!Objects.equals(user.getId(), rental.getOwner().getId())) {
            throw new RentalException(HttpStatus.FORBIDDEN, "Can't update a rental that you don't own");
        }

        Rental updatedRental = rentalMapper.toRental(rentalRequestDto);
        updatedRental.setId(id);
        updatedRental.setOwner(user);
        updatedRental.setPicture(rental.getPicture());
        updatedRental.setCreatedAt(rental.getCreatedAt());

        rentalRepository.save(updatedRental);
    }

}
