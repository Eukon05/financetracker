package com.eukon05.financetracker.token;

import com.eukon05.financetracker.token.usecase.TokenFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SecurityRequirements
@Tag(name = "Token", description = "Handles confirming account changes")
class TokenController {

    private final TokenFacade tokenFacade;

    @GetMapping("/confirm-registration")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Confirm creating an account and enable it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", ref = "notfound"),
            @ApiResponse(responseCode = "400", ref = "validation")
    })
    void confirmRegistration(@RequestParam String token) {
        tokenFacade.confirmRegistration(token);
    }

    @GetMapping("/confirm-email-change")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Confirm a request to change your email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", ref = "notfound"),
            @ApiResponse(responseCode = "400", ref = "validation")
    })
    void confirmEmailChange(@RequestParam String token) {
        tokenFacade.confirmEmailChange(token);
    }

    @GetMapping("/confirm-password-change")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Confirm a request to change your password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", ref = "notfound"),
            @ApiResponse(responseCode = "400", ref = "validation")
    })
    void confirmPasswordChange(@RequestParam String token) {
        tokenFacade.confirmPasswordChange(token);
    }

}
