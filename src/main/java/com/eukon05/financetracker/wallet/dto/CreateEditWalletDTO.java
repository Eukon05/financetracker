package com.eukon05.financetracker.wallet.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CreateEditWalletDTO(@NotBlank @Size(min = 1, max = 100) String name) {
}
