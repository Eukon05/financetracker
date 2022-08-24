package com.eukon05.financetracker.token.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TokenExpiredException extends RuntimeException {
    private static final String MESSAGE_TOKEN_EXPIRED = "The token has expired";

    public TokenExpiredException() {
        super(MESSAGE_TOKEN_EXPIRED);
    }
}
