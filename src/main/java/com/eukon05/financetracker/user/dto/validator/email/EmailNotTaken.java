package com.eukon05.financetracker.user.dto.validator.email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailNotTakenValidator.class)
public @interface EmailNotTaken {

    String message() default "{email.taken}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
