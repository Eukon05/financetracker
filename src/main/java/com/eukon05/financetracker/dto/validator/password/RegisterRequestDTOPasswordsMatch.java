package com.eukon05.financetracker.dto.validator.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegisterRequestDTOPasswordsMatchValidator.class)
public @interface RegisterRequestDTOPasswordsMatch {

    String message() default "{password.mismatch}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
