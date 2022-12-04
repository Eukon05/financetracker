package com.eukon05.financetracker.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String VALIDATION_ERROR_ENTRY = "%s: %s";
    private static final String MESSAGE_VALIDATION_ERROR = "Validation error";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(String.format(VALIDATION_ERROR_ENTRY, error.getField(), error.getDefaultMessage()));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(String.format(VALIDATION_ERROR_ENTRY, error.getObjectName(), error.getDefaultMessage()));
        }

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(HttpStatus.BAD_REQUEST, MESSAGE_VALIDATION_ERROR, errors, ((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, apiErrorDTO, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(HttpStatus.resolve(ex.getStatusCode().value()), ex.getReason(), Collections.emptyList(), request.getRequestURI());
        return new ResponseEntity<>(apiErrorDTO, ex.getStatusCode());
    }

}
