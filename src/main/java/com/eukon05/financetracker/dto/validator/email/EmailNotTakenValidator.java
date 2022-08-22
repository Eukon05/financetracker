package com.eukon05.financetracker.dto.validator.email;

import com.eukon05.financetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailNotTakenValidator implements ConstraintValidator<EmailNotTaken, String> {

    private final UserRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !repository.existsByEmail(email);
    }
}
