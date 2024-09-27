package com.openclassrooms.ocprojet3.domains.rental;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "picture", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "messages", ignore = true)
    Rental toRental(RentalRequestDto rentalRequestDto);

    @Mapping(target = "ownerId", source = "owner.id")
    RentalResponseDto toRentalResponseDto(Rental rental);

    default RentalListResponseDto toRentalListResponseDto(List<Rental> rentals) {
        return new RentalListResponseDto((rentals.stream().map(this::toRentalResponseDto)).toList());
    }
}
