package com.openclassrooms.ocprojet3.mapper;

import com.openclassrooms.ocprojet3.dto.RentalListResponseDto;
import com.openclassrooms.ocprojet3.dto.RentalRequestDto;
import com.openclassrooms.ocprojet3.dto.RentalResponseDto;
import com.openclassrooms.ocprojet3.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "messages", ignore = true)
    Rental toRental(RentalRequestDto rentalRequestDto);

    @Mapping(target = "ownerId", source = "owner.id")
    RentalResponseDto toRentalResponseDto(Rental rental);

    default RentalListResponseDto toRentalListResponseDto(List<Rental> rentals) {
        RentalListResponseDto rentalListResponseDto = new RentalListResponseDto();
        rentalListResponseDto.setRentals(rentals.stream()
                .map(this::toRentalResponseDto)
                .collect(Collectors.toList()));

        return rentalListResponseDto;
    }
}
