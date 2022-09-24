package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteWalletUseCaseImpl implements DeleteWalletUseCase {
    private final GetWalletByIdUseCase getWalletByIdUseCase;

    @Override
    @Transactional
    public void deleteWallet(String username, long walletID) {
        Wallet wallet = getWalletByIdUseCase.getWalletById(username, walletID);
        wallet.getUser().getWallets().remove(wallet);
    }
}
