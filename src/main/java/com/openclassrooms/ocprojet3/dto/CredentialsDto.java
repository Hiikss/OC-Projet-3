package com.openclassrooms.ocprojet3.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User login request data")
public record CredentialsDto(String email, String password) {
}
