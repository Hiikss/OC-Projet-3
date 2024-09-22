package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.dto.AuthResponseDto;
import com.openclassrooms.ocprojet3.dto.CredentialsDto;
import com.openclassrooms.ocprojet3.dto.UserRequestDto;
import com.openclassrooms.ocprojet3.dto.UserResponseDto;
import com.openclassrooms.ocprojet3.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login an user", description = "Authenticate an user and return an access token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication succeed"),
            @ApiResponse(responseCode = "401", description = "Authencation failed")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody CredentialsDto credentialsDto) {
        log.info("[Auth Controller] Attempting to login");

        return ResponseEntity.ok(authService.login(credentialsDto));
    }

    @Operation(summary = "Register an user", description = "Register a new user and return an access token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registration succeed"),
            @ApiResponse(responseCode = "409", description = "The given email is already used")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserRequestDto userRequestDto) {
        log.info("[Auth Controller] Register");

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequestDto));
    }

    @Operation(summary = "Get authenticated user", description = "Retrieve the authenticated user from the jwt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieve"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMe(@AuthenticationPrincipal Jwt jwt) {
        log.info("[Auth Controller] Get authenticated user");

        return ResponseEntity.ok(authService.getUserByEmail(jwt.getSubject()));
    }
}
