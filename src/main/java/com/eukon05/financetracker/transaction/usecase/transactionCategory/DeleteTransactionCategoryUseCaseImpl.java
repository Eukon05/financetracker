package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.exceptions.TransactionCategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeleteTransactionCategoryUseCaseImpl implements DeleteTransactionCategoryUseCase {

    private final TransactionCategoryRepository repository;

    @Override
    public void deleteTransactionCategory(long id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete, () -> {
                    throw new TransactionCategoryNotFoundException(id);
                });
    }

}
