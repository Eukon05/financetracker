package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionRepository;
import com.eukon05.financetracker.transaction.exceptions.TransactionNotFoundException;
import com.eukon05.financetracker.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetTransactionByIdUseCaseImpl implements GetTransactionByIdUseCase {
    private final TransactionRepository repository;

    @Override
    public Transaction getTransactionById(User user, long transactionID) {
        Transaction transaction = repository.findById(transactionID).orElseThrow(() -> new TransactionNotFoundException(transactionID));

        if (!transaction.getWallet().getUser().equals(user)) {
            throw new TransactionNotFoundException(transactionID);
        }

        return transaction;
    }
}
