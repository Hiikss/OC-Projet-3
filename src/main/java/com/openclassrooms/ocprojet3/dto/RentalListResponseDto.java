package com.openclassrooms.ocprojet3.dto;

import lombok.Data;

import java.util.List;

@Data
public class RentalListResponseDto {

    private List<RentalResponseDto> rentals;
}
