package com.eukon05.financetracker.transaction.dto;

import com.eukon05.financetracker.transaction.TransactionCategoryType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record CreateTransactionCategoryDTO(@NotBlank @Size(min = 1, max = 100) String name,
                                           @NotNull TransactionCategoryType type) {
}
