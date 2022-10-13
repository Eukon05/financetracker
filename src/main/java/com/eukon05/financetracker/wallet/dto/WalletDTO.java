package com.eukon05.financetracker.wallet.dto;

import java.math.BigDecimal;

public record WalletDTO(long id, String name, BigDecimal balance, String currency) {
}
