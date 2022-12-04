package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.exceptions.TransactionCategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetTransactionCategoryUseCaseImpl implements GetTransactionCategoryUseCase {

    private final TransactionCategoryRepository repository;

    @Override
    public TransactionCategory getTransactionCategory(long id) {
        return repository.findById(id).orElseThrow(() -> new TransactionCategoryNotFoundException(id));
    }

}
