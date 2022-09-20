package com.eukon05.financetracker.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public record ForgotDTO(@Email @NotEmpty String email) {
}
