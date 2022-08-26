package com.eukon05.financetracker.wallet.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletFacade {

    private final CreateWalletUseCase createWalletUseCase;

    public void createWallet(String username, String name) {
        createWalletUseCase.createWallet(username, name);
    }

}
