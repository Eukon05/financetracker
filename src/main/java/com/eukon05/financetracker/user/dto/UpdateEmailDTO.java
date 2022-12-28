package com.eukon05.financetracker.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UpdateEmailDTO(@Email @NotEmpty String email) {
}
