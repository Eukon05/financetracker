package com.eukon05.financetracker.wallet.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletFacade {

    private final CreateWalletUseCase createWalletUseCase;
    private final DeleteWalletUseCase deleteWalletUseCase;

    public void createWallet(String username, String name) {
        createWalletUseCase.createWallet(username, name);
    }

    public void deleteWallet(String username, long walletID) {
        deleteWalletUseCase.deleteWallet(username, walletID);
    }

}
