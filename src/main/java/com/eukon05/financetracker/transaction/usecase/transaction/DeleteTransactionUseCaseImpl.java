package com.eukon05.financetracker.transaction.usecase.transaction;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeleteTransactionUseCaseImpl implements DeleteTransactionUseCase {
    private final TransactionRepository repository;

    @Override
    public void deleteTransaction(Transaction transaction) {
        repository.delete(transaction);
    }
}
