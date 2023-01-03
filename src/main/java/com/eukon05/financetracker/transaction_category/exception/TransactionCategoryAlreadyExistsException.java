package com.eukon05.financetracker.transaction_category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransactionCategoryAlreadyExistsException extends ResponseStatusException {

    private static final String MESSAGE_CATEGORY_ALREADY_EXISTS = "Category %s already exists";

    public TransactionCategoryAlreadyExistsException(String name) {
        super(HttpStatus.CONFLICT, String.format(MESSAGE_CATEGORY_ALREADY_EXISTS, name));
    }

}
