package com.eukon05.financetracker.wallet.dto;

import com.eukon05.financetracker.wallet.dto.validator.CurrencyCode;

import javax.validation.constraints.Size;

public record EditWalletDTO(@Size(min = 1, max = 100) String name, @CurrencyCode(optional = true) String currency) {
}
