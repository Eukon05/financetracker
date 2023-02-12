package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public
class WalletFacade {
    private final WalletService service;

    public void createWallet(String userId, CreateWalletDTO dto) {
        service.createWallet(userId, dto);
    }

    public void deleteWallet(String userId, long walletID) {
        Wallet wallet = getWallet(userId, walletID);
        service.deleteWallet(wallet);
    }

    public void editWallet(String userId, long walletID, EditWalletDTO dto) {
        Wallet wallet = getWallet(userId, walletID);
        service.editWallet(wallet, dto);
    }

    public List<WalletDTO> getUserWalletDTOs(String userId) {
        return service.getUserWalletDTOs(userId);
    }

    public WalletDTO getWalletDTO(String userId, long walletID) {
        return service.getWalletDTO(userId, walletID);
    }

    public Wallet getWallet(String userId, long walletID) {
        return service.getWallet(userId, walletID);
    }

    public boolean checkOwnership(String userId, long walletID) {
        return service.checkOwnership(userId, walletID);
    }
}
