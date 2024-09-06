package com.openclassrooms.ocprojet3.service;

import org.springframework.security.core.Authentication;

public interface JWTService {

    String generateToken(Authentication authentication);
}
