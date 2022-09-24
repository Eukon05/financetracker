package com.eukon05.financetracker.transaction.dto;

import com.eukon05.financetracker.transaction.TransactionType;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public record EditTransactionDTO(@Size(max = 100) String name,
                                 @PositiveOrZero BigDecimal value, TransactionType type) {
}
