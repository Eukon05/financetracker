package com.eukon05.financetracker.transaction.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionDTO(long id, long walletID, String name, BigDecimal value, Instant createdAt,
                             long categoryID) {
}
