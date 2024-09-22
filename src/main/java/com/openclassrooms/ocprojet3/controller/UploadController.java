package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.service.UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploads")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Upload", description = "Upload endpoints")
public class UploadController {

    private final UploadService uploadService;

    @Operation(summary = "Get image by name", description = "Retrieve image by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "404", description = "Image not found")
    })
    @GetMapping(value = "/{name}")
    public ResponseEntity<Resource> getImageByName(@PathVariable String name) {
        log.info("[Upload Controller] Get uploaded image by name {}", name);

        return ResponseEntity.ok().body(uploadService.getUploadByName(name));
    }
}
