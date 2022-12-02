package com.eukon05.financetracker.transaction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransactionTypeMismatchException extends ResponseStatusException {

    private static final String MESSAGE_TYPE_MISMATCH = "Provided transaction category does not match the transaction's type";

    public TransactionTypeMismatchException() {
        super(HttpStatus.CONFLICT, MESSAGE_TYPE_MISMATCH);
    }

}
