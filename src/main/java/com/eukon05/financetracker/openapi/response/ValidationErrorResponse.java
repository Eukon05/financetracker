package com.eukon05.financetracker.openapi.response;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ProblemDetail;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ApiResponse(responseCode = "400", description = "Validation failed for given input data", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
public @interface ValidationErrorResponse {
}
