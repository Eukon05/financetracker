package com.eukon05.financetracker.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransactionNotFoundException extends ResponseStatusException {

    private static final String MESSAGE_TRANSACTION_NOT_FOUND = "Transaction with id '%d' does not exist or does not belong to your account";

    public TransactionNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND, String.format(MESSAGE_TRANSACTION_NOT_FOUND, id));
    }
}
