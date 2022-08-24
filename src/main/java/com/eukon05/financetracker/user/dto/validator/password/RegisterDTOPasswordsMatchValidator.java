package com.eukon05.financetracker.user.dto.validator.password;

import com.eukon05.financetracker.user.dto.RegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegisterDTOPasswordsMatchValidator implements ConstraintValidator<RegisterDTOPasswordsMatch, RegisterDTO> {
    @Override
    public boolean isValid(RegisterDTO registerDTO, ConstraintValidatorContext constraintValidatorContext) {
        return registerDTO.password().equals(registerDTO.repeatPassword());
    }
}
