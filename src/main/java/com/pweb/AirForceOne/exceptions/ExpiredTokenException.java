package com.pweb.AirForceOne.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException() {
        super("Bearer token expired");
    }
}
