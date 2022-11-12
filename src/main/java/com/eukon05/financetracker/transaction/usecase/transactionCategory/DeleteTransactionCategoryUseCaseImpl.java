package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.exceptions.DefaultTransactionCategoryModificationException;
import com.eukon05.financetracker.transaction.exceptions.TransactionCategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeleteTransactionCategoryUseCaseImpl implements DeleteTransactionCategoryUseCase {

    private final TransactionCategoryRepository repository;

    @Override
    public void deleteTransactionCategory(long id) {
        if (id == 0) {
            throw new DefaultTransactionCategoryModificationException();
        }

        repository.findById(id)
                .ifPresentOrElse(c -> {
                    TransactionCategory dc = repository.findById(0L).get();
                    c.getTransactions().forEach(t -> t.setCategory(dc));
                    repository.delete(c);
                }, () -> {
                    throw new TransactionCategoryNotFoundException(id);
                });
    }

}
