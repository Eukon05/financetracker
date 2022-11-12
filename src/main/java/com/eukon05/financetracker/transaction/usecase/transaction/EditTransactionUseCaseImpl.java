package com.eukon05.financetracker.transaction.usecase.transaction;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.exceptions.TransactionTypeMismatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class EditTransactionUseCaseImpl implements EditTransactionUseCase {

    @Override
    @Transactional
    public void editTransaction(Transaction transaction, EditTransactionDTO dto, TransactionCategory category) {
        Optional.ofNullable(dto.name()).ifPresent(transaction::setName);
        Optional.ofNullable(dto.value()).ifPresent(transaction::setValue);
        Optional.ofNullable(category).ifPresent(c -> {
            if (c.getId() != 0 && c.getType().sign != transaction.getValue().signum()) {
                throw new TransactionTypeMismatchException();
            }
            transaction.setCategory(c);
        });
    }
}
