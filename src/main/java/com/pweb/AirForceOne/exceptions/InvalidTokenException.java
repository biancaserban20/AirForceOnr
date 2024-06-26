package com.pweb.AirForceOne.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Bearer token not valid");
    }
}
