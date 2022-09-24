package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.wallet.dto.WalletDTO;

interface GetWalletDTOByIdUseCase {

    WalletDTO getWalletDTOById(String username, long walletID);

}
