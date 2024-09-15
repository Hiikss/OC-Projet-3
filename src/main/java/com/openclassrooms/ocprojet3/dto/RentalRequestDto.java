package com.openclassrooms.ocprojet3.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RentalRequestDto {

    private String name;

    private int surface;

    private int price;

    private MultipartFile picture;

    private String description;
}
