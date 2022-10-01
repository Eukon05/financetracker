package com.eukon05.financetracker.auth;

import com.eukon05.financetracker.auth.dto.ForgotDTO;
import com.eukon05.financetracker.auth.dto.LoginDTO;
import com.eukon05.financetracker.auth.usecase.AuthFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Handles issuing JWTs and resetting user's password")
class AuthController {

    private final AuthFacade authFacade;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Generate an access token")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "400", ref = "validation")
    })
    Map<String, String> login(@RequestBody @Valid LoginDTO dto, HttpServletRequest request) {
        return authFacade.login(dto, request.getRequestURL().toString());
    }

    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request a refreshed access token")
    @SecurityRequirement(name = "Refresh Token Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "400", ref = "validation")
    })
    Map<String, String> refresh(HttpServletRequest request) {
        return authFacade.refresh(request.getHeader(AUTHORIZATION), request.getRequestURL().toString());
    }

    @PostMapping("/forgot")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Submit a request to reset your password")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "400", ref = "validation"),
            @ApiResponse(responseCode = "404", ref = "notfound")
    })
    void forgotPassword(@Valid @RequestBody ForgotDTO dto, HttpServletRequest request) {
        authFacade.forgot(dto.email(), request.getRequestURL().toString().replace("/forgot", ""));
    }


}
