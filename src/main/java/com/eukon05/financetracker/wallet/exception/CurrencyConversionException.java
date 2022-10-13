package com.eukon05.financetracker.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CurrencyConversionException extends ResponseStatusException {

    private static final String MESSAGE_CONVERSION_ERROR = "Currency conversion failed";

    public CurrencyConversionException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, MESSAGE_CONVERSION_ERROR);
    }

}
