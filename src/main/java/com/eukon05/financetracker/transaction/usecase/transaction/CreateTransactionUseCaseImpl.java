package com.eukon05.financetracker.transaction.usecase.transaction;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.exceptions.TransactionTypeMismatchException;
import com.eukon05.financetracker.transaction.mapper.TransactionModelMapper;
import com.eukon05.financetracker.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {
    private final TransactionModelMapper mapper;

    @Override
    @Transactional
    public void createTransaction(Wallet wallet, TransactionCategory category, CreateTransactionDTO dto) {
        Transaction transaction = mapper.mapCreateTransactionDTOtoTransaction(dto);
        if (!category.getType().equals(transaction.getType())) {
            throw new TransactionTypeMismatchException();
        }
        transaction.setCategory(category);
        wallet.getTransactions().add(transaction);
    }
}
