package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeleteTransactionUseCaseImpl implements DeleteTransactionUseCase {

    private final GetTransactionByIdUseCase getTransactionByIdUseCase;
    private final TransactionRepository repository;

    @Override
    public void deleteTransaction(String username, long transactionID) {
        Transaction transaction = getTransactionByIdUseCase.getTransactionById(username, transactionID);
        repository.delete(transaction);
    }
}
