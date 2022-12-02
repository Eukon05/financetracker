package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.exceptions.DefaultTransactionCategoryModificationException;
import com.eukon05.financetracker.transaction.exceptions.TransactionCategoryAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class EditTransactionCategoryUseCaseImpl implements EditTransactionCategoryUseCase {

    private final TransactionCategoryRepository repository;

    @Override
    @Transactional
    public void editTransactionCategory(TransactionCategory category, EditTransactionCategoryDTO dto) {
        if (category.getId() == 0) {
            throw new DefaultTransactionCategoryModificationException();
        }

        Optional.ofNullable(dto.name()).ifPresent(name -> {
            if (repository.existsByNameAndType(name, category.getType())) {
                throw new TransactionCategoryAlreadyExistsException(name);
            }
            category.setName(name);
        });
    }

}
