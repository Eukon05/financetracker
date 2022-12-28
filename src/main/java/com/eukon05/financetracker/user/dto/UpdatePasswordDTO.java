package com.eukon05.financetracker.user.dto;

import com.eukon05.financetracker.user.dto.validator.PasswordsMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordsMatch
public record UpdatePasswordDTO(@Size(min = 8, max = 50) @NotBlank String password,
                                @Size(min = 8, max = 50) @NotBlank String repeatPassword) {
}
