package com.eukon05.financetracker.token.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TokenNotFoundException extends ResponseStatusException {
    private static final String MESSAGE_TOKEN_NOT_FOUND = "Token not found.";

    public TokenNotFoundException() {
        super(HttpStatus.NOT_FOUND, MESSAGE_TOKEN_NOT_FOUND);
    }
}
