package com.eukon05.financetracker.openapi.param;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Instant;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Parameter(name = "toDate", schema = @Schema(implementation = Instant.class))
public @interface ToDateParam {
}
