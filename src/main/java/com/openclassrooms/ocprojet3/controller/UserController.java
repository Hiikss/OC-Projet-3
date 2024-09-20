package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.dto.UserResponseDto;
import com.openclassrooms.ocprojet3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        log.info("[User Controller] Get user by id: {}", id);

        return ResponseEntity.ok(userService.getUserResponseDtoById(id));
    }
}
