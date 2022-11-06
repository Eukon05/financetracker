package com.eukon05.financetracker.transaction.dto;

import com.eukon05.financetracker.transaction.TransactionType;

import javax.validation.constraints.Size;

public record EditTransactionCategoryDTO(@Size(min = 1, max = 100) String name, TransactionType type) {
}
