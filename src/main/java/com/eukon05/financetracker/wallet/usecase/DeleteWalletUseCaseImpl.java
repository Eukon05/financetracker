package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteWalletUseCaseImpl implements DeleteWalletUseCase {

    @Override
    @Transactional
    public void deleteWallet(Wallet wallet) {
        wallet.getUser().getWallets().remove(wallet);
    }
}
