package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.usecase.transaction.TransactionFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "Transaction", description = "Handles operations related to transactions")
class TransactionController {

    private final TransactionFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", ref = "validation"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "404", ref = "notfound")
    })
    void createTransaction(@RequestBody @Valid CreateTransactionDTO dto, Principal principal) {
        facade.createTransaction(principal.getName(), dto);
    }

    @DeleteMapping("/{transactionID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete transaction with a given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "404", ref = "notfound")
    })
    void deleteTransaction(@PathVariable long transactionID, Principal principal) {
        facade.deleteTransaction(principal.getName(), transactionID);
    }

    @GetMapping(value = "/{transactionID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about a transaction with a given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "404", ref = "notfound")
    })
    TransactionDTO getTransaction(@PathVariable long transactionID, Principal principal) {
        return facade.getTransactionDTOById(principal.getName(), transactionID);
    }

    @PutMapping("/{transactionID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit transaction with a given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", ref = "validation"),
            @ApiResponse(responseCode = "401", ref = "unauthorized"),
            @ApiResponse(responseCode = "404", ref = "notfound")
    })
    void editTransaction(@PathVariable long transactionID, Principal principal, @RequestBody @Valid EditTransactionDTO dto) {
        facade.editTransaction(principal.getName(), transactionID, dto);
    }

}
