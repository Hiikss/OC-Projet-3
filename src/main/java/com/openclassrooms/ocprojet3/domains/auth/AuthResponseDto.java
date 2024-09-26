package com.openclassrooms.ocprojet3.domains.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response data")
public record AuthResponseDto(

        @Schema(description = "The access token for authentication")
        String token) {
}
