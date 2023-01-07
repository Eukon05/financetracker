package com.eukon05.financetracker.transaction_category.dto;

import com.eukon05.financetracker.transaction_category.TransactionCategoryType;

public record TransactionCategoryDTO(long id, String name, TransactionCategoryType type) {
}
