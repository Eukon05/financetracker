package com.eukon05.financetracker.transaction.usecase.transaction;

import com.eukon05.financetracker.transaction.TransactionRepository;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.mapper.TransactionModelMapper;
import com.eukon05.financetracker.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPagedTransactionDTOsForWalletUseCaseImpl implements GetPagedTransactionDTOsForWalletUseCase {

    private final TransactionRepository repository;
    private final TransactionModelMapper mapper;

    @Override
    public Page<TransactionDTO> getPagedTransactionDTOsForWallet(Wallet wallet, Pageable pageable) {
        return repository.getTransactionsByWallet(wallet, pageable).map(mapper::mapTransactionToTransactionDTO);
    }
}
