package com.eukon05.financetracker.user.dto.validator.password;

import com.eukon05.financetracker.user.dto.RegisterDTO;
import com.eukon05.financetracker.user.dto.UpdatePasswordDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {
    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext constraintValidatorContext) {
        if (dto instanceof RegisterDTO registerDTO) {
            return registerDTO.password().equals(registerDTO.repeatPassword());
        } else if (dto instanceof UpdatePasswordDTO updatePasswordDTO) {
            return updatePasswordDTO.password().equals(updatePasswordDTO.repeatPassword());
        } else {
            return false;
        }
    }
}
