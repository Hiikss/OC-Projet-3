package com.openclassrooms.ocprojet3.domains.upload;

import com.openclassrooms.ocprojet3.application.errors.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uploads")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Upload", description = "Upload endpoints")
public class UploadController {

    private final UploadService uploadService;

    @Operation(summary = "Get image by name", description = "Retrieve image by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image successfully retrieved", content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE, schema = @Schema(type = "string", format = "binary"))),
            @ApiResponse(responseCode = "401", description = "User not authenticated", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping(value = "/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Resource getImageByName(@PathVariable String name) {
        log.info("[Upload Controller] Get uploaded image by name {}", name);

        return uploadService.getUploadByName(name);
    }
}
