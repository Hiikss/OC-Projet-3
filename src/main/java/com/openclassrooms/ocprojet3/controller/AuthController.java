package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.dto.AuthResponseDto;
import com.openclassrooms.ocprojet3.dto.CredentialsDto;
import com.openclassrooms.ocprojet3.service.AuthService;
import com.openclassrooms.ocprojet3.service.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody CredentialsDto credentialsDto, Authentication authentication) {
        log.info("[Auth Controller] Attempting to login");

        authService.login(credentialsDto);
        return ResponseEntity.ok(new AuthResponseDto(jwtService.generateToken(authentication)));
    }
}
