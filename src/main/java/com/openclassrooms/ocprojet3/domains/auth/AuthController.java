package com.openclassrooms.ocprojet3.domains.auth;

import com.openclassrooms.ocprojet3.application.errors.ErrorDto;
import com.openclassrooms.ocprojet3.domains.user.UserRequestDto;
import com.openclassrooms.ocprojet3.domains.user.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
            @ApiResponse(responseCode = "401", description = "Authencation failed", content = @Content(contentSchema = @Schema(implementation = ErrorDto.class))),
    })
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponseDto login(@RequestBody CredentialsDto credentialsDto) {
        log.info("[Auth Controller] Attempting to login");

        return authService.login(credentialsDto);
    }

    @Operation(summary = "Register an user", description = "Register a new user and return an access token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registration succeed"),
            @ApiResponse(responseCode = "409", description = "The given email is already used", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDto register(@RequestBody UserRequestDto userRequestDto) {
        log.info("[Auth Controller] Register");

        return authService.register(userRequestDto);
    }

    @Operation(summary = "Get authenticated user", description = "Retrieve the authenticated user from the jwt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieve"),
            @ApiResponse(responseCode = "401", description = "User not authenticated", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
                @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getMe(@AuthenticationPrincipal Jwt jwt) {
        log.info("[Auth Controller] Get authenticated user");

        return authService.getUserByEmail(jwt.getSubject());
    }
}
