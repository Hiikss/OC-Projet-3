package com.openclassrooms.ocprojet3.controller;

import com.openclassrooms.ocprojet3.dto.MessageRequestDto;
import com.openclassrooms.ocprojet3.dto.ResponseDto;
import com.openclassrooms.ocprojet3.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "404", description = "User or rental not found")
    })
    @PostMapping
    public ResponseEntity<ResponseDto> createMessage(@RequestBody MessageRequestDto messageRequestDto) {
        log.info("[Message Controller] Create message");

        messageService.createMessage(messageRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Message send with success"));
    }
}
