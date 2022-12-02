package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.exceptions.DefaultTransactionCategoryModificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeleteTransactionCategoryUseCaseImpl implements DeleteTransactionCategoryUseCase {

    private final TransactionCategoryRepository repository;

    @Override
    public void deleteTransactionCategory(TransactionCategory category) {
        if (category.getId() == 0) {
            throw new DefaultTransactionCategoryModificationException();
        }

        TransactionCategory dc = repository.findById(0L).get();
        category.getTransactions().forEach(t -> t.setCategory(dc));

        repository.delete(category);
    }

}
