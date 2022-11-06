package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionType;
import com.eukon05.financetracker.transaction.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.TransactionCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionCategoryFacade {

    private final GetTransactionCategoryDTOsUseCase getTransactionCategoryDTOsUseCase;
    private final CreateTransactionCategoryUseCase createTransactionCategoryUseCase;
    private final DeleteTransactionCategoryUseCase deleteTransactionCategoryUseCase;
    private final EditTransactionCategoryUseCase editTransactionCategoryUseCase;
    private final GetTransactionCategoryUseCase getTransactionCategoryUseCase;

    public List<TransactionCategoryDTO> getTransactionCategoryDTOs(TransactionType type) {
        if (Optional.ofNullable(type).isEmpty()) {
            return getTransactionCategoryDTOsUseCase.getTransactionCategoryDTOs();
        } else {
            return getTransactionCategoryDTOsUseCase.getTransactionCategoryDTOs(type);
        }
    }

    public void createTransactionCategory(CreateTransactionCategoryDTO dto) {
        createTransactionCategoryUseCase.createTransactionCategory(dto);
    }

    public void deleteTransactionCategory(long id) {
        deleteTransactionCategoryUseCase.deleteTransactionCategory(id);
    }

    public void editTransactionCategory(long id, EditTransactionCategoryDTO dto) {
        editTransactionCategoryUseCase.editTransactionCategory(id, dto);
    }

    public TransactionCategory getTransactionCategory(long id) {
        return getTransactionCategoryUseCase.getTransactionCategory(id);
    }

}
