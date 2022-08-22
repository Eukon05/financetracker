package com.eukon05.financetracker.dto;

import com.eukon05.financetracker.dto.validator.email.EmailNotTaken;
import com.eukon05.financetracker.dto.validator.password.RegisterRequestDTOPasswordsMatch;
import com.eukon05.financetracker.dto.validator.username.UsernameNotTaken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@RegisterRequestDTOPasswordsMatch
public record RegisterDTO(@Size(min = 1, max = 50) @NotBlank @UsernameNotTaken String username,
                          @Size(min = 8, max = 50) @NotBlank String password,
                          @Size(min = 8, max = 50) @NotBlank String repeatPassword,
                          @Size(min = 1, max = 100) @NotBlank String name,
                          @Size(min = 1, max = 200) @NotBlank String surname,
                          @Email @NotEmpty @EmailNotTaken String email) {
}
