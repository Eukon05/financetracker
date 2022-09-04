package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionFacade {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;
    private final GetTransactionDTOByIdUseCase getTransactionDTOByIdUseCase;

    public void createTransaction(String username, CreateTransactionDTO dto) {
        createTransactionUseCase.createTransaction(username, dto);
    }

    public void deleteTransaction(String username, long transactionID) {
        deleteTransactionUseCase.deleteTransaction(username, transactionID);
    }

    public TransactionDTO getTransactionDTOById(String username, long transactionID) {
        return getTransactionDTOByIdUseCase.getTransactionDTOById(username, transactionID);
    }

}
