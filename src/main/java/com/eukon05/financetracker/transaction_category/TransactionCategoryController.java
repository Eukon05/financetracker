package com.eukon05.financetracker.transaction_category;

import com.eukon05.financetracker.openapi.response.AccessDeniedResponse;
import com.eukon05.financetracker.openapi.response.ConflictResponse;
import com.eukon05.financetracker.openapi.response.NotFoundResponse;
import com.eukon05.financetracker.openapi.response.ValidationErrorResponse;
import com.eukon05.financetracker.transaction_category.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.TransactionCategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
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
    @ValidationErrorResponse
    @ConflictResponse
    @AccessDeniedResponse
    void createTransactionCategory(@RequestBody @Valid CreateTransactionCategoryDTO dto) {
        facade.createTransactionCategory(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about all transaction categories")
    @AccessDeniedResponse
    List<TransactionCategoryDTO> getAllCategories(@RequestParam(name = "type", required = false) TransactionCategoryType type) {
        return facade.getTransactionCategoryDTOs(type);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about a single transaction category with a given ID")
    @NotFoundResponse
    @AccessDeniedResponse
    TransactionCategoryDTO getCategory(@PathVariable long id) {
        return facade.getTransactionCategoryDTO(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete transaction category with a given ID")
    @NotFoundResponse
    @AccessDeniedResponse
    void deleteTransactionCategory(@PathVariable long id) {
        facade.deleteTransactionCategory(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit transaction category with a given ID")
    @NotFoundResponse
    @AccessDeniedResponse
    @ValidationErrorResponse
    @ConflictResponse
    void editTransactionCategory(@PathVariable long id, @RequestBody @Valid EditTransactionCategoryDTO dto) {
        facade.editTransactionCategory(id, dto);
    }
}
