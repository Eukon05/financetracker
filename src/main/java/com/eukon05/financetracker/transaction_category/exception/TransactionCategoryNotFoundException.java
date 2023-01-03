package com.eukon05.financetracker.transaction_category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TransactionCategoryNotFoundException extends ResponseStatusException {

    private static final String MESSAGE_CATEGORY_NOT_FOUND = "Category with id %d does not exist";

    public TransactionCategoryNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND, String.format(MESSAGE_CATEGORY_NOT_FOUND, id));
    }

}
