package com.eukon05.financetracker.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record ForgotDTO(@Email @NotEmpty String email) {
}
