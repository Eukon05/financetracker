package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.TransactionCategoryDTO;
import com.eukon05.financetracker.transaction.usecase.transactionCategory.TransactionCategoryFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Transaction Category", description = "Handles operations related to transaction categories")
class TransactionCategoryController {

    private final TransactionCategoryFacade facade;

    @PostMapping
    void createTransactionCategory(@RequestBody @Valid CreateTransactionCategoryDTO dto) {
        facade.createTransactionCategory(dto);
    }

    @GetMapping
    List<TransactionCategoryDTO> getAllCategories(@RequestParam(name = "type", required = false) TransactionType type) {
        return facade.getTransactionCategoryDTOs(type);
    }

    @DeleteMapping("/{id}")
    void deleteTransactionCategory(@PathVariable long id) {
        facade.deleteTransactionCategory(id);
    }

    @PutMapping("/{id}")
    void editTransactionCategory(@PathVariable long id, @RequestBody @Valid EditTransactionCategoryDTO dto) {
        facade.editTransactionCategory(id, dto);
    }

}
