package com.openclassrooms.ocprojet3.configuration;

import com.openclassrooms.ocprojet3.dto.ErrorDto;
import com.openclassrooms.ocprojet3.exception.MessageException;
import com.openclassrooms.ocprojet3.exception.RentalException;
import com.openclassrooms.ocprojet3.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleUserException(UserException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(ErrorDto.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler(RentalException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleRentalException(RentalException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(ErrorDto.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler(MessageException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleMessageException(MessageException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(ErrorDto.builder().message(exception.getMessage()).build());
    }
}
