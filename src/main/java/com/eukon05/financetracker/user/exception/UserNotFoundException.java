package com.eukon05.financetracker.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    private static final String MESSAGE_USER_WITH_USERNAME_NOT_FOUND = "User %s not found.";

    public UserNotFoundException(String username) {
        super(HttpStatus.NOT_FOUND, String.format(MESSAGE_USER_WITH_USERNAME_NOT_FOUND, username));
    }
}
