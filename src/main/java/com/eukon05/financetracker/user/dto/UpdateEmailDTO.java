package com.eukon05.financetracker.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public record UpdateEmailDTO(@Email @NotEmpty String email) {
}
