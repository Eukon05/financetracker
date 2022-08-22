package com.eukon05.financetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TokenNotFoundException extends RuntimeException {
    private static final String MESSAGE_TOKEN_NOT_FOUND = "Token not found.";

    public TokenNotFoundException() {
        super(MESSAGE_TOKEN_NOT_FOUND);
    }
}
