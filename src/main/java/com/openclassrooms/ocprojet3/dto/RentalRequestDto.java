package com.openclassrooms.ocprojet3.dto;

import lombok.Data;

@Data
public class RentalRequestDto {

    private String name;

    private int surface;

    private int price;

    private String picture;

    private String description;
}
