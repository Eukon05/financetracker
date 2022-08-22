package com.eukon05.financetracker.dto.validator.username;

import com.eukon05.financetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UsernameNotTakenValidator implements ConstraintValidator<UsernameNotTaken, String> {

    private final UserRepository repository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !repository.existsByUsername(username);
    }
}
