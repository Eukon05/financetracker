package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
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
    public void createTransaction(Wallet wallet, CreateTransactionDTO dto) {
        wallet.getTransactions().add(mapper.mapCreateTransactionDTOtoTransaction(dto));
    }
}
