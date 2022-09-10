package com.eukon05.financetracker.token.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidTokenException extends ResponseStatusException {

    private static final String MESSAGE_INVALID_TOKEN = "Invalid token";

    public InvalidTokenException() {
        super(HttpStatus.BAD_REQUEST, MESSAGE_INVALID_TOKEN);
    }

}
