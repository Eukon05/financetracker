package com.eukon05.financetracker.transaction.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateTransactionDTO(@Positive long walletID, @NotBlank @Size(min = 1, max = 100) String name,
                                   @NotNull BigDecimal value, @PositiveOrZero long categoryID) {
}
