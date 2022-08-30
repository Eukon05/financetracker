package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionFacade {

    private final CreateTransactionUseCase createTransactionUseCase;

    public void createTransaction(String username, CreateTransactionDTO dto) {
        createTransactionUseCase.createTransaction(username, dto);
    }

}
