package com.openclassrooms.ocprojet3.domains.message;

import com.openclassrooms.ocprojet3.application.common.ResponseDto;
import com.openclassrooms.ocprojet3.application.errors.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Message", description = "Message endpoints")
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "Create a message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Message successfully created"),
            @ApiResponse(responseCode = "401", description = "User not authenticated", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "User or rental not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto createMessage(@RequestBody MessageRequestDto messageRequestDto) {
        log.info("[Message Controller] Create message");

        messageService.createMessage(messageRequestDto);
        return new ResponseDto("Message send with success");
    }
}
