package com.eukon05.financetracker.transaction.dto;

import com.eukon05.financetracker.transaction.TransactionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public record CreateTransactionDTO(@Positive long walletID, @NotBlank @Size(min = 1, max = 100) String name,
                                   @Positive BigDecimal value, @NotNull TransactionType type,
                                   @Positive long categoryID) {
}
