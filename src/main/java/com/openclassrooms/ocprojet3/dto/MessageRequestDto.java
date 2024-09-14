package com.openclassrooms.ocprojet3.dto;

import lombok.Data;

@Data
public class MessageRequestDto {

    private String message;

    private Long userId;

    private Long rentalId;
}
