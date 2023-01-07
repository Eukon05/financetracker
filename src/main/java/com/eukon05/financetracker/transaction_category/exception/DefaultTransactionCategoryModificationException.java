package com.eukon05.financetracker.transaction_category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DefaultTransactionCategoryModificationException extends ResponseStatusException {

    private static final String MESSAGE_DEFAULT_CATEGORY_MODIFICATION = "Modification of the default transaction category is not allowed";

    public DefaultTransactionCategoryModificationException() {
        super(HttpStatus.BAD_REQUEST, MESSAGE_DEFAULT_CATEGORY_MODIFICATION);
    }

}
