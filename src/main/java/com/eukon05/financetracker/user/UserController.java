package com.eukon05.financetracker.user;

import com.eukon05.financetracker.user.dto.RegisterDTO;
import com.eukon05.financetracker.user.dto.UpdateEmailDTO;
import com.eukon05.financetracker.user.dto.UpdatePasswordDTO;
import com.eukon05.financetracker.user.dto.UserDTO;
import com.eukon05.financetracker.user.usecase.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Handles creating and managing an account")
class UserController {

    private final UserFacade userFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register an account")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", ref = "conflict"),
            @ApiResponse(responseCode = "400", ref = "validation")
    })
    void createUser(@RequestBody @Valid RegisterDTO dto, HttpServletRequest request) {
        userFacade.createUser(dto, request.getRequestURL().toString().replace("/users", ""));
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about your account")
    @ApiResponse(responseCode = "401", ref = "unauthorized")
    UserDTO getUser(Principal principal) {
        return userFacade.getUserDTO(principal.getName());
    }

    @PutMapping("/me/email")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update your email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "400", ref = "validation")
    })
    void updateEmail(Principal principal, @Valid @RequestBody UpdateEmailDTO dto, HttpServletRequest request) {
        userFacade.updateUserEmail(principal.getName(), dto.email(), request.getRequestURL().toString().replace("/users/me/email", ""));
    }

    @PutMapping("/me/password")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update your password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "400", ref = "validation")
    })
    void updatePassword(Principal principal, @Valid @RequestBody UpdatePasswordDTO dto, HttpServletRequest request) {
        userFacade.updateUserPassword(principal.getName(), dto.password(), request.getRequestURL().toString().replace("/users/me/password", ""));
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register an administrator account")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", ref = "conflict"),
            @ApiResponse(responseCode = "400", ref = "validation"),
            @ApiResponse(responseCode = "401", ref = "unauthorized")
    })
    void createAdmin(@RequestParam(name = "masterPassword") String masterPassword, RegisterDTO dto, HttpServletRequest request) {
        userFacade.createAdmin(masterPassword, dto, request.getRequestURL().toString().replace("/users/admin", ""));
    }

}
