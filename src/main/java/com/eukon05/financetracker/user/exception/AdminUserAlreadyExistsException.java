package com.eukon05.financetracker.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AdminUserAlreadyExistsException extends ResponseStatusException {

    private static final String MESSAGE_ADMIN_ALREADY_EXISTS = "The admin user has already been created";

    public AdminUserAlreadyExistsException() {
        super(HttpStatus.CONFLICT, MESSAGE_ADMIN_ALREADY_EXISTS);
    }

}
