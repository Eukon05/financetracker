package com.eukon05.financetracker.wallet.dto;

import com.eukon05.financetracker.wallet.dto.validator.CurrencyCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateWalletDTO(@NotBlank @Size(min = 1, max = 100) String name, @CurrencyCode String currency) {
}
