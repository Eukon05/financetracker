package com.eukon05.financetracker.user.dto;

import com.eukon05.financetracker.user.dto.validator.email.EmailNotTaken;
import com.eukon05.financetracker.user.dto.validator.password.RegisterDTOPasswordsMatch;
import com.eukon05.financetracker.user.dto.validator.username.UsernameNotTaken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@RegisterDTOPasswordsMatch
public record RegisterDTO(@Size(min = 1, max = 50) @NotBlank @UsernameNotTaken String username,
                          @Size(min = 8, max = 50) @NotBlank String password,
                          @Size(min = 8, max = 50) @NotBlank String repeatPassword,
                          @Email @NotEmpty @EmailNotTaken String email) {
}
