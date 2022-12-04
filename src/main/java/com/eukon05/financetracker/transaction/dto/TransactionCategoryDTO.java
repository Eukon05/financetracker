package com.eukon05.financetracker.transaction.dto;

import com.eukon05.financetracker.transaction.TransactionCategoryType;

public record TransactionCategoryDTO(long id, String name, TransactionCategoryType type) {
}
