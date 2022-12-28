package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.service.transaction.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    private final TransactionService facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", ref = "validation"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "404", ref = "notfound")
    })
    void createTransaction(@RequestBody @Valid CreateTransactionDTO dto, @AuthenticationPrincipal Jwt jwt) {
        facade.createTransaction(jwt.getSubject(), dto);
    }

    @DeleteMapping("/{transactionID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete transaction with a given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "404", ref = "notfound")
    })
    void deleteTransaction(@PathVariable long transactionID, @AuthenticationPrincipal Jwt jwt) {
        facade.deleteTransaction(jwt.getSubject(), transactionID);
    }

    @GetMapping(value = "/{transactionID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about a transaction with a given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "404", ref = "notfound")
    })
    TransactionDTO getTransaction(@PathVariable long transactionID, @AuthenticationPrincipal Jwt jwt) {
        return facade.getTransactionDTOById(jwt.getSubject(), transactionID);
    }

    @PutMapping("/{transactionID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit transaction with a given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", ref = "validation"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "404", ref = "notfound")
    })
    void editTransaction(@PathVariable long transactionID, @AuthenticationPrincipal Jwt jwt, @RequestBody @Valid EditTransactionDTO dto) {
        facade.editTransaction(jwt.getSubject(), transactionID, dto);
    }

}
