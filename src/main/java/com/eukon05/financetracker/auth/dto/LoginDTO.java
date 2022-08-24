package com.eukon05.financetracker.auth.dto;

import javax.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank String username, @NotBlank String password) {
}
