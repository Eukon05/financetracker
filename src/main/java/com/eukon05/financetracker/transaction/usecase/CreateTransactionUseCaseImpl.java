package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.mapper.TransactionModelMapper;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.usecase.WalletFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final WalletFacade walletFacade;
    private final TransactionModelMapper mapper;

    @Override
    @Transactional
    public void createTransaction(String username, long walletID, CreateTransactionDTO dto) {
        Wallet wallet = walletFacade.getWalletById(username, walletID);
        wallet.getTransactions().add(mapper.mapCreateTransactionDTOtoTransaction(dto));
    }
}
