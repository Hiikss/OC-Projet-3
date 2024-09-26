package com.openclassrooms.ocprojet3.domains.auth;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}
