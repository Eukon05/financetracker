package com.eukon05.financetracker.transaction.dto;

import com.eukon05.financetracker.transaction.TransactionType;

public record TransactionCategoryDTO(long id, String name, TransactionType type) {
}
