package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.user.usecase.UserFacade;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.exception.WalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetWalletByIdUseCaseImpl implements GetWalletByIdUseCase {

    private final UserFacade userFacade;

    @Override
    public Wallet getWalletById(String username, long walletID) {
        return userFacade
                .getUserByUsernameOrThrow(username)
                .getWallets()
                .stream()
                .filter(wallet -> wallet.getId() == walletID)
                .findFirst()
                .orElseThrow(() -> new WalletNotFoundException(walletID));
    }
}
