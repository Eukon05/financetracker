package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.dto.WalletDTO;

import java.util.List;

interface WalletService {

    void createWallet(String userId, CreateWalletDTO dto);

    void deleteWallet(Wallet wallet);

    void editWallet(Wallet wallet, EditWalletDTO dto);

    List<WalletDTO> getUserWalletDTOs(String userId);

    WalletDTO getWalletDTO(String userId, long walletID);

    Wallet getWallet(String userId, long walletID);

    boolean checkOwnership(String userId, long walletID);

}
