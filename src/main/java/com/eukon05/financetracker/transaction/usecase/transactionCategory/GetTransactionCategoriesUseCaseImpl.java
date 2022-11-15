package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.TransactionCategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class GetTransactionCategoriesUseCaseImpl implements GetTransactionCategoriesUseCase {

    private final TransactionCategoryRepository repository;

    @Override
    public List<TransactionCategory> getTransactionCategoryDTOs() {
        return repository.findAll();
    }

    @Override
    public List<TransactionCategory> getTransactionCategoryDTOs(TransactionCategoryType type) {
        return repository.findAllByType(type);
    }

}
