package com.openclassrooms.ocprojet3.dto;

import lombok.Data;

@Data
public class UserRequestDto {

    private String email;

    private String password;

    private String name;
}
