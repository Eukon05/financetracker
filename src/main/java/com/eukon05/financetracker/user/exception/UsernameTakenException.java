package com.eukon05.financetracker.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsernameTakenException extends ResponseStatusException {

    private static final String MESSAGE_USERNAME_TAKEN = "Username '%s' is already taken";

    public UsernameTakenException(String username) {
        super(HttpStatus.CONFLICT, String.format(MESSAGE_USERNAME_TAKEN, username));
    }

}
