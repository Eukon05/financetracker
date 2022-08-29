package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletFacade {

    private final CreateWalletUseCase createWalletUseCase;
    private final DeleteWalletUseCase deleteWalletUseCase;
    private final EditWalletUseCase editWalletUseCase;
    private final GetUserWalletsUseCase getUserWalletsUseCase;
    private final GetWalletByIdUseCase getWalletByIdUseCase;

    public void createWallet(String username, String name) {
        createWalletUseCase.createWallet(username, name);
    }

    public void deleteWallet(String username, long walletID) {
        deleteWalletUseCase.deleteWallet(username, walletID);
    }

    public void editWallet(String username, long walletID, String name) {
        editWalletUseCase.editWallet(username, walletID, name);
    }

    public List<WalletDTO> getUserWallets(String username) {
        return getUserWalletsUseCase.getUserWallets(username);
    }

    public Wallet getWalletById(String username, long walletID) {
        return getWalletByIdUseCase.getWalletById(username, walletID);
    }

}
