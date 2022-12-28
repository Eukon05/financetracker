package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.exception.WalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetWalletByIdUseCaseImpl implements GetWalletByIdUseCase {

    @Override
    public Wallet getWalletById(User user, long walletID) {
        return user
                .getWallets()
                .stream()
                .filter(wallet -> wallet.getId() == walletID)
                .findFirst()
                .orElseThrow(() -> new WalletNotFoundException(walletID));
    }
}
