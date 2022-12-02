package com.eukon05.financetracker.transaction.dto;

import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record EditTransactionDTO(@Size(max = 100) String name, BigDecimal value, long categoryID) {
}
