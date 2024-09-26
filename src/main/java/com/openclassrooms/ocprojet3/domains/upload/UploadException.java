package com.openclassrooms.ocprojet3.domains.upload;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UploadException extends RuntimeException {

    private final HttpStatus status;

    public UploadException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
