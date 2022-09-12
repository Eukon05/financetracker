package com.eukon05.financetracker.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailTakenException extends ResponseStatusException {

    private static final String MESSAGE_EMAIL_TAKEN = "Email '%s' is already taken";

    public EmailTakenException(String email) {
        super(HttpStatus.CONFLICT, String.format(MESSAGE_EMAIL_TAKEN, email));
    }

}
