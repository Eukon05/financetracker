package com.eukon05.financetracker.user.dto.validator.username;

import com.eukon05.financetracker.user.UserRepository;
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
