package com.eukon05.financetracker.transaction.dto;

import com.eukon05.financetracker.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionDTO(long id, long walletID, String name, TransactionType type, BigDecimal value,
                             Instant createdAt, String categoryName) {
}
