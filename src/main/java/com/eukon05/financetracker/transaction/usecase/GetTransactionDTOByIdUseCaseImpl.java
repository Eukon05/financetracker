package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.mapper.TransactionModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetTransactionDTOByIdUseCaseImpl implements GetTransactionDTOByIdUseCase {
    private final TransactionModelMapper mapper;
    private final GetTransactionByIdUseCase getTransactionByIdUseCase;

    @Override
    public TransactionDTO getTransactionDTOById(String username, long transactionID) {
        Transaction transaction = getTransactionByIdUseCase.getTransactionById(username, transactionID);
        return mapper.mapTransactionToTransactionDTO(transaction);
    }

}
