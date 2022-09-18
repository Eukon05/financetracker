package com.eukon05.financetracker.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Getter
class ApiError {

    private final Instant timestamp = Instant.now();
    private final int status;
    private final String error;
    private final List<String> details;
    private final String message;
    private final String path;

    public ApiError(HttpStatus status, String message, List<String> details, String path) {
        this.status = status.value();
        this.message = message;
        this.error = status.getReasonPhrase();
        this.details = details;
        this.path = path;
    }

}
