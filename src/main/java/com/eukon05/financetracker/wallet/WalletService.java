package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.dto.WalletDTO;

import java.util.List;

public interface WalletService {

    void createWallet(String userId, CreateWalletDTO dto);

    void deleteWallet(String userId, long walletID);

    void editWallet(String userId, long walletID, EditWalletDTO dto);

    List<WalletDTO> getUserWalletDTOs(String userId);

    WalletDTO getWalletDTOById(String userId, long walletID);

    Wallet getWalletById(String userId, long walletID);

}
