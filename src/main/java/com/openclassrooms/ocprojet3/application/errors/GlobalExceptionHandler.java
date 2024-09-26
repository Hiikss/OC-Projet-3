package com.openclassrooms.ocprojet3.application.errors;

import com.openclassrooms.ocprojet3.domains.auth.AuthenticationException;
import com.openclassrooms.ocprojet3.domains.message.MessageException;
import com.openclassrooms.ocprojet3.domains.rental.RentalException;
import com.openclassrooms.ocprojet3.domains.upload.UploadException;
import com.openclassrooms.ocprojet3.domains.user.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDto> handleUserException(UserException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(RentalException.class)
    public ResponseEntity<ErrorDto> handleRentalException(RentalException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ErrorDto> handleMessageException(MessageException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(UploadException.class)
    public ResponseEntity<ErrorDto> handleUploadException(UploadException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDto handleAuthenticationException(AuthenticationException exception) {
        return new ErrorDto(exception.getMessage());
    }
}
