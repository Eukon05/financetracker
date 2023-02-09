package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.openapi.response.NotFoundResponse;
import com.eukon05.financetracker.openapi.response.ValidationErrorResponse;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "Transaction", description = "Handles operations related to transactions")
class TransactionController {

    private final TransactionFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new transaction")
    @ValidationErrorResponse
    @NotFoundResponse
    void createTransaction(@RequestBody @Valid CreateTransactionDTO dto, @AuthenticationPrincipal Jwt jwt) {
        facade.createTransaction(jwt.getSubject(), dto);
    }

    @DeleteMapping("/{transactionID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete transaction with a given ID")
    @NotFoundResponse
    void deleteTransaction(@PathVariable long transactionID, @AuthenticationPrincipal Jwt jwt) {
        facade.deleteTransaction(jwt.getSubject(), transactionID);
    }

    @GetMapping(value = "/{transactionID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about a transaction with a given ID")
    @NotFoundResponse
    TransactionDTO getTransaction(@PathVariable long transactionID, @AuthenticationPrincipal Jwt jwt) {
        return facade.getTransactionDTO(jwt.getSubject(), transactionID);
    }

    @PatchMapping("/{transactionID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit transaction with a given ID")
    @ValidationErrorResponse
    @NotFoundResponse
    void editTransaction(@PathVariable long transactionID, @AuthenticationPrincipal Jwt jwt, @RequestBody @Valid EditTransactionDTO dto) {
        facade.editTransaction(jwt.getSubject(), transactionID, dto);
    }

}
