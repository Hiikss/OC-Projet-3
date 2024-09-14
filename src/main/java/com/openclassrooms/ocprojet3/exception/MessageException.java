package com.openclassrooms.ocprojet3.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageException extends RuntimeException {

    private final HttpStatus status;

    public MessageException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
