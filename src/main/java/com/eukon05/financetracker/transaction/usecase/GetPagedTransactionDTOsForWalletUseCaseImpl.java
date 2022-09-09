package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.TransactionRepository;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.mapper.TransactionModelMapper;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.usecase.WalletFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetPagedTransactionDTOsForWalletUseCaseImpl implements GetPagedTransactionDTOsForWalletUseCase {

    private final TransactionRepository repository;
    private final WalletFacade walletFacade;
    private final TransactionModelMapper mapper;

    @Override
    public Page<TransactionDTO> getPagedTransactionDTOsForWallet(String username, long walletID, Pageable pageable) {
        Wallet wallet = walletFacade.getWalletById(username, walletID);
        return repository.getTransactionsByWallet(wallet, pageable).map(mapper::mapTransactionToTransactionDTO);
    }
}
