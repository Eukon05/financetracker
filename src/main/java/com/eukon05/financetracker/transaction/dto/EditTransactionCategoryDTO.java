package com.eukon05.financetracker.transaction.dto;

import jakarta.validation.constraints.Size;

public record EditTransactionCategoryDTO(@Size(min = 1, max = 100) String name) {
}
