package com.eukon05.financetracker.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MissingTokenException extends ResponseStatusException {

    private static final String MESSAGE_MISSING_TOKEN = "Token not present";

    public MissingTokenException() {
        super(HttpStatus.BAD_REQUEST, MESSAGE_MISSING_TOKEN);
    }

}
