package com.eukon05.financetracker.dto.validator.password;

import com.eukon05.financetracker.dto.RegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegisterRequestDTOPasswordsMatchValidator implements ConstraintValidator<RegisterRequestDTOPasswordsMatch, RegisterDTO> {
    @Override
    public boolean isValid(RegisterDTO registerDTO, ConstraintValidatorContext constraintValidatorContext) {
        return registerDTO.password().equals(registerDTO.repeatPassword());
    }
}
