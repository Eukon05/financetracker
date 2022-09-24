package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class EditTransactionUseCaseImpl implements EditTransactionUseCase {

    private final GetTransactionByIdUseCase getTransactionByIdUseCase;

    @Override
    @Transactional
    public void editTransaction(String username, long transactionID, EditTransactionDTO dto) {
        Transaction transaction = getTransactionByIdUseCase.getTransactionById(username, transactionID);

        Optional.ofNullable(dto.name()).ifPresent(transaction::setName);
        Optional.ofNullable(dto.value()).ifPresent(transaction::setValue);
        Optional.ofNullable(dto.type()).ifPresent(transaction::setType);
    }
}
