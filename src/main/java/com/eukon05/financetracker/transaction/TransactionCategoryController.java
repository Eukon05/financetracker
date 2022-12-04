package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.TransactionCategoryDTO;
import com.eukon05.financetracker.transaction.usecase.transactionCategory.TransactionCategoryFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Transaction Category", description = "Handles operations related to transaction categories")
class TransactionCategoryController {

    private final TransactionCategoryFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new transaction category")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", ref = "validation"),
                    @ApiResponse(responseCode = "401", ref = "unauthorized"),
                    @ApiResponse(responseCode = "403", ref = "unauthorized"),
                    @ApiResponse(responseCode = "409", ref = "conflict")
            }
    )
    void createTransactionCategory(@RequestBody @Valid CreateTransactionCategoryDTO dto) {
        facade.createTransactionCategory(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about all transaction categories")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "401", ref = "unauthorized"),
                    @ApiResponse(responseCode = "403", ref = "unauthorized")
            }
    )
    List<TransactionCategoryDTO> getAllCategories(@RequestParam(name = "type", required = false) TransactionCategoryType type) {
        return facade.getTransactionCategoryDTOs(type);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about a single transaction category with a given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", ref = "notfound"),
                    @ApiResponse(responseCode = "401", ref = "unauthorized"),
                    @ApiResponse(responseCode = "403", ref = "unauthorized"),
            }
    )
    TransactionCategoryDTO getCategory(@PathVariable long id) {
        return facade.getTransactionCategoryDTO(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete transaction category with a given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", ref = "notfound"),
                    @ApiResponse(responseCode = "401", ref = "unauthorized"),
                    @ApiResponse(responseCode = "403", ref = "unauthorized"),
            }
    )
    void deleteTransactionCategory(@PathVariable long id) {
        facade.deleteTransactionCategory(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit transaction category with a given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", ref = "notfound"),
                    @ApiResponse(responseCode = "401", ref = "unauthorized"),
                    @ApiResponse(responseCode = "403", ref = "unauthorized"),
                    @ApiResponse(responseCode = "400", ref = "validation"),
                    @ApiResponse(responseCode = "409", ref = "conflict")
            }
    )
    void editTransactionCategory(@PathVariable long id, @RequestBody @Valid EditTransactionCategoryDTO dto) {
        facade.editTransactionCategory(id, dto);
    }

}
