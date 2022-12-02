package com.eukon05.financetracker.transaction.dto;

import com.eukon05.financetracker.transaction.TransactionCategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTransactionCategoryDTO(@NotBlank @Size(min = 1, max = 100) String name,
                                           @NotNull TransactionCategoryType type) {
}
