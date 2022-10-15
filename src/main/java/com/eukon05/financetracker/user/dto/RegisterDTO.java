package com.eukon05.financetracker.user.dto;

import com.eukon05.financetracker.user.dto.validator.PasswordsMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@PasswordsMatch
public record RegisterDTO(@Size(min = 1, max = 50) @NotBlank String username,
                          @Size(min = 8, max = 50) @NotBlank String password,
                          @Size(min = 8, max = 50) @NotBlank String repeatPassword,
                          @Email @NotEmpty String email) {
}
