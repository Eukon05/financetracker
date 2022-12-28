package com.eukon05.financetracker.token.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TokenExpiredException extends ResponseStatusException {
    private static final String MESSAGE_TOKEN_EXPIRED = "The token has expired";

    public TokenExpiredException() {
        super(HttpStatus.BAD_REQUEST, MESSAGE_TOKEN_EXPIRED);
    }
}
