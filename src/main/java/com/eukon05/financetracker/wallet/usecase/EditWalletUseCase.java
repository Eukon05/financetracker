package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;

interface EditWalletUseCase {

    void editWallet(Wallet wallet, EditWalletDTO dto);

}
