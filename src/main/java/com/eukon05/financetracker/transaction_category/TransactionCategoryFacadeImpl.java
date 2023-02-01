package com.eukon05.financetracker.transaction_category;

import com.eukon05.financetracker.transaction_category.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.TransactionCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class TransactionCategoryFacadeImpl implements TransactionCategoryFacade {
    private final TransactionCategoryService service;

    public List<TransactionCategoryDTO> getTransactionCategoryDTOs(TransactionCategoryType type) {
        return service.getTransactionCategoryDTOs(type);
    }

    public void createTransactionCategory(CreateTransactionCategoryDTO dto) {
        service.createTransactionCategory(dto);
    }


    public void deleteTransactionCategory(long id) {
        TransactionCategory category = getTransactionCategory(id);
        service.deleteTransactionCategory(category);
    }

    public void editTransactionCategory(long id, EditTransactionCategoryDTO dto) {
        TransactionCategory category = getTransactionCategory(id);
        service.editTransactionCategory(category, dto);
    }

    public TransactionCategory getTransactionCategory(long id) {
        return service.getTransactionCategory(id);
    }

    public TransactionCategoryDTO getTransactionCategoryDTO(long id) {
        return service.getTransactionCategoryDTO(id);
    }

    public boolean exists(long id) {
        return service.exists(id);
    }
}
