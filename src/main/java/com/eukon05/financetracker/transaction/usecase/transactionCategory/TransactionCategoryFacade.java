package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionCategoryType;
import com.eukon05.financetracker.transaction.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.TransactionCategoryDTO;
import com.eukon05.financetracker.transaction.mapper.TransactionCategoryModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionCategoryFacade {

    private final GetTransactionCategoriesUseCase getTransactionCategoriesUseCase;
    private final CreateTransactionCategoryUseCase createTransactionCategoryUseCase;
    private final DeleteTransactionCategoryUseCase deleteTransactionCategoryUseCase;
    private final EditTransactionCategoryUseCase editTransactionCategoryUseCase;
    private final GetTransactionCategoryUseCase getTransactionCategoryUseCase;
    private final TransactionCategoryModelMapper mapper;

    public List<TransactionCategoryDTO> getTransactionCategoryDTOs(TransactionCategoryType type) {
        List<TransactionCategory> categories;

        if (Optional.ofNullable(type).isEmpty()) {
            categories = getTransactionCategoriesUseCase.getTransactionCategoryDTOs();
        }
        else {
            categories = getTransactionCategoriesUseCase.getTransactionCategoryDTOs(type);
        }

        return categories.stream().map(mapper::mapTransactionCategoryToTransactionCategoryDTO).toList();
    }

    public void createTransactionCategory(CreateTransactionCategoryDTO dto) {
        createTransactionCategoryUseCase.createTransactionCategory(dto);
    }

    public void deleteTransactionCategory(long id) {
        TransactionCategory category = getTransactionCategory(id);
        deleteTransactionCategoryUseCase.deleteTransactionCategory(category);
    }

    public void editTransactionCategory(long id, EditTransactionCategoryDTO dto) {
        TransactionCategory category = getTransactionCategory(id);
        editTransactionCategoryUseCase.editTransactionCategory(category, dto);
    }

    public TransactionCategory getTransactionCategory(long id) {
        return getTransactionCategoryUseCase.getTransactionCategory(id);
    }

    public TransactionCategoryDTO getTransactionCategoryDTO(long id) {
        return mapper.mapTransactionCategoryToTransactionCategoryDTO(getTransactionCategory(id));
    }

}
