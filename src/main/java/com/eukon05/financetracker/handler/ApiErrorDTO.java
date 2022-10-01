package com.eukon05.financetracker.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Getter
public class ApiErrorDTO {

    private final Instant timestamp = Instant.now();
    private final int status;
    private final String error;
    private final String message;
    private final List<String> details;
    private final String path;

    public ApiErrorDTO(HttpStatus status, String message, List<String> details, String path) {
        this.status = status.value();
        this.message = message;
        this.error = status.getReasonPhrase();
        this.details = details;
        this.path = path;
    }

}
